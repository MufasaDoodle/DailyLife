package com.quantilink.dailylife.grocery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.quantilink.dailylife.data.GroceryRepo;
import com.quantilink.dailylife.models.Grocery;
import com.quantilink.dailylife.models.GroceryList;


public class GroceryViewModel extends AndroidViewModel {

    private GroceryRepo groceryRepo;

    private GroceryList groceryList;

    public GroceryViewModel(@NonNull Application application) {
        super(application);
        groceryRepo = GroceryRepo.getInstance(application);
    }

    public void setCurrentGroceryList(GroceryList list){
        groceryList = list;
    }

    public void updateGroceryList(){
        groceryRepo.updateGrocery(groceryList);
    }

    public GroceryList getGroceryList(){
        return groceryList;
    }

    public void addGrocery(Grocery grocery) {
        groceryList.addGrocery(grocery);
    }

    public void removeGroceryFromList(int index){
        groceryList.getGroceries().remove(index);
        updateGroceryList();
    }

    public void setNewTitle(String title){
        groceryList.setGroceryListTitle(title);
    }
}
