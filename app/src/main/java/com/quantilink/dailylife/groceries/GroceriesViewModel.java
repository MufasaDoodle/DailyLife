package com.quantilink.dailylife.groceries;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.quantilink.dailylife.data.GroceryRepo;
import com.quantilink.dailylife.models.GroceryList;
import com.quantilink.dailylife.models.TodoList;

import java.util.ArrayList;
import java.util.List;

public class GroceriesViewModel extends AndroidViewModel {

    private GroceryRepo repo;

    public GroceriesViewModel(@NonNull Application application) {
        super(application);
        repo = GroceryRepo.getInstance(application);
    }

    public LiveData<List<GroceryList>> getGroceryLists() {
        return repo.getGroceries();
    }

    public void addGroceryList(String groceryListTitle){
        GroceryList toAdd = new GroceryList(groceryListTitle, new ArrayList<>());
        repo.addGrocery(toAdd);
    }

    public void deleteTodoList(GroceryList groceryList){
        repo.removeGrocery(groceryList);
    }

    public void deleteAllLists() {
        //todo
    }
}
