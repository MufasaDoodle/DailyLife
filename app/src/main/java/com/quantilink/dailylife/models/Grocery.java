package com.quantilink.dailylife.models;

import java.io.Serializable;

public class Grocery implements Serializable {

    private String title;
    private int amount;
    private boolean isChecked;

    public Grocery(String title, int amount, boolean isChecked) {
        this.title = title;
        this.amount = amount;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
