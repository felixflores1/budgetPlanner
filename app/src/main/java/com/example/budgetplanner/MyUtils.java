package com.example.budgetplanner;

import android.content.Context;

import com.example.budgetplanner.DB.AppDataBase;
import com.example.budgetplanner.DB.BudgetPlannerDAO;

public class MyUtils {
    public static void insertUser(Context context, String username, String password) {
        // Insert the user into the database using the BudgetPlannerDAO
        AppDataBase db = AppDataBase.getInstance(context);
        BudgetPlannerDAO dao = db.budgetPlannerDao();
        dao.insertBudgetPlanner(new BudgetPlanner(username, password));
    }
}
