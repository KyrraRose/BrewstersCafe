package com.pluralsight.model.drinks;

import com.pluralsight.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public abstract class Drink extends MenuItem {
    protected String size,temp,base,milk;
    protected List<String> syrup,toppings;
    //some of these will have an assumed default, and we want the option for multiple syrups


    public Drink(String item, String size, String base, String milk) {
        super(item);
        this.size = size;
        this.base = base;
        this.syrup = new ArrayList<>();
        this.toppings = new ArrayList<>();
        this.temp = "Hot";
        this.milk = milk;

    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }


    public List<String> getSyrup() {
        return syrup;
    }

    public void setSyrup(String syrup) {
        this.syrup.add(syrup) ;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings.add(toppings);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }

    public void setSyrup(List<String> syrup) {
        this.syrup = syrup;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    @Override
    public double calculateTotal() {
        switch(this.size){case "Small"->this.price=1;case "Medium"->this.price=1.5;case "Large"->this.price=2;}
        if (!this.base.equals("None")){
            switch(this.size){case "Small"->this.price+=.5;case "Medium"->this.price+=1;case "Large"->this.price+=1.5;}
        }
        if(!this.milk.equals("None")){
            switch(this.size){case "Small"->this.price+=.25;case "Medium"->this.price+=.5;case "Large"->this.price+=.75;}
        }
        return this.price;
    }

    public String displayDrink(){
        StringBuilder drink = new StringBuilder(String.format("Drink: %s %s%n\t\t- %s%n\t\t- %s%n\t\t- %s",this.temp,this.item,this.size,this.base,this.milk));
        if (!this.syrup.isEmpty()){
            for(String s :this.syrup){
                drink.append(String.format("%n\t\t- %s",s));
            }
        }
        if (!this.toppings.isEmpty()){
            for(String t :this.toppings){
                drink.append(String.format("%n\t\t- %s",t));
            }
        }
        drink.append(String.format("%n\tDrink Total: $%.2f",this.price));
        return drink.toString();
    }
}
