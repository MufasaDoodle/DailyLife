package com.quantilink.dailylife.data;

import androidx.lifecycle.LiveData;

import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;

public class TodoListRepo {
    private static TodoListRepo instance;
    private TodoListDAO todoListDAO;

    private TodoListRepo(){
        todoListDAO = TodoListDAO.getInstance();
        //webClient = WebClient.getInstance();
    }

    public static TodoListRepo getInstance(){
        if(instance == null){
            instance = new TodoListRepo();
        }

        return instance;
    }
    public void addTodoList(String todolistTitle){
        todoListDAO.addTodoList(todolistTitle);
    }

    public void deleteTodoList(int index){
        todoListDAO.deleteTodoList(index);
    }

    public void updateTodoList(TodoList todoList){
        todoListDAO.updateTodoList(todoList);
    }

    public void updateTodoListName(String oldName, String newName){
        todoListDAO.updateTodoListName(oldName, newName);
    }

    public int getIndex(TodoList todoList){
        return todoListDAO.getIndex(todoList);
    }

    public LiveData<ArrayList<TodoList>> getTodoLists(){
        return todoListDAO.getTodoLists();
    }
}
