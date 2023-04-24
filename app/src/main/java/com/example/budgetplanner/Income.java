package com.example.budgetplanner;

import androidx.room.PrimaryKey;

public class Income {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String source;
    private double amount;

    public Income(String source, double amount) {
        this.source = source;
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public double getAmount() {
        return amount;
    }
}
