package com.example.car_rental_prj;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.car_rental_prj.BlueCarFragment;
import com.example.car_rental_prj.GreenCarFragment;
import com.example.car_rental_prj.RedCarFragment;
import com.example.car_rental_prj.WhiteCarFragment;
import com.example.car_rental_prj.YellowCarFragment;

public class FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fragments);

        // Set default fragment
        replaceFragment(new WhiteCarFragment());

        ImageButton whiteButton = findViewById(R.id.buttonBlack);
        ImageButton redButton = findViewById(R.id.buttonRed);
        ImageButton blueButton = findViewById(R.id.buttonBlue);
        ImageButton greenButton = findViewById(R.id.buttonGreen);
        ImageButton yellowButton = findViewById(R.id.buttonYellow);

        whiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new WhiteCarFragment());
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new RedCarFragment());
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new BlueCarFragment());
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new GreenCarFragment());
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new YellowCarFragment());
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
