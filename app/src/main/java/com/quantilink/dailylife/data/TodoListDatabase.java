package com.quantilink.dailylife.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.quantilink.dailylife.models.TodoList;

@Database(entities = {TodoList.class}, version = 1)
public abstract class TodoListDatabase extends RoomDatabase {

    private static TodoListDatabase instance;
    public abstract TodoListDAO todoListDAO();

    public static synchronized TodoListDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), TodoListDatabase.class, "todolist_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }
}
