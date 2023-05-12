package com.example.budgetplanner;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "expenses_table",
        foreignKeys = @ForeignKey(entity = BudgetPlanner.class,
                parentColumns = "id",
                childColumns = "budget_id",
                onDelete = ForeignKey.CASCADE))
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "budget_id")
    private int budgetId;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "date")
    private String date;

    public Expense(int budgetId, String category, double amount, String title, String date) {
        this.budgetId = budgetId;
        this.category = category;
        this.amount = amount;
        this.title = title;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
