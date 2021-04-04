package com.quantilink.dailylife.todos;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.TodoListRepo;
import com.quantilink.dailylife.models.Todo;
import com.quantilink.dailylife.models.TodoList;

public class TodoViewModel extends ViewModel {
    private TodoList todoList;

    private TodoListRepo todoListRepo;

    public TodoViewModel() {
        todoListRepo = TodoListRepo.getInstance();
    }

    public void setCurrentTodoList(TodoList todoList){
        this.todoList = todoList;
    }

    public void updateTodoList(){
        todoListRepo.updateTodoList(todoList);
    }

    public TodoList getTodoList(){
        return todoList;
    }

    public void addNewTodo(String todoText){
        Todo todo = new Todo(todoText);
        todoList.addTodo(todo);
    }
}
