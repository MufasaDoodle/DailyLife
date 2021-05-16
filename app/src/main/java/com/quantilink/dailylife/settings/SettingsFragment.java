package com.quantilink.dailylife.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.quantilink.dailylife.R;
import com.quantilink.dailylife.models.GroceryList;
import com.quantilink.dailylife.models.Note;
import com.quantilink.dailylife.models.TodoList;
import com.quantilink.dailylife.signin.SignInActivity;

import java.util.List;

public class SettingsFragment extends Fragment {
    private SettingsViewModel viewModel;

    List<GroceryList> groceryLists;
    List<Note> notes;
    List<TodoList> todoLists;

    Button logInBtn;
    Button saveDataBtn;
    TextView text;
    Button restoreDataBtn;
    Button wipeDataBtn;

    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(SettingsViewModel.class);
        viewModel.setId();
        checkIfSignedIn();
        root = inflater.inflate(R.layout.fragment_settings, container, false);

        viewModel.getGroceries().observe(getViewLifecycleOwner(), groceryLists1 -> {
            groceryLists = groceryLists1;
        });

        viewModel.getNotes().observe(getViewLifecycleOwner(), notes1 -> {
            notes = notes1;
        });

        viewModel.getTodoLists().observe(getViewLifecycleOwner(), todoLists1 -> {
            todoLists = todoLists1;
        });

        logInBtn = root.findViewById(R.id.logInBtn);
        logInBtn.setOnClickListener( v-> {
            logInOrOut();
        });

        text = root.findViewById(R.id.statusText);
        viewModel.statusString.observe(getViewLifecycleOwner(), text::setText);

        saveDataBtn = root.findViewById(R.id.saveDataBtn);

        saveDataBtn.setOnClickListener(v -> {
            saveDataToCloud();
        });

        restoreDataBtn =  root.findViewById(R.id.restoreDataBtn);

        restoreDataBtn.setOnClickListener(v -> {
            restoreDataFromCloud();
        });

        wipeDataBtn = root.findViewById(R.id.wipeDataBtn);

        wipeDataBtn.setOnClickListener(v -> {
            wipeLocalStorage();
        });

        return root;
    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if(user != null){
                //user is logged in
                logInBtn.setText("Sign out");
                saveDataBtn.setEnabled(true);
                restoreDataBtn.setEnabled(true);
                String message = "Welcome " + user.getDisplayName();
                text.setText(message);
            }
            else {
                //user is not logged in
                logInBtn.setText("Sign In");
                text.setText("Log in to enable cloud functionality");
                saveDataBtn.setEnabled(false);
                restoreDataBtn.setEnabled(false);
            }
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(getContext(), SignInActivity.class));
        getActivity().finish();
    }

    public void logInOrOut(){
        if(viewModel.getCurrentUser().getValue() != null){
            viewModel.signOut();
            viewModel.setId();
        }
        else {
            startLoginActivity();
            viewModel.setId();
        }
    }

    public void saveDataToCloud(){
        viewModel.saveDataToCloud(groceryLists, notes, todoLists);
    }

    public void restoreDataFromCloud(){
        viewModel.restoreDataFromCloud();
    }

    public void wipeLocalStorage(){
        viewModel.wipeLocalStorage();
        Toast.makeText(getContext(), "All local data wiped", Toast.LENGTH_SHORT).show();
    }
}
