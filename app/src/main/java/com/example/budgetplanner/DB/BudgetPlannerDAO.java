package com.example.budgetplanner.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetplanner.Budget;
import com.example.budgetplanner.BudgetPlanner;

@Dao
public interface BudgetPlannerDAO {
    @Insert
    void insert(BudgetPlanner...budgetPlanners);

    @Update
    void update(BudgetPlanner...budgetPlanners);

    @Delete
    void delete(BudgetPlanner...budgetPlanners);

    @Query("SELECT * FROM budget_table WHERE username = :username AND password = :password") // Update table name here
    BudgetPlanner getUser(String username, String password);

    @Query("SELECT EXISTS(SELECT * FROM budget_table WHERE username=:username AND password=:password)")
    Boolean isValidUser(String username, String password);

}
