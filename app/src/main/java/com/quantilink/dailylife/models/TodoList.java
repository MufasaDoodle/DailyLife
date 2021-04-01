package com.quantilink.dailylife.models;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoList implements Serializable {
    private String todoListTitle;
    private ArrayList<Todo> todos;

    public TodoList(String todoListTitle, ArrayList<Todo> todos) {
        this.todoListTitle = todoListTitle;
        this.todos = todos;
    }

    public String getTodoListTitle() {
        return todoListTitle;
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
