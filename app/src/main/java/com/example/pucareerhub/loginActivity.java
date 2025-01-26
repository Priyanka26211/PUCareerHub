package com.example.pucareerhub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private CheckBox showPasswordCheckBox;
    private Button loginButton;
    private TextView goToRegisterPage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Make sure to use the correct XML layout file name

        // Initialize the views
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.editTextTextPassword);
        showPasswordCheckBox = findViewById(R.id.checkBox);
        loginButton = findViewById(R.id.button);
        goToRegisterPage = findViewById(R.id.textView4);

        // Handle "Show Password" checkbox
        showPasswordCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                passwordInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
            } else {
                passwordInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        // Set up the login button click listener
        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                // Show error message if fields are empty
                Toast.makeText(loginActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            } else {
                // Perform login validation
                // For now, let's assume any non-empty values are valid
                Toast.makeText(loginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                // You can move to another activity if login is successful
                // For example: startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

        // Set up the "Go to Register Page" TextView click listener
        goToRegisterPage.setOnClickListener(v -> {
            // Navigate to the Register Activity
            // startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            Toast.makeText(loginActivity.this, "Navigating to Register Page", Toast.LENGTH_SHORT).show();
        });
    }
}

