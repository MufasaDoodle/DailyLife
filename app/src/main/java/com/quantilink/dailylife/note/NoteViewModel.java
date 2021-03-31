package com.quantilink.dailylife.note;

import androidx.lifecycle.ViewModel;

import com.quantilink.dailylife.data.NoteRepo;
import com.quantilink.dailylife.models.Note;

public class NoteViewModel extends ViewModel {
    private NoteRepo noteRepo;

    public NoteViewModel() {
        noteRepo = NoteRepo.getInstance();
    }

    public void removeNote(Note note) {
        noteRepo.removeNote(note);
    }
    public void UpdateNote(int id, Note note){
        noteRepo.updateNote(id, note);
    }
}
