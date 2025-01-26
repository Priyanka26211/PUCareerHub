package com.example.pucareerhub;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.Cleaner;

public class RegisterActivity<Retrofit> extends AppCompatActivity {

        private EditText usernameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
        private Button registerButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            usernameEditText = findViewById(R.id.username_input);
            emailEditText = findViewById(R.id.editTextTextEmail);
            passwordEditText = findViewById(R.id.editTextTextPassword);
            confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
            registerButton = findViewById(R.id.buttonRegister);

            registerButton.setOnClickListener(v -> {
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

            };
        }
}