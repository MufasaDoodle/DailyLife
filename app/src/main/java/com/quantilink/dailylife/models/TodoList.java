package com.quantilink.dailylife.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.quantilink.dailylife.helpers.DataConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for holding multiple todos
 */
@Entity(tableName = "todolist_table")
public class TodoList implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String todoListTitle;

    @TypeConverters(DataConverter.class)
    private List<Todo> todos;


    public TodoList(String todoListTitle, List<Todo> todos) {
        this.todoListTitle = todoListTitle;
        this.todos = todos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    public String getTodoListTitle() {
        return todoListTitle;
    }

    @TypeConverters(DataConverter.class)
    public List<Todo> getTodos() {
        return todos;
    }

    public void addTodo(Todo todo){
        todos.add(todo);
    }

    public void setTodoListTitle(String todoListTitle) {
        this.todoListTitle = todoListTitle;
    }
}
