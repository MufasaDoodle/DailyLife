package com.quantilink.dailylife.todolist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantilink.dailylife.R;

public class TodosFragment extends Fragment {

    private TodosViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(TodosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todos, container, false);

        return root;
    }
}