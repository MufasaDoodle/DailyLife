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

/**
 * Repository for accessing todolists local database
 */
public class TodoListRepo {
    private static TodoListRepo instance;
    private TodoListDAO todoListDAO;
    private final ExecutorService executorService;

    private LiveData<List<TodoList>> allTodoLists;

    private TodoListRepo(Application application) {
        TodoListDatabase database = TodoListDatabase.getInstance(application);
        todoListDAO = database.todoListDAO();
        allTodoLists = todoListDAO.getAllTodoLists();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static TodoListRepo getInstance(Application application) {
        if (instance == null) {
            instance = new TodoListRepo(application);
        }

        return instance;
    }

    public void addTodoList(String todolistTitle) {
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
        executorService.execute(() -> todoListDAO.delete(todoList));
    }

    public void updateTodoList(TodoList todoList) {
        executorService.execute(() -> todoListDAO.update(todoList));
    }

    public LiveData<List<TodoList>> getTodoLists() {
        return todoListDAO.getAllTodoLists();
    }

    public void deleteAllLists() {
        executorService.execute(() -> {
            todoListDAO.deleteAllTodoLists();
        });
    }
}
