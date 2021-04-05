package com.quantilink.dailylife.todos;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.TodoListRepo;
import com.quantilink.dailylife.models.Todo;
import com.quantilink.dailylife.models.TodoList;

public class TodoViewModel extends ViewModel {
    private TodoList todoList;

    private TodoListRepo todoListRepo;

    private String oldTitle = "";

    private String tempNewTitle = "";

    public TodoViewModel() {
        todoListRepo = TodoListRepo.getInstance();
    }

    public void setCurrentTodoList(TodoList todoList){
        this.todoList = todoList;

        if(todoList.getTodoListTitle().equals("")){
            createNewTodoList();
        }
    }

    public void updateTodoList(){
        if(todoList.getTodoListTitle().isEmpty() && todoList.getTodos().isEmpty()){
            return;
        }

        todoListRepo.updateTodoList(todoList);
    }

    public TodoList getTodoList(){
        return todoList;
    }

    public void addNewTodo(String todoText){
        Todo todo = new Todo(todoText);
        todoList.addTodo(todo);
    }

    public void updateTodoListName(){
        todoList.setTodoListTitle(tempNewTitle);
        todoListRepo.updateTodoListName(oldTitle, tempNewTitle);
    }

    public void createNewTodoList(){
        Log.e("ERROR", "empty");
        todoListRepo.addTodoList(""); //the title is gonna be an empty string to show it's a new list
    }

    public void setOldTitle(){
        oldTitle = todoList.getTodoListTitle();
    }

    public void setNewTitle(String name){
        tempNewTitle = name;
    }
}
