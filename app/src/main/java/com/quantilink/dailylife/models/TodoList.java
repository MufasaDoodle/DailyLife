package com.quantilink.dailylife.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

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

    public void addTodo(Todo todo){
        todos.add(todo);
    }

    public void setTodoListTitle(String todoListTitle) {
        this.todoListTitle = todoListTitle;
    }
}
