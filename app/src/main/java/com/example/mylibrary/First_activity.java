package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mylibrary.Registartion.Admin_signin;
import com.example.mylibrary.Registartion.User_sigin;

public class First_activity extends AppCompatActivity {
    LinearLayout user,admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        user=findViewById(R.id.user);
        admin=findViewById(R.id.admin);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(First_activity.this, User_sigin.class));
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