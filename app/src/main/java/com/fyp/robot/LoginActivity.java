package com.fyp.robot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName,edtPassword;
    Button btnLogin,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        edtPassword= findViewById(R.id.edt_password);

        edtUserName = findViewById(R.id.edt_user_name);


        btnLogin= findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_singup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    Log.d("LoginActivity", "signInWithEmail:success");
                                    Toast.makeText(LoginActivity.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this,CustomerActivity.class));
                                    finish();
                                    // Proceed to next activity or handle login success
                                } else {
                                    // Sign in failed
                                    Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });



// Sign In



    }
}