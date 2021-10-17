package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_screen extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        auth=FirebaseAuth.getInstance();
        FirebaseUser user =auth.getCurrentUser();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String s1 = sharedPreferences.getString("name", "");
                    //Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_SHORT).show();
                    if(user!=null){
                    if(s1.equals("user")) {
                        startActivity( new Intent(Splash_screen.this, usermain.class));
                        finish();
                    }
                    else if(s1.equals("admin")) {
                        startActivity(  new Intent(Splash_screen.this, adminMain.class));
                        finish();}
                        else{
                            Intent i=new Intent(Splash_screen.this, First_activity.class);
                            startActivity(i);
                            finish();}
                    }
                    else{
                    Intent i=new Intent(Splash_screen.this, First_activity.class);
                    startActivity(i);
                    finish();}

                }
            }, 1000);
        }
}
