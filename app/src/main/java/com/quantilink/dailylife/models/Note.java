package com.quantilink.dailylife.models;

import java.io.Serializable;

public class Note implements Serializable {
    private String noteTitle;
    private String noteText;

    public Note(String noteTitle, String noteText) {
        this.noteTitle = noteTitle;
        this.noteText = noteText;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteText() {
        return noteText;
    }
}
