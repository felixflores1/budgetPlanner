package com.example.budgetplanner.DB;

import androidx.room.TypeConverter;

import com.example.budgetplanner.Expense;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Converters {

    @TypeConverter
    public static List<Expense> fromJson(String value) {
        List<Expense> expenses = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(value);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String category = jsonObject.getString("category");
                double amount = jsonObject.getDouble("amount");
                String date = jsonObject.getString("date");
                String description = jsonObject.getString("description");
                expenses.add(new Expense(expenses.size()+1, category, amount, date, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @TypeConverter
    public static String toJson(List<Expense> expenses) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Expense expense : expenses) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("category", expense.getCategory());
                jsonObject.put("amount", expense.getAmount());
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray.toString();
    }
}
