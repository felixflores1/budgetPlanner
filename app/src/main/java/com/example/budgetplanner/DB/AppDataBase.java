package com.example.budgetplanner.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budgetplanner.BudgetPlanner;
import com.example.budgetplanner.DB.BudgetPlannerDAO;
import com.example.budgetplanner.DB.Converters;
import com.example.budgetplanner.Expense;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BudgetPlanner.class, Expense.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "BudgetPlanner.db";
    public static final String BUDGET_TABLE = "budget_table";
    private static volatile AppDataBase instance;

    private static final Object LOCK = new Object();

    public abstract BudgetPlannerDAO budgetPlannerDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDataBase.class, DATABASE_NAME)
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
            });
        }
    };
}


