package com.quantilink.dailylife.data;

import androidx.lifecycle.MutableLiveData;

import com.quantilink.dailylife.models.Note;

import java.util.ArrayList;

public class NoteDAO {
    private ArrayList<Note> notes;
    private static NoteDAO instance;

    private NoteDAO() {
        notes = new ArrayList<>();

        if (notes.isEmpty()) {
            seed();
        }
    }

    public static NoteDAO getInstance() {
        if (instance == null) {
            instance = new NoteDAO();
        }
        return instance;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        if (note != null) {
            notes.add(note);
        }
    }

    public void removeNote(Note note) {
        if (note == null) {
            return;
        }

        notes.remove(note);
    }

    public void updateNote(int id, Note note){
        if(id < 0){
            return;
        }

        if(notes.get(id) != null){
            notes.set(id, note);
        }
    }

    private void seed() {
        notes.add(new Note("title1", "hey bro"));
        notes.add(new Note( "title2", "hey bro2"));
        notes.add(new Note("title3", "hey bro3"));
        notes.add(new Note("title4", "hey bro4"));
        notes.add(new Note("title5", "hey bro5"));
        notes.add(new Note("title6", "hey bro6"));
        notes.add(new Note("title7", "hey bro7"));
        notes.add(new Note("title8", "hey bro8"));
        notes.add(new Note("title9", "hey bro9"));
        notes.add(new Note("title10", "hey bro10"));
        notes.add(new Note("title11", "hey bro11"));
        notes.add(new Note("title12", "hey bro12"));
        notes.add(new Note("title13", "hey bro13"));
        notes.add(new Note("title14", "hey bro14"));
        notes.add(new Note("title15", "hey bro15"));
        notes.add(new Note("title16", "hey bro16"));
        notes.add(new Note("title17", "hey bro17"));
    }
}
