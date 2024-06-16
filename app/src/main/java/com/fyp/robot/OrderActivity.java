package com.fyp.robot;

import static android.app.PendingIntent.getActivity;
import static com.fyp.robot.R.id.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentActivity;

import com.fyp.robot.R;
import com.fyp.robot.databinding.FragmentTerminalBinding;
import com.fyp.robot.robot.MainActivity;
import com.fyp.robot.robot.SerialListener;
import com.fyp.robot.robot.SerialService;
import com.fyp.robot.robot.TerminalFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {

    AppCompatButton btnConfirmOrder,btnPayCOD,btnpayJazzcash;
    EditText edtName,edtQuantity,edtTableNumber;
    TextView txtProductName,txtProductPrice;

    String price,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        FirebaseApp.initializeApp(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Reference to the root of your Firebase Realtime Database
        DatabaseReference rootRef = database.getReference("tableNumber");
        txtProductName = findViewById(R.id.txt_product_name);
        txtProductPrice = findViewById(R.id.txt_product_price);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            price = extras.getString("price");
            description = extras.getString("description");
            String buttonClicked = extras.getString("buttonClicked");
            if (buttonClicked != null) {
                if (buttonClicked.equals("GreenTeaButton")) {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                } else if (buttonClicked.equals("SpecialTeaButton")) {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                }
                else if (buttonClicked.equals("SpecialTeaButton"))
                {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                }
                else if (buttonClicked.equals("CaramelCoffeeButton"))
                {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                }
                else if (buttonClicked.equals("LatteCoffeeButton"))
                {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                }
                else if (buttonClicked.equals("CappucinnoCoffeeButton"))
                {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                }
                else if (buttonClicked.equals("ChickenSandwichButton"))
                {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                }
                else if (buttonClicked.equals("MegaSandwichButton"))
                {
                    txtProductName.setText(description);
                    txtProductPrice.setText(price);
                }


            }

            // Now you can use the received data as needed
        }








        final MediaPlayer mp = MediaPlayer.create(this, R.raw.confirmation);

        btnConfirmOrder = findViewById(R.id.btn_confirm_order);
        edtName = findViewById(R.id.edt_name);
        edtQuantity = findViewById(R.id.edt_quantity);
        edtTableNumber = findViewById(R.id.edt_table_number);
        btnPayCOD = findViewById(R.id.btn_cash_order);
        btnpayJazzcash = findViewById(R.id.btn_online);



        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        btnPayCOD.setOnClickListener(v -> {

        });
        btnpayJazzcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString(); //
                String quantity = edtQuantity.getText().toString();
                String tableNumber = edtTableNumber.getText().toString(); //

                Order order1 = new Order(name,quantity,tableNumber,description);
                rootRef.setValue(order1, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if (error != null) {
                            System.out.println("Data could not be saved: " + error.getMessage());
                        } else {
                            System.out.println("Data saved successfully.");
                        }
                    }
                });
                mp.start();




                Toast.makeText(OrderActivity.this,"Order Confirmed",Toast.LENGTH_LONG).show();
                myEdit.putString("name", edtName.getText().toString());
                myEdit.putString("quantity", edtQuantity.getText().toString());
                myEdit.putString("TableNumber", edtTableNumber.getText().toString());
                myEdit.apply();



                startActivity(new Intent(OrderActivity.this,MainActivity.class));
                finish();
            }
        });




    }
}