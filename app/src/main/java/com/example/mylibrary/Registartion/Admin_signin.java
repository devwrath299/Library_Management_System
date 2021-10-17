package com.example.mylibrary.Registartion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.R;
import com.example.mylibrary.adminMain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_signin extends AppCompatActivity {
     TextView username,password;
     Button button;
    ProgressDialog progressDialog;
     FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin);
       TextView jump=findViewById(R.id.donotadmin);
        username=findViewById(R.id.adminsigninemail);
        password=findViewById(R.id.adminsigninpass);
        button =findViewById(R.id.signinbutton);
        auth= FirebaseAuth.getInstance();
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Admin_signup.class));
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                progressDialog=new ProgressDialog(Admin_signin.this);
                progressDialog.setMessage("Wait");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.setProgress(20);
                progressDialog.show();
             if(!user.isEmpty() && !pass.isEmpty())
             {
                 fn(user,pass);
             }
             else
             {
                 progressDialog.dismiss();
                 Toast.makeText(getApplicationContext(), "Enter All Credentials", Toast.LENGTH_SHORT).show();
             }
            }
        });


    }

    void fn(String a,String b)
    {
        auth.signInWithEmailAndPassword(a,b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
               try
               {
                   SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                   SharedPreferences.Editor myEdit = sharedPreferences.edit();
                   Toast.makeText(getApplicationContext(), "Sign in Sucessful", Toast.LENGTH_SHORT).show();
                   myEdit.putString("name","admin");
                   myEdit.commit();
                   Toast.makeText(getApplicationContext(), "Sign in Sucessful", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(getApplicationContext(), adminMain.class));
               }
               catch (Exception e)
               {
                   Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

}