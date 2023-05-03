package com.example.budgetplanner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetplanner.DB.AppDataBase;
import com.example.budgetplanner.DB.BudgetPlannerDAO;
import com.example.budgetplanner.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignupBinding binding;

    EditText mUsernameEditText;
    EditText mPasswordEditText;
    Button mSignUpButton;
    AppDataBase mAppDataBase;

    BudgetPlannerDAO mBudgetPlannerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mUsernameEditText = binding.mainSignUpUser;
        mPasswordEditText = binding.mainSignUpPassword;
        mSignUpButton = binding.signupButton;
        mAppDataBase = AppDataBase.getInstance(this);
        mBudgetPlannerDAO = mAppDataBase.budgetPlannerDao();

        mSignUpButton.setOnClickListener(v -> {
            String username = mUsernameEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();

            BudgetPlanner budgetPlanner = new BudgetPlanner(username, password);
            new InsertTask().execute(budgetPlanner);
        });
    }

    private class InsertTask extends AsyncTask<BudgetPlanner, Void, Void> {
        @Override
        protected Void doInBackground(BudgetPlanner... budgetPlanners) {
            mBudgetPlannerDAO.insert(budgetPlanners[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
