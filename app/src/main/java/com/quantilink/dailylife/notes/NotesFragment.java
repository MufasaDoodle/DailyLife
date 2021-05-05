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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(NotesViewModel.class);
        root = inflater.inflate(R.layout.fragment_notes, container, false);

        notesList = root.findViewById(R.id.notesRV);
        notesList.hasFixedSize();
        notesList.setLayoutManager(new LinearLayoutManager(root.getContext()));

        viewModel.getNotes().observe(getViewLifecycleOwner(), notes -> {
            mNotesAdapter = new NotesAdapter(notes, this);
            notesList.setAdapter(mNotesAdapter);
        });

        FloatingActionButton fab = root.findViewById(R.id.FABaddnote);

        fab.setOnClickListener(v -> {
            addNewNote();
        });

        return root;
    }

    private void addNewNote() {
        Note newNote = new Note("New Note", "Write your note here!");
        viewModel.addNote(newNote);
    }

    @Override
    public void OnListItemClick(Note note) {
        Intent intent = new Intent(root.getContext(), NoteActivity.class);
        intent.putExtra("Note", note);
        startActivity(intent);
    }

    @Override
    public void OnListItemDeleteClick(Note note) {
        viewModel.removeNote(note);
        mNotesAdapter.notifyDataSetChanged();
    }
}
