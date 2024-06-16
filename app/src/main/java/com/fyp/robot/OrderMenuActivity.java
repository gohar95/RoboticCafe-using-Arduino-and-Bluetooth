package com.fyp.robot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class OrderMenuActivity extends AppCompatActivity {
    TextToSpeech mTTS;
    TextView teaBubble,teaGreen,teaSpecial,coffeeCafe,coffeeCapu,coffeeCaramel,sandwichChicken,sandwichMega;
    int teaPrice1,teaPrice2,teaPrice3,coffeePrice1,coffeePrice2,coffeePrice3,sandwichPrice1,sandwichPrice2;
    LinearLayout Tea1,Tea2,Tea3,Coffee1,Coffe2,Coffee3,Sandwich1,Sandwich2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_menu);
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mTTS.speak("Please Place Your Order", TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        Tea1 = findViewById(R.id.green_tea_layout);
        Tea2 = findViewById(R.id.special_tea);
        Tea3 = findViewById(R.id.bubble);
        Coffee1 = findViewById(R.id.cappu_coffe);
        Coffe2 = findViewById(R.id.cafe_coffee);
        Coffee3 = findViewById(R.id.caramel_layout);
        Sandwich1 = findViewById(R.id.sadwich1);
        Sandwich2 = findViewById(R.id.sandwich2);
        teaBubble = findViewById(R.id.desc_bubble_tea);
        teaGreen = findViewById(R.id.desc_green_tea);
        teaSpecial = findViewById(R.id.desc_special_tea);
        coffeeCapu = findViewById(R.id.desc_cappu_coffee);
        coffeeCafe = findViewById(R.id.desc_latte_coffee);
        coffeeCaramel = findViewById(R.id.desc_caramel_coffee);
        sandwichChicken = findViewById(R.id.desc_chicken_sandwich);
        sandwichMega = findViewById(R.id.desc_mega_sandwich);
        coffeePrice1 = coffeePrice2 = coffeePrice3 = teaPrice3 = teaPrice1 = teaPrice2 = 250;

        Tea1.setOnClickListener(view -> {
            // Storing data into SharedPreferences
            Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
            i.putExtra("price", "250");
            i.putExtra("description", "Green Tea");
            i.putExtra("buttonClicked", "GreenTeaButton");
            startActivity(i);


        });

        Tea2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
                i.putExtra("price", "250");
                i.putExtra("description", "Special Tea with Cookies");
                i.putExtra("buttonClicked", "SpecialTeaButton");
                startActivity(i);
            }
        });
        Tea3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
                i.putExtra("price", "250");
                i.putExtra("description", "Bubble Tea");
                i.putExtra("buttonClicked", "BubbleTeaButton");
                startActivity(i);
            }
        });
        Coffee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
                i.putExtra("price", "250");
                i.putExtra("description", "Cappuccino");
                i.putExtra("buttonClicked", "CappucinnoCoffeeButton");
                startActivity(i);
            }
        });
        Coffe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
                i.putExtra("price", "250");
                i.putExtra("description", "Cafe latte");
                i.putExtra("buttonClicked", "LatteCoffeeButton");
                startActivity(i);
            }
        });
        Coffee3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
                i.putExtra("price", "250");
                i.putExtra("description", "Caramel correto");
                i.putExtra("buttonClicked", "CaramelCoffeeButton");
                startActivity(i);
            }
        });
        Sandwich1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
                i.putExtra("price", "250");
                i.putExtra("description", "Chicken teriyaki sandwich");
                i.putExtra("buttonClicked", "ChickenSandwichButton");
                startActivity(i);
            }
        });
        Sandwich2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderMenuActivity.this, OrderActivity.class);
                i.putExtra("price", "250");
                i.putExtra("description", "Mega sandwich");
                i.putExtra("buttonClicked", "MegaSandwichButton");
                startActivity(i);
            }
        });

    }

}