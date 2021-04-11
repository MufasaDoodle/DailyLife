package com.quantilink.dailylife.todolist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.TodoListRepo;
import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;
import java.util.List;

public class TodosViewModel extends AndroidViewModel {



    private TodoListRepo todoListRepo;


    public TodosViewModel(Application app) {
        super(app);
        todoListRepo = TodoListRepo.getInstance(app);
    }

    public LiveData<List<TodoList>> getTodoLists() {
        return todoListRepo.getTodoLists();
    }

    public void addTodoList(String todolistTitle){
        todoListRepo.addTodoList(todolistTitle);
    }

    public void deleteTodoList(int index){
        //todoListRepo.deleteTodoList(index);
        //todo
    }

    public void deleteAllLists() {
        todoListRepo.deleteAllLists();
    }
}
