package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mylibrary.Registartion.Admin_signin;
import com.example.mylibrary.Registartion.User_sigin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class First_activity extends AppCompatActivity {
    LinearLayout user,admin;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        user=findViewById(R.id.user);
        admin=findViewById(R.id.admin);


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(First_activity.this);
                View view1=getLayoutInflater().inflate(R.layout.dialog,null);
                EditText adminid=view1.findViewById(R.id.adminids);
                Button button=view1.findViewById(R.id.materialButton);
                builder.setMessage("Enter Your Admin ID Here");

                builder.setView(view1);
                AlertDialog dialog=builder.create();
                dialog.show();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog=new ProgressDialog(First_activity.this);
                        progressDialog.setMessage("Wait");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setProgress(20);
                        progressDialog.show();

                        if(!adminid.getText().toString().isEmpty())
                        {
                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("admin");
                            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    progressDialog.dismiss();
                                    if (snapshot.hasChild(adminid.getText().toString())) {
                                        // Exist! Do whatever.
                                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("adminID",adminid.getText().toString());
                                        myEdit.commit();
                                        Intent intent=new Intent(getApplicationContext(),User_sigin.class);
                                        intent.putExtra("ADMINID",adminid.getText().toString());
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // Don't exist! Do something.
                                        Toast.makeText(getApplicationContext(), "Wrong ID", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed, how to handle?

                                }

                            });
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Enter Admin Id", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(First_activity.this, Admin_signin.class));
            }
        });


    }


}