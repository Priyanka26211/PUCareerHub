package com.example.pucareerhub;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacementActivity extends AppCompatActivity {

    private TextView titleTextView, companyNameTextView, jobRoleTextView, placementDateTextView, salaryTextView, contactInfoTextView;
    private Button showMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        titleTextView = findViewById(R.id.title);
        companyNameTextView = findViewById(R.id.companyName);
        jobRoleTextView = findViewById(R.id.jobRole);
        placementDateTextView = findViewById(R.id.placementDate);
        salaryTextView = findViewById(R.id.salaryInfo);
        contactInfoTextView = findViewById(R.id.contactInfo);
        showMoreButton = findViewById(R.id.showMoreButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://your-server-ip:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getPlacementInfo().enqueue(new Callback<PlacementResponse>() {
            @Override
            public void onResponse(Call<PlacementResponse> call, Response<PlacementResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PlacementResponse placement = response.body();

                    titleTextView.setText("Placement Information");
                    companyNameTextView.setText("Company: " + placement.getCompanyName());
                    jobRoleTextView.setText("Job Role: " + placement.getJobRole());
                    placementDateTextView.setText("Placement Date: " + placement.getPlacementDate());
                    salaryTextView.setText("Salary: " + placement.getSalary());
                    contactInfoTextView.setText("Contact: " + placement.getContactInfo());
                } else {
                    Toast.makeText(PlacementActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlacementResponse> call, Throwable t) {
                Toast.makeText(PlacementActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Optional: Add functionality to "Show More" button if needed
        showMoreButton.setOnClickListener(v -> {
            // Handle the "Show More" action, maybe show additional details in a new screen
            Toast.makeText(PlacementActivity.this, "Show More clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
