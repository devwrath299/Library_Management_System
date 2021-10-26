package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.mylibrary.Admin_Fragments.Add_Book;
import com.example.mylibrary.Admin_Fragments.AdminProfile;
import com.example.mylibrary.Admin_Fragments.Payment;
import com.example.mylibrary.Admin_Fragments.USers_profile;
import com.example.mylibrary.Admin_Fragments.Update_book;
import com.google.android.material.navigation.NavigationView;

public class adminMain extends AppCompatActivity {
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
         Toolbar toolbar=findViewById(R.id.admintoolbar);
        drawerLayout=findViewById(R.id.admindrawer);
        nav=findViewById(R.id.adminnav);
         setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,new Add_Book()).commit();

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.Addbook:
                    {
                        temp=new Add_Book();
                        break;
                    }
                    case R.id.updatebook:
                    {
                        temp=new Update_book();
                        break;
                    }
                    case R.id.users_profile:
                    {
                        temp= new USers_profile();
                        break;
                    }
                    case R.id.fine:
                    {
                        temp=new Payment();

                        break;
                    }

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.containers,temp).commit();
                 drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rightside, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.Logout:
            {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
               //  Toast.makeText(getApplicationContext(), "Sign in Sucessful", Toast.LENGTH_SHORT).show();
                myEdit.putString("name","null");
                myEdit.commit();
                startActivity(new Intent(adminMain.this,First_activity.class));
                finish();
                break;
            }
            case R.id.profile:
            {//your action
                getSupportFragmentManager().beginTransaction().replace(R.id.containers,new AdminProfile()).commit();
                break;}
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}