package com.example.budgetplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private Button manageBudgetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardlogin);

        // Initialize the manageBudgetButton and set up its OnClickListener
        manageBudgetButton = findViewById(R.id.manageButton);
        manageBudgetButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, BudgetActivity.class);
            startActivity(intent);
        });
    }
}
