package com.example.budgetplanner.DB;

import android.content.Context;
import android.service.autofill.UserData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budgetplanner.BudgetPlanner;
import com.example.budgetplanner.DB.BudgetPlannerDAO;
import com.example.budgetplanner.DB.Converters;
import com.example.budgetplanner.Expense;


@Database(entities = {BudgetPlanner.class, Expense.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {


    private static final String DATABASE_NAME = "BudgetPlanner.db";
    private static volatile AppDataBase instance;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDataBase create(final Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration() // Add this line for destructive migration
                .build();
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Perform any necessary migration steps here.
        }
    };

    public abstract BudgetPlannerDAO budgetPlannerDao();
}


