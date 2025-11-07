package com.pluralsight;

public abstract class MenuItem {
    protected double price;
    protected String item;

    public MenuItem(double price, String item) {
        this.price = price;
        this.item = item;
    }

    public  double calculateTotal(){
        return this.price;
    }
}
