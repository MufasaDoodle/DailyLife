package com.quantilink.dailylife.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * An individual note. Saved in a room database
 */
@Entity(tableName = "note_table")
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
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

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
