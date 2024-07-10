package com.example.car_rental_prj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PartsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_part_details);


        Intent intent = getIntent();
        String partName = intent.getStringExtra("part_name");


        ImageView partImage = findViewById(R.id.part_image);
        TextView partNameTextView = findViewById(R.id.part_name);
        TextView partDescriptionTextView = findViewById(R.id.part_description);
        Button buyPartButton = findViewById(R.id.buy_part_button);

        //parts details
        switch (partName) {
            case "Engineoil":
                partImage.setImageResource(R.drawable.part1);
                partNameTextView.setText(partName);
                partDescriptionTextView.setText("Lubricates and cools engine components, ensuring smooth operation.");
                break;
            case "Carfilters":
                partImage.setImageResource(R.drawable.part2);
                partNameTextView.setText(partName);
                partDescriptionTextView.setText("Remove impurities from air, fuel, oil and also enhancing engine power and performance.");
                break;
            case "Ledlights":
                partImage.setImageResource(R.drawable.part3);
                partNameTextView.setText(partName);
                partDescriptionTextView.setText("Provide bright, energy-efficient lighting for improved visibility and aesthetics.");
                break;
            case "Spark Plugs":
                partImage.setImageResource(R.drawable.part4);
                partNameTextView.setText(partName);
                partDescriptionTextView.setText("Ignites the air-fuel mixture in the engine's chamber, enabling performance.");
                break;
            case "Suspensionkit":
                partImage.setImageResource(R.drawable.part5);
                partNameTextView.setText(partName);
                partDescriptionTextView.setText("Enhances vehicle handling and comfort by absorbing shocks and maintaining tire contact with the road.");
                break;
            case "Engineparts":
                partImage.setImageResource(R.drawable.part6);
                partNameTextView.setText(partName);
                partDescriptionTextView.setText("Various components like pistons, valves, and crankshaft, crucial for the engine's function and efficiency.");
                break;
            default:
                // Handle default case
                break;
        }

        // Buy Part button
        buyPartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PartsDetailsActivity.this, "Thanks for wishing to buy a car.Our team will contact you soon ", Toast.LENGTH_SHORT).show();

            }
        });

        Button whatsappButton = findViewById(R.id.whatsapp_button);
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "";
                openWhatsApp(phoneNumber);
            }
        });
    }

    private void openWhatsApp(String phoneNumber) {
        Uri uri = Uri.parse("https://wa.me/" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
