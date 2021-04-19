package com.quantilink.dailylife.note;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.NoteRepo;
import com.quantilink.dailylife.models.Note;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepo noteRepo;

    private Note note;

    public NoteViewModel(Application app) {
        super(app);
        noteRepo = NoteRepo.getInstance(app);
    }

    public void removeNote(Note note) {
        noteRepo.removeNote(note);
    }

    public void updateNote(){
        noteRepo.updateNote(note);
    }

    public void setCurrentNote(Note note){
        this.note = note;
    }

    public Note getCurrentNote(){
        return note;
    }

    public void setNewTitle(String title){
        note.setNoteTitle(title);
    }

    public void setNewNoteText(String text){
        note.setNoteText(text);
    }
}
