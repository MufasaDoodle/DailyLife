package com.quantilink.dailylife.groceries;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quantilink.dailylife.R;
import com.quantilink.dailylife.grocery.GroceryActivity;
import com.quantilink.dailylife.models.GroceryList;
import com.quantilink.dailylife.models.GroceryListAdapter;
import com.quantilink.dailylife.todos.TodoActivity;

public class GroceriesFragment extends Fragment implements GroceryListAdapter.OnListItemClickListener {

    private GroceriesViewModel viewModel;

    RecyclerView groceryLists;
    GroceryListAdapter groceryListAdapter;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(GroceriesViewModel.class);
        root = inflater.inflate(R.layout.fragment_grocery, container, false);

        groceryLists = root.findViewById(R.id.groceryListRV);
        groceryLists.hasFixedSize();
        groceryLists.setLayoutManager(new LinearLayoutManager(root.getContext()));

        viewModel.getGroceryLists().observe(getViewLifecycleOwner(), groceryLists -> {
            groceryListAdapter = new GroceryListAdapter(groceryLists, this);
            this.groceryLists.setAdapter(groceryListAdapter);
        });

        FloatingActionButton fab = root.findViewById(R.id.FABaddgroceryList);

        fab.setOnClickListener(v -> {
            addNewEmptyList();
        });

        return root;
    }

    @Override
    public void OnListItemClick(GroceryList groceryList) {
        Intent intent = new Intent(root.getContext(), GroceryActivity.class);
        intent.putExtra("GroceryList", groceryList);
        startActivity(intent);
    }

    @Override
    public void OnListItemDelete(GroceryList groceryList) {
        viewModel.deleteTodoList(groceryList);
        groceryListAdapter.notifyDataSetChanged();
    }

    public void addNewEmptyList(){
        addNewGroceryList("New List");
    }

    void addNewGroceryList(String groceryListTitle){
        if(!groceryListTitle.isEmpty()){
            viewModel.addGroceryList(groceryListTitle);
        }
    }
}
