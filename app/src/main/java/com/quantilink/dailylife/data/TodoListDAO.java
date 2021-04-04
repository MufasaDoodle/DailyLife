package com.quantilink.dailylife.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.quantilink.dailylife.models.Todo;
import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;

public class TodoListDAO {
    private MutableLiveData<ArrayList<TodoList>> todoLists;
    private static TodoListDAO instance;

    private TodoListDAO() {
        todoLists = new MutableLiveData<>();
        ArrayList<TodoList> temp = new ArrayList<>();
        todoLists.setValue(temp);

        if (todoLists.getValue().isEmpty()) {
            seed();
        }
    }

    public static TodoListDAO getInstance() {
        if (instance == null) {
            instance = new TodoListDAO();
        }
        return instance;
    }

    public void addTodoList(String todolistTitle){
        ArrayList<Todo> temptodos = new ArrayList<>();
        TodoList temptodolist = new TodoList(todolistTitle, temptodos);
        ArrayList<TodoList> temp = todoLists.getValue();
        temp.add(temptodolist);
        todoLists.postValue(temp);
    }

    public void deleteTodoList(int index){
        ArrayList<TodoList> temp = todoLists.getValue();
        temp.remove(index);
        todoLists.postValue(temp);
    }

    public LiveData<ArrayList<TodoList>> getTodoLists(){
        return todoLists;
    }

    public void updateTodoList(TodoList todoList){
        int index = -1;
        ArrayList<TodoList> temp = todoLists.getValue();
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getTodoListTitle().equals(todoList.getTodoListTitle())){
                index = i;
            }
        }

        temp.set(index, todoList);
        todoLists.postValue(temp);
    }

    public int getIndex(TodoList todoList){
        return todoLists.getValue().indexOf(todoList);
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

        ArrayList<TodoList> temp = new ArrayList<>();

        temp.add(todoList1);
        temp.add(todoList2);

        todoLists.postValue(temp);
    }
}
