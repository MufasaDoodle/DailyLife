package com.quantilink.dailylife.todos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.Note;
import com.quantilink.dailylife.models.TodoList;
import com.quantilink.dailylife.note.NoteViewModel;

public class TodoActivity extends AppCompatActivity {
    private TodoViewModel viewModel;

    private TodoList todoList;

    AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        //init text fields or whatever we do with each todo

        Bundle bundle = getIntent().getExtras();
        todoList = (TodoList) bundle.get("TodoList");

        //set text fields to whatever values todo
    }
}
