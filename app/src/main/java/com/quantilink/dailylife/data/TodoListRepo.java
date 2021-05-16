package com.quantilink.dailylife.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class TodoListRepo {
    private static TodoListRepo instance;
    //private TodoListTestDAO todoListTestDAO;
    private TodoListDAO todoListDAO;
    private final ExecutorService executorService;

    private LiveData<List<TodoList>> allTodoLists;

    private TodoListRepo(Application application) {
        TodoListDatabase database = TodoListDatabase.getInstance(application);
        todoListDAO = database.todoListDAO();
        allTodoLists = todoListDAO.getAllTodoLists();
        executorService = Executors.newFixedThreadPool(2);
        //todoListTestDAO = TodoListTestDAO.getInstance();
        //webClient = WebClient.getInstance();
    }

    public static TodoListRepo getInstance(Application application) {
        if (instance == null) {
            instance = new TodoListRepo(application);
        }

        return instance;
    }

    public void addTodoList(String todolistTitle) {
        //todoListTestDAO.addTodoList(todolistTitle);
        TodoList toAdd = new TodoList(todolistTitle, new ArrayList<>());
        executorService.execute(() -> {
            todoListDAO.insert(toAdd);
        });
    }

    public void addTodoList(TodoList todoList){
        executorService.execute(() -> {
            todoListDAO.insert(todoList);
        });
    }

    public void deleteTodoList(TodoList todoList) {
        //todoListTestDAO.deleteTodoList(index);
        executorService.execute(() -> todoListDAO.delete(todoList));
    }

    public void updateTodoList(TodoList todoList) {
        //todoListTestDAO.updateTodoList(todoList);
        executorService.execute(() -> todoListDAO.update(todoList));
    }

    public void updateTodoListName(String oldName, String newName) {
        //todoListTestDAO.updateTodoListName(oldName, newName);
        //todo implement a proper query in the interface
    }

    public int getIndex(TodoList todoList) {
        //return todoListTestDAO.getIndex(todoList);
        //todo implement proper query in the interface

        return -1;
    }

    public LiveData<List<TodoList>> getTodoLists() {
        //return todoListTestDAO.getTodoLists();
        return todoListDAO.getAllTodoLists();
    }

    public TodoList getLatestTodoList() {
        executorService.execute(() -> {
            todoListDAO.getLatestTodoList();
        });
        return null; //TODO
    }

    public void deleteAllLists() {
        executorService.execute(() -> {
            todoListDAO.deleteAllTodoLists();
        });
    }
}
