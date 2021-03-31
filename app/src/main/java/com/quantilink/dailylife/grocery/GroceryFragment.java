package com.quantilink.dailylife.grocery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.quantilink.dailylife.R;

public class GroceryFragment extends Fragment {
    private GroceryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(GroceryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_grocery, container, false);

        return root;
    }
}