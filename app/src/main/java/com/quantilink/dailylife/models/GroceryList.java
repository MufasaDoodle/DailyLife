package com.quantilink.dailylife.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.quantilink.dailylife.helpers.DataConverter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "grocery_table")
public class GroceryList implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String groceryListTitle;

    @TypeConverters(DataConverter.class)
    private List<Grocery> groceries;

    public GroceryList(String groceryListTitle, List<Grocery> groceries) {
        this.groceryListTitle = groceryListTitle;
        this.groceries = groceries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroceryListTitle() {
        return groceryListTitle;
    }

    public void setGroceryListTitle(String groceryListTitle) {
        this.groceryListTitle = groceryListTitle;
    }

    @TypeConverters(DataConverter.class)
    public List<Grocery> getGroceries() {
        return groceries;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
    }

    public void addGrocery(Grocery grocery){
        groceries.add(grocery);
    }
}
