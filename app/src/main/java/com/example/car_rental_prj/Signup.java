package com.example.car_rental_prj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //varibles
    TextInputLayout regName, regUsername, regEmail, regPhoneNO, regPassword;
    Button regBtn, regTOLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);


        //Hooks
        regName = findViewById(R.id.fullname);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhoneNO = findViewById(R.id.phoneno);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.regbtn);
        regTOLoginBtn = findViewById(R.id.regToLoginbtn);

        //when click "Already have an account" button
        regTOLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });



    //When click "Signup Go" button
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                if (validateName() | validateUserName() | validateEmail() | validatePhoneNo() | validatePassword()) {
                    //Get all values
                    String name = regName.getEditText().getText().toString();
                    String username = regUsername.getEditText().getText().toString();
                    String email = regEmail.getEditText().getText().toString();
                    String phoneNo = regPhoneNO.getEditText().getText().toString();
                    String password = regPassword.getEditText().getText().toString();

                    userHelperClass helperClass = new userHelperClass(name, username, email, phoneNo, password);
                    reference.child(username).setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to save data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }

            }

        });
    }

    private boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else{
            //null remove error
            regName.setError(null);
            regName.setErrorEnabled(false);//remove space reserved by design if occur
            return true;
        }
    }
    private boolean validateUserName() {
        String val = regUsername.getEditText().getText().toString();
        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length()>16) {
            regUsername.setError("username too long");
            return false;

        } else{
            //null remove error
            regUsername.setError(null);//it hide error
            regUsername.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern  = "[a-zA-Z0-9. _-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else{
            //null remove error
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePhoneNo() {
        String val = regPhoneNO.getEditText().getText().toString();
        if(val.isEmpty()){
            regPhoneNO.setError("Field cannot be empty");
            return false;
        }
        else{
            //null remove error
            regPhoneNO.setError(null);
            regPhoneNO.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordval = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";

        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordval)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            // Remove error
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

}
