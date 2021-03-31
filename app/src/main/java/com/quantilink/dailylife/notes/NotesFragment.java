package com.quantilink.dailylife.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.Note;
import com.quantilink.dailylife.models.NotesAdapter;
import com.quantilink.dailylife.note.NoteActivity;

import java.util.ArrayList;

public class NotesFragment extends Fragment implements NotesAdapter.OnListItemClickListener{
    private NotesViewModel viewModel;
    RecyclerView notesList;
    NotesAdapter mNotesAdapter;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        root = inflater.inflate(R.layout.fragment_notes, container, false);

        notesList = root.findViewById(R.id.notesRV);
        notesList.hasFixedSize();
        notesList.setLayoutManager(new LinearLayoutManager(root.getContext()));

        //replace with retrieving data from database or webservice
        seed();

        notesList.setAdapter(mNotesAdapter);

        return root;
    }

    private void seed(){
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("title1", "hey bro"));
        notes.add(new Note("title2", "hey bro2"));
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

        mNotesAdapter = new NotesAdapter(notes, this);
    }

    @Override
    public void OnListItemClick(Note note) {
        Intent intent = new Intent(root.getContext(), NoteActivity.class);
        intent.putExtra("Note", note);
        startActivity(intent);
    }
}
