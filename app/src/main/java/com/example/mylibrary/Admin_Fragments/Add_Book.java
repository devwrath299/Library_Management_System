package com.example.mylibrary.Admin_Fragments;

import static android.view.View.GONE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.R;
import com.example.mylibrary.user;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_Book#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_Book extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Add_Book() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_Book.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_Book newInstance(String param1, String param2) {
        Add_Book fragment = new Add_Book();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView title,author,costs,quantitys,bookshelfnos,texts,bookid;
    String currentuserid;
    LinearLayout layout;
    Button add;
    FloatingActionButton fabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add__book, container, false);

        title=view.findViewById(R.id.TITLEs);
        bookid=view.findViewById(R.id.BOOKIDs);
        author=view.findViewById(R.id.AUTHOR);
        costs=view.findViewById(R.id.cost);
        quantitys=view.findViewById(R.id.quantity);
        bookshelfnos=view.findViewById(R.id.bookshelfno);
        add=view.findViewById(R.id.adds);
        layout=view.findViewById(R.id.l1);
        fabs=view.findViewById(R.id.fab);
        texts=view.findViewById(R.id.t100);

        fabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabs.setVisibility(GONE);
                texts.setVisibility(GONE);
                layout.setVisibility(View.VISIBLE);
            }
        });

        currentuserid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("admin").child(currentuserid).child("Books");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !title.getText().toString().isEmpty() && !author.getText().toString().isEmpty() && !costs.getText().toString().isEmpty() && !quantitys.getText().toString().isEmpty() && !bookshelfnos.getText().toString().isEmpty())
                {
                    String bookids=reference.push().getKey();
                    user users=new user(title.getText().toString(),bookids,author.getText().toString(),costs.getText().toString(),quantitys.getText().toString(),bookshelfnos.getText().toString());
                    reference.child(bookids).setValue(users);
                    Toast.makeText(getContext(), "Sucessfully added", Toast.LENGTH_SHORT).show();
                    fabs.setVisibility(View.VISIBLE);
                    texts.setVisibility(View.VISIBLE);
                    layout.setVisibility(GONE);

                }
                else
                {
                    Toast.makeText(getContext(), "Enter All Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }



}