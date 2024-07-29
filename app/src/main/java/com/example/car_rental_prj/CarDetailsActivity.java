
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

public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_car_details);

        // Get data from intent
        Intent intent = getIntent();
        String carName = intent.getStringExtra("car_name");


        ImageView carImage = findViewById(R.id.car_image);
        TextView carNameTextView = findViewById(R.id.car_name);
        TextView carDescriptionTextView = findViewById(R.id.car_description);
        Button buyCarButton = findViewById(R.id.buy_car_button);

        //car details
        switch (carName) {
            case "Rolls Royce":
                carImage.setImageResource(R.drawable.car1);
                carNameTextView.setText(carName);
                carDescriptionTextView.setText("Luxurious epitome of automotive elegance and unparalleled craftsmanship.");
                break;
            case "Audi Etron gt":
                carImage.setImageResource(R.drawable.car2);
                carNameTextView.setText(carName);
                carDescriptionTextView.setText("Cutting-edge electric vehicle with futuristic design and advanced technology.");
                break;
            case "BMW":
                carImage.setImageResource(R.drawable.car3);
                carNameTextView.setText(carName);
                carDescriptionTextView.setText("Iconic blend of performance, innovation, and driving pleasure.");
                break;
            case "KIA":
                carImage.setImageResource(R.drawable.car4);
                carNameTextView.setText(carName);
                carDescriptionTextView.setText("Reliable and stylish vehicles known for affordability and modern features.");
                break;
            case "Mercedes-Benz":
                carImage.setImageResource(R.drawable.car5);
                carNameTextView.setText(carName);
                carDescriptionTextView.setText("Synonymous with luxury, innovation, and automotive sophistication.");
                break;
            case "Toyota Corolla":
                carImage.setImageResource(R.drawable.car6);
                carNameTextView.setText(carName);
                carDescriptionTextView.setText("Reliable compact car celebrated for its efficiency and practicality.");
                break;
            default:

                break;
        }

        // Buy a Car button
        buyCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CarDetailsActivity.this, "Buy a Car button clicked", Toast.LENGTH_SHORT).show();

            }
        });

        // WhatsApp button
        Button whatsappButton = findViewById(R.id.whatsapp_button);
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "03315937444";
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
