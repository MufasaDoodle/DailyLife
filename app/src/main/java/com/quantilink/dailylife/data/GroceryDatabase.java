package com.quantilink.dailylife.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.quantilink.dailylife.models.GroceryList;

/**
 * Room database instance for groceries
 */
@Database(entities = {GroceryList.class}, version = 2)
public abstract class GroceryDatabase extends RoomDatabase {
    private static GroceryDatabase instance;
    public abstract GroceryDAO groceryDAO();

    public static synchronized GroceryDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), GroceryDatabase.class, "grocery_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }
}
