package com.quantilink.dailylife.data;

import com.quantilink.dailylife.models.Todo;
import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;

public class TodoListDAO {
    private ArrayList<TodoList> todoLists;
    private static TodoListDAO instance;

    private TodoListDAO() {
        todoLists = new ArrayList<>();

        if (todoLists.isEmpty()) {
            seed();
        }
    }

    public static TodoListDAO getInstance() {
        if (instance == null) {
            instance = new TodoListDAO();
        }
        return instance;
    }

    public ArrayList<TodoList> getTodoLists(){
        return todoLists;
    }

    private void seed() {
        ArrayList<Todo> todos1 = new ArrayList<>();
        ArrayList<Todo> todos2 = new ArrayList<>();
        todos1.add(new Todo("todo1"));
        todos1.add(new Todo("todo2"));
        todos1.add(new Todo("todo3"));

        todos2.add(new Todo("todo4"));
        todos2.add(new Todo("todo5"));
        todos2.add(new Todo("todo6"));

        TodoList todoList1 = new TodoList("title1", todos1);
        TodoList todoList2 = new TodoList("title2", todos2);

        todoLists.add(todoList1);
        todoLists.add(todoList2);
    }
}
