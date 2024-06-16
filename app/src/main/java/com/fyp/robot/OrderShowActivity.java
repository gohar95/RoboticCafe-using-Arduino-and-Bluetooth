package com.fyp.robot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderShowActivity extends AppCompatActivity {

    TextView txtName,txtQuatity,txtTableName;
    Button btnMainScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_show);

        txtName = findViewById(R.id.txt_name);
        txtQuatity = findViewById(R.id.txt_quantity);
        txtTableName = findViewById(R.id.txt_table_name);
        btnMainScreen = findViewById(R.id.btn_main_screen);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String name = sh.getString("name", "null");
        String quantity = sh.getString("quantity", "null");
        String tableNumber = sh.getString("TableNumber","null");
        txtName.setText(name);
        txtQuatity.setText(quantity);
        txtTableName.setText(tableNumber);

        btnMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderShowActivity.this,CustomerActivity.class));

                finish();
            }
        });
    }
}