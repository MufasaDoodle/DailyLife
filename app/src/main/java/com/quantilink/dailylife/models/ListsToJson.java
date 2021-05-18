package com.quantilink.dailylife.models;

import java.util.List;

/**
 * Used to serialize json data for the datapackage class. This solution was an artifact of continual issues with Retrofit
 */
public class ListsToJson {
    List<GroceryList> groceryLists;
    List<Note> notes;
    List<TodoList> todoLists;

    public ListsToJson(List<GroceryList> groceryLists, List<Note> notes, List<TodoList> todoLists) {
        this.groceryLists = groceryLists;
        this.notes = notes;
        this.todoLists = todoLists;
    }

    public List<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }
}
