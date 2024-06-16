package com.fyp.robot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class CustomerActivity extends AppCompatActivity {

    AppCompatButton btnShowOrder, btnOrderMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        btnShowOrder = findViewById(R.id.btn_show_order);
        btnOrderMenu = findViewById(R.id.btn_order_menu);
        btnShowOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerActivity.this, OrderShowActivity.class));
                finish();
            }
        });
        btnOrderMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerActivity.this, FaceActivity.class));
                finish();
            }
        });

    }
}