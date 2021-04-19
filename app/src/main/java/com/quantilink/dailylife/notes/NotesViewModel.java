package com.quantilink.dailylife.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.NoteRepo;
import com.quantilink.dailylife.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    private NoteRepo noteRepo;

    public NotesViewModel(Application app) {
        super(app);
        noteRepo = NoteRepo.getInstance(app);
    }

    public LiveData<List<Note>> getNotes(){
        return noteRepo.getNotes();
    }

    public void addNote(Note note) {
        noteRepo.addNote(note);
    }

    public void removeNote(Note note) {
        noteRepo.removeNote(note);
    }
    public void UpdateNote(Note note){
        noteRepo.updateNote(note);
    }
}
