package com.example.mylibrary.Registartion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.R;
import com.example.mylibrary.adminMain;
import com.example.mylibrary.databinding.ActivityMainBinding;
import com.example.mylibrary.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;


public class Admin_signup extends AppCompatActivity {
   TextView username,email,password;
   Button signup;
   FirebaseAuth auth;
   FirebaseDatabase database;
   DatabaseReference refrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        TextView  jump=findViewById(R.id.AlreadyHaveaccountadmin);
        jump.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),Admin_signin.class));
            finish();
        });

        auth=FirebaseAuth.getInstance();

       username=findViewById(R.id.admin_name);
       email=findViewById(R.id.admin_email);
       password=findViewById(R.id.admin_password);
       signup=findViewById(R.id.adminsignup);
       database=FirebaseDatabase.getInstance();
       refrence=database.getReference();



       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !username.getText().toString().isEmpty())
               {fn(email.getText().toString(),password.getText().toString());}
               else
               {
                   Toast.makeText(getApplicationContext(), "Enter All Credentials", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }

    void  fn(String a,String b) {
        auth.createUserWithEmailAndPassword(a,b).addOnCompleteListener(Admin_signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Registration Succesful", Toast.LENGTH_SHORT).show();
                    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    refrence.child("admin").child(currentuser).child("Adminame").setValue(username.getText().toString());
                    startActivity(new Intent(Admin_signup.this,adminMain.class));
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }




}