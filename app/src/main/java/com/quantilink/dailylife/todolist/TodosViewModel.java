package com.quantilink.dailylife.todolist;

import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.TodoListRepo;
import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;

public class TodosViewModel extends ViewModel {

    private TodoListRepo todoListRepo;

    public TodosViewModel() {
        todoListRepo = TodoListRepo.getInstance();
    }

    public ArrayList<TodoList> getTodoLists() {
        return todoListRepo.getTodoLists();
    }
}
