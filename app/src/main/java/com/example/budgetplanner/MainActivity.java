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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BudgetPlannerDAO mBudgetPlannerDAO;
    private List<BudgetPlanner> mPredefinedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBudgetPlannerDAO = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "budgetplanner_db")
                .build()
                .budgetPlannerDao();

        mPredefinedUsers = new ArrayList<>();
        mPredefinedUsers.add(new BudgetPlanner("Admin1", "Admin1"));
        mPredefinedUsers.add(new BudgetPlanner("testuser1", "testuser1"));

        // ...

        Button loginButton = findViewById(R.id.mainLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameEditText = findViewById(R.id.mainUsernameEditText);
                EditText passwordEditText = findViewById(R.id.mainPasswordEditText);

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                new LoginTask(MainActivity.this, mBudgetPlannerDAO, mPredefinedUsers).execute(username, password);
            }
        });
    }

    private static class LoginTask extends AsyncTask<String, Void, Boolean> {
        private BudgetPlannerDAO mBudgetPlannerDAO;
        private MainActivity mActivity;
        private List<BudgetPlanner> mPredefinedUsers;

        public LoginTask(MainActivity activity, BudgetPlannerDAO dao, List<BudgetPlanner> predefinedUsers) {
            mActivity = activity;
            mBudgetPlannerDAO = dao;
            mPredefinedUsers = predefinedUsers;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            // Check if the user is one of the predefined users
            for (BudgetPlanner user : mPredefinedUsers) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }

            // If not, check the database for a matching user
            BudgetPlanner budget = mBudgetPlannerDAO.getUser(username, password);
            Log.d("LoginTask", "getUser() returned " + budget);

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
