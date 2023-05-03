package com.example.budgetplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.budgetplanner.DB.AppDataBase;
import com.example.budgetplanner.DB.BudgetPlannerDAO;
import com.example.budgetplanner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    BudgetPlannerDAO mBudgetPlannerDAO;

    Button mLoginButton;

    Button mSignUpButton;

    EditText mUsernameEditText;

    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mSignUpButton= findViewById(R.id.signup_button);
        mLoginButton = binding.mainLoginButton;
        mUsernameEditText = binding.mainUsernameEditText;
        mPasswordEditText = binding.mainPasswordEditText;

        mBudgetPlannerDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .build()
                .budgetPlannerDao();

        mSignUpButton.setOnClickListener(v -> {
            Log.d("SignUpActivity", "Sign up button clicked");
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        mLoginButton.setOnClickListener(v -> {
            String username = mUsernameEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();
            new LoginTask(MainActivity.this, mBudgetPlannerDAO).execute(username, password);
        });

    }

    private static class LoginTask extends AsyncTask<String, Void, Boolean> {
        private BudgetPlannerDAO mBudgetPlannerDAO;
        private MainActivity mActivity;

        public LoginTask(MainActivity activity, BudgetPlannerDAO dao) {
            mActivity = activity;
            mBudgetPlannerDAO = dao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            BudgetPlanner budget = mBudgetPlannerDAO.getUser(username, password);

            if (budget != null) {
                // Save the budget ID in SharedPreferences
                SharedPreferences sharedPreferences = mActivity.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("budget_id", budget.getId());
                editor.apply();

                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Intent intent = new Intent(mActivity, DashboardActivity.class);
                mActivity.startActivity(intent);
            } else {
                Toast.makeText(mActivity, "Login failed. Please check your username and password.", Toast.LENGTH_SHORT).show();
            }
        }
    }




}


