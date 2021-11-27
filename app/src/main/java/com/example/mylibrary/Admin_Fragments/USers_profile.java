package com.example.mylibrary.Admin_Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mylibrary.R;
import com.example.mylibrary.UsersAdapter;
import com.example.mylibrary.bookadapter;
import com.example.mylibrary.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link USers_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class USers_profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public USers_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment USers_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static USers_profile newInstance(String param1, String param2) {
        USers_profile fragment = new USers_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView recyclerView;
    ArrayList<user> list;
    UsersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_u_sers_profile, container, false);
        recyclerView = view.findViewById(R.id.recycle);

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("admin").child(currentuser).child("users");


        list = new ArrayList<>();
        adapter=new UsersAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user ad=new user();

                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    for(DataSnapshot d:dataSnapshot.getChildren())
                    {

                        if(d.getKey().equals("UserDetails"))
                        {
                            //Toast.makeText(getContext(),d.getKey().toString(), Toast.LENGTH_SHORT).show();
                            for(DataSnapshot s:d.getChildren())
                            {

                                if(s.getKey().equals("address"))
                                ad.setAddress(s.getValue().toString());
                                if(s.getKey().equals("contact"))
                                    ad.setContact(s.getValue().toString());
                                if(s.getKey().equals("email"))
                                    ad.setEmail(s.getValue().toString());
                                if(s.getKey().equals("username"));
                                    ad.setUsername(s.getValue().toString());


                            }

                        }
                        if(d.getKey().equals("fronturl"))
                        {
                           for(DataSnapshot sd:d.getChildren())
                           {
                            if(sd.getKey().equals("fronturl"))
                           ad.setUri(sd.getValue().toString());

                           }
                        }

                    }
                }
                list.add(ad);
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;


    }
}