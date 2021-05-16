package com.quantilink.dailylife.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.quantilink.dailylife.models.Grocery;
import com.quantilink.dailylife.models.GroceryList;
import com.quantilink.dailylife.models.Note;

import java.util.List;

@Dao
public interface GroceryDAO {
    @Insert
    void insert(GroceryList groceryList);

    @Update
    void update(GroceryList groceryList);

    @Delete
    void delete(GroceryList groceryList);

    @Query("SELECT * FROM grocery_table")
    LiveData<List<GroceryList>> getAllGroceries();

    @Query("SELECT * FROM grocery_table")
    List<GroceryList> getAllGroceriesList();

    @Query("DELETE FROM grocery_table")
    void deleteAllGroceryLists();

    @Query("SELECT * FROM grocery_table WHERE id=:index")
    GroceryList getGroceryList(long index);
}
