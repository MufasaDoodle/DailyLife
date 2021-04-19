package com.quantilink.dailylife.note;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.Note;

public class NoteActivity extends AppCompatActivity {
    private NoteViewModel viewModel;

    EditText noteTitle;
    TextView noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);

        noteTitle = findViewById(R.id.noteTitleET);
        noteText = findViewById(R.id.noteText);
        ImageButton button = findViewById(R.id.noteBackBtn);

        button.setOnClickListener(v -> {
            saveAndClose();
        });

        Bundle bundle = getIntent().getExtras();
        viewModel.setCurrentNote((Note) bundle.get("Note"));

        noteTitle.setText(viewModel.getCurrentNote().getNoteTitle());
        noteText.setText(viewModel.getCurrentNote().getNoteText());

        //updates the note when editing occurs in the editText
        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setNewTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });

        //updates the note when editing occurs in the editText
        noteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setNewNoteText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });
    }

    void saveAndClose() {
        viewModel.updateNote();
        Toast.makeText(this, "Saved note", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        saveAndClose();
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        viewModel.updateNote();
        Toast.makeText(this, "Saved note", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
}
