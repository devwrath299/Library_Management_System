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

import com.example.mylibrary.Admin_Fragments.Add_Book;
import com.example.mylibrary.Admin_Fragments.AdminProfile;
import com.example.mylibrary.Admin_Fragments.USers_profile;
import com.example.mylibrary.UsersFragment.Finepay;
import com.example.mylibrary.UsersFragment.Notification;
import com.example.mylibrary.UsersFragment.Return;
import com.example.mylibrary.UsersFragment.Searchbook;
import com.example.mylibrary.UsersFragment.bookhistory;
import com.example.mylibrary.UsersFragment.issue;
import com.example.mylibrary.UsersFragment.user_profiles;
import com.google.android.material.navigation.NavigationView;

public class usermain extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermain);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=findViewById(R.id.drawer);

        toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.user_containers,new Searchbook()).commit();


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp =new Fragment();
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.bookshisory:
                    {
                        temp=new bookhistory() ;
                        break;
                    }
                    case R.id.searchbook:
                    {
                        temp=new Searchbook();
                        break;
                    }
                    case R.id.fine_user:
                    {
                        temp=new Finepay();
                        break;
                    }
                    case R.id.notification:
                    {
                        temp=new Notification();
                        break;
                    }
                    case R.id.Issuebook:
                    {
                        temp=new issue();
                        break;
                    }
                    case R.id.Returnbook:
                    {
                        temp=new Return();
                        break;
                    }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.user_containers,temp).commit();
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
                //Toast.makeText(getApplicationContext(), "Sign in Sucessful", Toast.LENGTH_SHORT).show();
                myEdit.putString("name","null");
                myEdit.commit();
                startActivity(new Intent(usermain.this,First_activity.class));
                finish();
                break;
            }
            case R.id.profile:
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.user_containers,new USers_profile()).commit();
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}