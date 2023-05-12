package com.example.budgetplanner;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetplanner.Expense;
import com.example.budgetplanner.R;

import java.util.ArrayList;

public class BudgetActivity extends AppCompatActivity {

    private TextView budgetAmountLabel;
    private EditText budgetAmountEditText;
    private TextView budgetCategoryLabel;
    private Spinner budgetCategorySpinner;
    private TextView budgetProgressLabel;
    private ProgressBar budgetProgressBar;
    private TextView budgetStatusLabel;
    private TextView budgetStatusTextView;
    private Button startBudgetButton;
    private RecyclerView expenseListRecyclerView;
    private Button addExpenseButton;

    private ArrayList<Expense> expenseList;
    private ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_activity);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        budgetAmountLabel = findViewById(R.id.budget_amount_label);
        budgetAmountEditText = findViewById(R.id.budget_amount_edittext);
        budgetCategoryLabel = findViewById(R.id.budget_category_label);
        budgetCategorySpinner = findViewById(R.id.budget_category_spinner);
        budgetProgressLabel = findViewById(R.id.budget_progress_label);
        budgetProgressBar = findViewById(R.id.budget_progress_bar);
        budgetStatusLabel = findViewById(R.id.budget_status_label);
        budgetStatusTextView = findViewById(R.id.budget_status_textview);
        startBudgetButton = findViewById(R.id.start_budget_button);
        expenseListRecyclerView = findViewById(R.id.expense_list_recyclerview);
        addExpenseButton = findViewById(R.id.add_expense_button);

        // Set up budget category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.budget_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        budgetCategorySpinner.setAdapter(adapter);

        // Set up expense list RecyclerView
        expenseList = new ArrayList<>();
        expenseAdapter = new ExpenseAdapter(expenseList);
        expenseListRecyclerView.setAdapter(expenseAdapter);
        expenseListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up add expense button click listener
        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch AddExpenseActivity to add a new expense
            }
        });

        // Set up budget category spinner item click listener
        budgetCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update budget category based on selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Set up start budget button click listener
        startBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start budget and update budget status
            }
        });
    }
}
