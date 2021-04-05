package com.quantilink.dailylife.todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.NotesAdapter;
import com.quantilink.dailylife.models.TodoList;
import com.quantilink.dailylife.models.TodoListAdapter;
import com.quantilink.dailylife.note.NoteActivity;
import com.quantilink.dailylife.todos.TodoActivity;

import java.util.ArrayList;

public class TodosFragment extends Fragment implements TodoListAdapter.OnListItemClickListener {

    private TodosViewModel viewModel;

    RecyclerView todoLists;
    TodoListAdapter todoListAdapter;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(TodosViewModel.class);
        root = inflater.inflate(R.layout.fragment_todos, container, false);

        todoLists = root.findViewById(R.id.todosRV);
        todoLists.hasFixedSize();
        todoLists.setLayoutManager(new LinearLayoutManager(root.getContext()));

        viewModel.getTodoLists().observe(getViewLifecycleOwner(), todolists -> {
            todoListAdapter = new TodoListAdapter(todolists, this);
            todoLists.setAdapter(todoListAdapter);
        });

        FloatingActionButton fab = root.findViewById(R.id.FABadd);

        fab.setOnClickListener(v -> {
            addNewEmptyList();
        });

        return root;
    }

    @Override
    public void OnListItemClick(TodoList todoList) {
        Intent intent = new Intent(root.getContext(), TodoActivity.class);
        intent.putExtra("TodoList", todoList);
        startActivity(intent);
    }

    public void addNewEmptyList(){
        TodoList newList = new TodoList("", new ArrayList<>());
        Intent intent = new Intent(root.getContext(), TodoActivity.class);
        intent.putExtra("TodoList", newList);
        startActivity(intent);
    }

    void addNewTodoList(String todoListTitle){
        if(!todoListTitle.isEmpty()){
            viewModel.addTodoList(todoListTitle);
        }
    }
}