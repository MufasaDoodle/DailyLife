package com.quantilink.dailylife.note;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.Note;

public class NoteActivity extends AppCompatActivity {
    private NoteViewModel viewModel;

    private Note currentNote;

    AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextView noteTitle = findViewById(R.id.noteTitle);
        TextView noteText = findViewById(R.id.noteText);

        Bundle bundle = getIntent().getExtras();
        currentNote = (Note) bundle.get("Note");

        noteTitle.setText(currentNote.getNoteTitle());
        noteText.setText(currentNote.getNoteText());
    }
}
