package com.example.car_rental_prj;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;

public class Home_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView carServiceImageView, carSpareparts, userProfile, carsView;

    private static final String TAG = "Home_page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);

        // Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        carsView = findViewById(R.id.cars);
        carServiceImageView = findViewById(R.id.car_service);
        carSpareparts = findViewById(R.id.spare_parts);
        userProfile = findViewById(R.id.user_profile);

        // Set up the toolbar
        setSupportActionBar(toolbar);

        // Navigation Drawer menu
        navigationView.bringToFront();

        // To and fro navigation when button is clicked
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // Get user data from the intent
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        carsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Cars.class);
                startActivity(intent);
            }
        });

        carServiceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, FragmentsActivity.class);
                startActivity(intent);
            }
        });

        carSpareparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, parts.class);
                startActivity(intent);
            }
        });

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, user_profile.class);
                intent.putExtra("username", user_username);
                intent.putExtra("name", user_name);
                intent.putExtra("email", user_email);
                intent.putExtra("phoneNo", user_phoneNo);
                intent.putExtra("password", user_password);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        String itemName = getResources().getResourceName(itemId);
        Log.d(TAG, "Menu item ID: " + itemId + " (" + itemName + ")");

        if (itemId == R.id.nav_home) {
            Log.d(TAG, "Home menu item selected");

        } else if (itemId == R.id.nav_cars) {
            Log.d(TAG, "Cars menu item selected");
            Intent carsIntent = new Intent(Home_page.this, Cars.class);
            startActivity(carsIntent);
        } else if (itemId == R.id.nav_parts) {
            Log.d(TAG, "Parts menu item selected");
            Intent partsIntent = new Intent(Home_page.this, parts.class);
            startActivity(partsIntent);
//        } else if (itemId == R.id.nav_login) {
//            Log.d(TAG, "Login menu item selected");
//            // Handle the login action
//            // You can add an intent or action here
       } else if (itemId == R.id.nav_profile) {
            Log.d(TAG, "Profile menu item selected");
            Intent intent = getIntent();
            String user_username = intent.getStringExtra("username");
            String user_name = intent.getStringExtra("name");
            String user_email = intent.getStringExtra("email");
            String user_phoneNo = intent.getStringExtra("phoneNo");
            String user_password = intent.getStringExtra("password");

            Intent profileIntent = new Intent(Home_page.this, user_profile.class);
            profileIntent.putExtra("username", user_username);
            profileIntent.putExtra("name", user_name);
            profileIntent.putExtra("email", user_email);
            profileIntent.putExtra("phoneNo", user_phoneNo);
            profileIntent.putExtra("password", user_password);
            startActivity(profileIntent);
        } else if (itemId == R.id.nav_logout) {
            Log.d(TAG, "Logout menu item selected");

            logout();

        } else if (itemId == R.id.nav_share) {
            Log.d(TAG, "Share menu item selected");

            shareAppLink();

        } else if (itemId == R.id.nav_rate) {
            Log.d(TAG, "Rate us menu item selected");

            showRateDialog();


        } else {
            Log.d(TAG, "Unknown menu item selected");
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void logout() {
        // Clear the stored user session data
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        // Navigate back to the Login activity
        Intent intent = new Intent(Home_page.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void shareAppLink() {
        String shareMessage = "Check out this amazing car rental app: https://www.example.com";
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
    private void showRateDialog() {
        final Dialog dialog = new Dialog(Home_page.this);
        dialog.setContentView(R.layout.activity_dialog_rate);

        RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
        Button submitButton = dialog.findViewById(R.id.submit_rating_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                Toast.makeText(Home_page.this, "Thank you for rating us " + rating + " stars!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    }

