package com.quantilink.dailylife.helpers;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quantilink.dailylife.models.Todo;

import java.lang.reflect.Type;
import java.util.List;

// from https://stackoverflow.com/questions/44580702/android-room-persistent-library-how-to-insert-class-that-has-a-list-object-fie
public class DataConverter {
    @TypeConverter
    public String fromTodos(List<Todo>todos){
        if(todos == null){
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Todo>>() {}.getType();
        String json = gson.toJson(todos, type);
        return json;
    }

    @TypeConverter
    public List<Todo> toTodos(String todoString){
        if(todoString == null){
            return (null);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<Todo>>() {}.getType();
        return gson.fromJson(todoString, type);
    }
}
