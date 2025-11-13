package com.pluralsight.ui;

import com.pluralsight.model.MenuItem;

import java.util.ArrayList;

public class Receipt<T extends MenuItem> {
    private ArrayList<T> receipt;
    private double orderTotal;

    public Receipt() {
        this.receipt = new ArrayList<>();
        this.orderTotal = 0;
    }


    // Remove or clear if needed
    public void clearItems() {
        this.receipt.clear();
    }

    public void addToReceipt(T item){
        receipt.add(item);
    }

    public ArrayList<T> getReceipt() {
        return this.receipt;
    }

    public void setReceipt(ArrayList<T> receipt) {
        this.receipt = receipt;
    }

    public double getOrderTotal() {
        for(T item : this.receipt){
            this.orderTotal+= item.calculateTotal();
        }
        return this.orderTotal;
    }

}
