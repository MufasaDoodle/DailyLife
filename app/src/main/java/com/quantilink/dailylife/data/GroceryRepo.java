package com.quantilink.dailylife.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.quantilink.dailylife.models.Grocery;
import com.quantilink.dailylife.models.GroceryList;
import com.quantilink.dailylife.models.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GroceryRepo {
    private static GroceryRepo instance;
    private GroceryDAO groceryDAO;
    private final ExecutorService executorService;

    private GroceryRepo(Application application){
        GroceryDatabase database = GroceryDatabase.getInstance(application);
        groceryDAO = database.groceryDAO();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static GroceryRepo getInstance(Application application){
        if(instance == null){
            instance = new GroceryRepo(application);
        }

        return instance;
    }

    public LiveData<List<GroceryList>> getGroceries(){
        return groceryDAO.getAllGroceries();
    }

    public void addGrocery(GroceryList groceryList) {
        if(groceryList == null){
            return;
        }

        executorService.execute(() -> {
            groceryDAO.insert(groceryList);
        });
    }

    public void removeGrocery(GroceryList groceryList) {
        if(groceryList == null){
            return;
        }

        executorService.execute(() -> {
            groceryDAO.delete(groceryList);
        });
    }

    public void updateGrocery(GroceryList groceryList){
        if(groceryList == null){
            return;
        }

        executorService.execute(() -> {
            groceryDAO.update(groceryList);
        });
    }
}
