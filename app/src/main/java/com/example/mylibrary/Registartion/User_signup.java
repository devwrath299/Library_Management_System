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

import com.example.mylibrary.First_activity;
import com.example.mylibrary.MainActivity;
import com.example.mylibrary.R;
import com.example.mylibrary.adminMain;
import com.example.mylibrary.usermain;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_signup extends AppCompatActivity {

 TextView username,email,password;
 Button button;
 FirebaseAuth auth;
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference refrence;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        TextView   jump=findViewById(R.id.AlreadyHaveaccountuser);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),User_sigin.class));
                finish();
            }
        });
        auth=FirebaseAuth.getInstance();
        username=findViewById(R.id.username);
        email=findViewById(R.id.useremail);
        password=findViewById(R.id.userpass);
        //adminid=findViewById(R.id.adminId);
        button=findViewById(R.id.user_sign_up);
        database=FirebaseDatabase.getInstance();
        refrence=database.getReference().child("admin");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog=new ProgressDialog(User_signup.this);
                progressDialog.setMessage("Wait");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.setProgress(20);
                progressDialog.show();

                String user=username.getText().toString();
                String emai=email.getText().toString();
                String pass=password.getText().toString();
                //String admin=adminid.getText().toString();
                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                 s1 = sh.getString("adminID", "");
                if(!user.isEmpty() && !emai.isEmpty() && !pass.isEmpty())
                {
                   fn(user,emai,pass);
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Enter All Credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    void  fn(String a,String b,String c)
    {
        auth.createUserWithEmailAndPassword(b,c).addOnCompleteListener(User_signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    Toast.makeText(getApplicationContext(), "Sign in Sucessful", Toast.LENGTH_SHORT).show();
                    myEdit.putString("name","user");
                    myEdit.commit();
                    Toast.makeText(getApplicationContext(), "Registrtion Successful", Toast.LENGTH_SHORT).show();
                    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    refrence.child(s1).child("users").child(currentuser).child("Username").setValue(a);
                    startActivity(new Intent(getApplicationContext(), usermain.class));
                    finish();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}