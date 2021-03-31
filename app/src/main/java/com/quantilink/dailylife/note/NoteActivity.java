package com.quantilink.dailylife.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.Note;

public class NoteActivity extends AppCompatActivity {
    private NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextView textView = findViewById(R.id.noteID);
        Bundle bundle = getIntent().getExtras();
        Note note = (Note) bundle.get("Note");
        textView.setText(note.getNoteText());
    }
}
