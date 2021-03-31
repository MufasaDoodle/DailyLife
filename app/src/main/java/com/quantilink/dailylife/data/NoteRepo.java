package com.quantilink.dailylife.data;

import com.quantilink.dailylife.models.Note;

import java.util.ArrayList;

public class NoteRepo {

    private static NoteRepo instance;
    private NoteDAO noteDAO;

    private NoteRepo(){
        noteDAO = NoteDAO.getInstance();
        //webClient = WebClient.getInstance();
    }

    public static NoteRepo getInstance(){
        if(instance == null){
            instance = new NoteRepo();
        }

        return instance;
    }

    public ArrayList<Note> getNotes(){
        return noteDAO.getNotes();
    }

    public void addNote(Note note) {
        noteDAO.addNote(note);
    }

    public void removeNote(Note note) {
        noteDAO.removeNote(note);
    }
    public void updateNote(int id, Note note){
        noteDAO.updateNote(id, note);
    }
}
