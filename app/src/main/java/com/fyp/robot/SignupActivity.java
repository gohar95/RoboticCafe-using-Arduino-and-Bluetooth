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

public class SignupActivity extends AppCompatActivity {

    EditText edtUserName,edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        edtPassword= findViewById(R.id.edt_password);

        edtUserName = findViewById(R.id.edt_email);


        btnLogin= findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    Log.d("LoginActivity", "signInWithEmail:success");
                                    Toast.makeText(SignupActivity.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                    finish();
                                    // Proceed to next activity or handle login success
                                } else {
                                    // Sign in failed
                                    Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}