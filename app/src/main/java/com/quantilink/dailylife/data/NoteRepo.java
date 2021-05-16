package com.quantilink.dailylife.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.quantilink.dailylife.models.Note;
import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepo {

    private static NoteRepo instance;
    //private NoteTestDAO noteTestDAO;
    private NoteDAO noteDAO;
    private final ExecutorService executorService;

    private LiveData<List<Note>> allNotes;

    private NoteRepo(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAllNotes();
        executorService = Executors.newFixedThreadPool(2);
        //noteTestDAO = NoteTestDAO.getInstance();
        //webClient = WebClient.getInstance();
    }

    public static NoteRepo getInstance(Application application){
        if(instance == null){
            instance = new NoteRepo(application);
        }

        return instance;
    }

    public LiveData<List<Note>> getNotes(){
        return noteDAO.getAllNotes();
        //return noteTestDAO.getNotes();
    }

    public void addNote(Note note) {
        if(note == null){
            return;
        }

        executorService.execute(() -> {
            noteDAO.insert(note);
        });

        //noteTestDAO.addNote(note);
    }

    public void removeNote(Note note) {
        if(note == null){
            return;
        }

        executorService.execute(() -> {
            noteDAO.delete(note);
        });

        //noteTestDAO.removeNote(note);
    }

    public void updateNote(Note note){
        if(note == null){
            return;
        }

        executorService.execute(() -> {
            noteDAO.update(note);
        });

        //noteTestDAO.updateNote(id, note);
    }

    public void deleteAllNotes(){
        executorService.execute(() -> {
            noteDAO.deleteAllNotes();
        });
    }
}
