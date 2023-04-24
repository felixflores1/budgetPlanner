package com.example.budgetplanner.DB;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.budgetplanner.BudgetPlanner;

@Dao
public interface BudgetPlannerDAO {
    @Query("SELECT * FROM budget_table WHERE username = :username AND password = :password") // Update table name here
    BudgetPlanner getUser(String username, String password);
    @Insert
    void insertBudgetPlanner(BudgetPlanner budgetPlanner);



}
