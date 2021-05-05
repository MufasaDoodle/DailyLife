package com.quantilink.dailylife.todos;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.TodoListRepo;
import com.quantilink.dailylife.models.Todo;
import com.quantilink.dailylife.models.TodoList;

public class TodoViewModel extends AndroidViewModel {
    private TodoList todoList;

    private TodoListRepo todoListRepo;

    public TodoViewModel(Application app) {
        super(app);
        todoListRepo = TodoListRepo.getInstance(app);
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

    public void removeTodoFromList(int index){
        todoList.getTodos().remove(index);
        updateTodoList();
    }

    public void setNewTitle(String name){
        todoList.setTodoListTitle(name);
    }
}
