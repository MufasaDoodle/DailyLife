package com.quantilink.dailylife.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.quantilink.dailylife.models.Note;
import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Repository for accessing notes local database
 */
public class NoteRepo {

    private static NoteRepo instance;
    private NoteDAO noteDAO;
    private final ExecutorService executorService;

    private LiveData<List<Note>> allNotes;

    private NoteRepo(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAllNotes();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static NoteRepo getInstance(Application application){
        if(instance == null){
            instance = new NoteRepo(application);
        }

        return instance;
    }

    public LiveData<List<Note>> getNotes(){
        return noteDAO.getAllNotes();
    }

    public void addNote(Note note) {
        if(note == null){
            return;
        }

        executorService.execute(() -> {
            noteDAO.insert(note);
        });
    }

    public void removeNote(Note note) {
        if(note == null){
            return;
        }

        executorService.execute(() -> {
            noteDAO.delete(note);
        });
    }

    public void updateNote(Note note){
        if(note == null){
            return;
        }

        executorService.execute(() -> {
            noteDAO.update(note);
        });
    }

    public void deleteAllNotes(){
        executorService.execute(() -> {
            noteDAO.deleteAllNotes();
        });
    }
}
