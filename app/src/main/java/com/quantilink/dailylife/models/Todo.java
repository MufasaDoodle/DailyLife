package com.quantilink.dailylife.models;

import java.io.Serializable;

public class Todo implements Serializable {
    private String todoText;
    private boolean isFinished = false;

    public Todo(String todoText) {
        this.todoText = todoText;
    }

    public String getTodoText() {
        return todoText;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void toggleBool(){
        isFinished = !isFinished;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
