package com.quantilink.dailylife.todolist;

import android.content.Context;
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

/**
 * Fragment to display all todos
 */
public class TodosFragment extends Fragment implements TodoListAdapter.OnListItemClickListener {

    private TodosViewModel viewModel;

    RecyclerView todoLists;
    TodoListAdapter todoListAdapter;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(TodosViewModel.class);
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

    @Override
    public void OnListItemDelete(TodoList todoList) {
        viewModel.deleteTodoList(todoList);
        todoListAdapter.notifyDataSetChanged();
    }

    public void addNewEmptyList(){
        addNewTodoList("New List");
    }

    void addNewTodoList(String todoListTitle){
        if(!todoListTitle.isEmpty()){
            viewModel.addTodoList(todoListTitle);
        }
    }
}