package com.example.budgetplanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetplanner.DB.AppDataBase;
import com.example.budgetplanner.DB.BudgetPlannerDAO;

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.mainSignUpUser);
        passwordEditText = findViewById(R.id.mainSignUpPassword);
        signUpButton = findViewById(R.id.mainSignUpButton);

        appDataBase = AppDataBase.getInstance(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    usernameEditText.setError("Please enter a username");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Please enter a password");
                    return;
                }

                // Create new user
                final BudgetPlanner user = new BudgetPlanner(username, password);

                // Insert user into database in a separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        appDataBase.budgetPlannerDao().insertBudgetPlanner(user);
                    }
                }).start();

                // Start HomeActivity
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
