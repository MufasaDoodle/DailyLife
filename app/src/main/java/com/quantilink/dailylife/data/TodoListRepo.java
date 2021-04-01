package com.quantilink.dailylife.data;

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

    public ArrayList<TodoList> getTodoLists(){
        return todoListDAO.getTodoLists();
    }
}
