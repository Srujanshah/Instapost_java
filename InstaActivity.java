package com.example.instapost_java;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class InstaActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth mAuth;
    private TextView userListTextView, hashtagListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta);

        // Firebase
        mAuth = FirebaseAuth.getInstance();

        // Navigation Bar
        drawerLayout = findViewById(R.id.drawer_layout);
        userListTextView = findViewById(R.id.user_text_view);
        hashtagListTextView = findViewById(R.id.hashtag_text_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.my_profile) {
                    Intent profileIntent = new Intent(InstaActivity.this, ImagesActivity.class);
                    startActivity(profileIntent);
                }
                else if (id == R.id.new_post) {
                    Intent moveToUpload = new Intent(InstaActivity.this, UploadActivity.class);
                    startActivity(moveToUpload);
                }
                else if (id == R.id.sign_out) {
                    Logout();
                }

                return true;
            }
        });

        userListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserListActivity.class));
            }
        });

        hashtagListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HashtagListActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void Logout() {
        mAuth.signOut();
        finish();
        Intent moveToHome = new Intent(InstaActivity.this, MainActivity.class);
        startActivity(moveToHome);
    }
}
