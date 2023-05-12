package com.example.budgetplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_list_item, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.titleTextView.setText(expense.getTitle());
        holder.amountTextView.setText(String.valueOf(expense.getAmount()));
        holder.dateTextView.setText(expense.getDate());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView amountTextView;
        TextView dateTextView;

        ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.expense_title_textview);
            amountTextView = itemView.findViewById(R.id.expense_amount_textview);
            dateTextView = itemView.findViewById(R.id.expense_date_textview);
        }
    }
}
