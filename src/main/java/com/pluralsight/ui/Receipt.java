package com.pluralsight.ui;

import com.pluralsight.model.Food;
import com.pluralsight.model.MenuItem;
import com.pluralsight.model.drinks.Drink;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.pluralsight.ui.MenuUtility.*;

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
    public String displayReceipt(){
        LocalDateTime now = LocalDateTime.now();
        StringBuilder receipt = new StringBuilder(String.format("%s\t\t\tThe Roost\n\t\tRECEIPT - CUSTOMER COPY\n\t\t%10tD %14tr\n\t✾Drinks: ",ALT_BORDER_T,now,now));
        for (MenuItem i : this.receipt){
            if (i instanceof Drink){receipt.append(i.formatForReceipt());}
        }
        receipt.append(String.format("\t✾Food Items:"));
        for (MenuItem i : this.receipt){
            if (i instanceof Food){receipt.append(i.formatForReceipt());}
        }
        receipt.append(String.format("\t\t\t\t≪ °❈ Total:"))
        return receipt.toString();
    }

}
