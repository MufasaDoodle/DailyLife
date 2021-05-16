package com.quantilink.dailylife.models;

import java.io.Serializable;
import java.util.List;

public class DataPackage implements Serializable {
    int id;
    List<GroceryList> groceryLists;
    List<Note> notes;
    List<TodoList> todoLists;

    public DataPackage(int id, List<GroceryList> groceryLists, List<Note> notes, List<TodoList> todoLists) {
        this.id = id;
        this.groceryLists = groceryLists;
        this.notes = notes;
        this.todoLists = todoLists;
    }

    @Override
    public String toString() {
        return "DataPackage{" +
                "id=" + id +
                ", groceryLists=" + groceryLists +
                ", notes=" + notes +
                ", todoLists=" + todoLists +
                '}';
    }
}
