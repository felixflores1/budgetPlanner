package com.example.budgetplanner;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.budgetplanner.DB.AppDataBase;

@Entity(tableName = AppDataBase.BUDGET_TABLE)
public class BudgetPlanner {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "budget")
    private double budget;

    @ColumnInfo(name = "savings_goal")
    private String savingsGoal;

    @ColumnInfo(name = "notification")
    private String notification;

    public BudgetPlanner(String username, String password) {
        this.username = username;
        this.password = password;
        this.budget = 0.0;
        this.savingsGoal = "";
        this.notification = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setSavingsGoal(String savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    public String getSavingsGoal() {
        return savingsGoal;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public double getTotalExpenses() {
        // TODO: calculate the user's total expenses here
        return 0.0;
    }

    public double getRemainingBudget() {
        // TODO: calculate the user's remaining budget here
        return 0.0;
    }

    public double getSavingsProgress() {
        // TODO: calculate the user's savings progress here
        return 0.0;
    }

    public void addExpense(String category, double amount) {
        // TODO: add the user's expense here
    }

    public void exportData() {
        // TODO: export the user's data here
    }

}