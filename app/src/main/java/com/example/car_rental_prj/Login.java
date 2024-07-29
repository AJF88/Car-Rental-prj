package com.example.car_rental_prj;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    // Variables
    Button callSignup, login_btn;
    ImageView logo;
    TextView logoText, logoDesc;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_text);
        logoDesc = findViewById(R.id.slogan_text);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.loginButton);
        callSignup = findViewById(R.id.signup_screen);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        callSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(logo, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(logoDesc, "logo_desc");
                pairs[3] = new Pair<View, String>(username, "username_textfield");
                pairs[4] = new Pair<View, String>(password, "password_textfield");
                pairs[5] = new Pair<View, String>(login_btn, "go_button_textfield");
                pairs[6] = new Pair<View, String>(callSignup, "signup_button_textfield");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }

    private boolean validateUserName() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser() {
        if (!validateUserName() | !validatePassword()) {
            return;
        } else {

            ifUser();
        }
    }

    private void ifUser() {
        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Home_page.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                    } else {
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                } else {
                    username.setError("No such user found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Error method here
            }
        });
    }
}
