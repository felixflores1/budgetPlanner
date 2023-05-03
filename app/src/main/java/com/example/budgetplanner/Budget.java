package com.example.budgetplanner;

import java.text.DecimalFormat;

public class Budget {
    private double budgetAmount;
    private String budgetCategory;
    private double expensesTotal;
    private boolean budgetStarted;

    public Budget(double budgetAmount, String budgetCategory) {
        this.budgetAmount = budgetAmount;
        this.budgetCategory = budgetCategory;
        this.expensesTotal = 0.0;
        this.budgetStarted = false;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public String getBudgetCategory() {
        return budgetCategory;
    }

    public double getExpensesTotal() {
        return expensesTotal;
    }

    public double getRemainingBudget() {
        return budgetAmount - expensesTotal;
    }

    public boolean isBudgetStarted() {
        return budgetStarted;
    }

    public void startBudget() {
        budgetStarted = true;
    }

    public void stopBudget() {
        budgetStarted = false;
    }

    public void addExpense(double expenseAmount) {
        expensesTotal += expenseAmount;
    }

    public String getExpensesTotalFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(expensesTotal);
    }

    public String getRemainingBudgetFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(getRemainingBudget());
    }

    public int getBudgetProgress() {
        if (budgetAmount == 0) {
            return 0;
        } else {
            double progress = expensesTotal / budgetAmount * 100;
            return (int) progress;
        }
    }
}
