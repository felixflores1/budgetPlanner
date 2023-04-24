package com.example.budgetplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.budgetplanner.DB.AppDataBase;
import com.example.budgetplanner.DB.BudgetPlannerDAO;
import com.example.budgetplanner.R;

public class MainActivity extends AppCompatActivity {

    private Context appContext;
    private AppDataBase mBudgetPlannerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BudgetPlannerDAO budgetPlannerDAO;
        AppDataBase appDataBase;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBudgetPlannerDatabase = AppDataBase.getInstance(this);

        // Get references to UI elements
        Button loginButton = findViewById(R.id.mainLoginButton);
        Button signUpButton = findViewById(R.id.signup_button);
        EditText usernameEditText = findViewById(R.id.mainUsernameEditText);
        EditText passwordEditText = findViewById(R.id.mainPasswordEditText);


        // Set onClickListener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get username and password inputs
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if username and password are valid
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    BudgetPlanner user = mBudgetPlannerDatabase.budgetPlannerDao().getUser(username, password);
                    if (user != null) {
                        // Navigate to the user's dashboard
                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        intent.putExtra("user_id", user.getId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }







}