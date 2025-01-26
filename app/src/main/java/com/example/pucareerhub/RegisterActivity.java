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

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    // Create the Retrofit instance
                    Cleaner GsonConverterFactory;
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://your-server-ip:3000/")  // Replace with your server IP or domain
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiService apiService = retrofit.create(ApiService.class);
                    RegisterRequest registerRequest = new RegisterRequest(username, email, password, confirmPassword);

                    // Make the API call
                    Call<RegisterResponse> call = apiService.registerUser(registerRequest);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
