package com.quantilink.dailylife.notes;

import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.NoteRepo;
import com.quantilink.dailylife.models.Note;

import java.util.ArrayList;

public class NotesViewModel extends ViewModel {
    private NoteRepo noteRepo;

    public NotesViewModel() {
        noteRepo = NoteRepo.getInstance();
    }

    public ArrayList<Note> getNotes(){
        return noteRepo.getNotes();
    }

    public void addNote(Note note) {
        noteRepo.addNote(note);
    }

    public void removeNote(Note note) {
        noteRepo.removeNote(note);
    }
    public void UpdateNote(int id, Note note){
        noteRepo.updateNote(id, note);
    }
}
