package com.pluralsight.model.drinks;

import java.util.ArrayList;

public class SiphonCoffee extends Drink {
    private boolean roomForCream,addShot;
    private int numShots;

    public SiphonCoffee(String item, String size, String base, String milk) {
        super(item, size, base, milk);
        this.roomForCream = false;
        this.addShot = false;
        this.numShots = 0;
    }

    public boolean isAddShot() {
        return addShot;
    }

    public void setAddShot(int numShots) {
        this.addShot = true;
        this.price += .5*numShots;
    }

    public void setRoomForCream(){
        this.roomForCream = true;
        this.milk = "None";
    }

    public boolean isRoomForCream() {
        return roomForCream;
    }
    public double calculateTotal() {
        this.price = 0;
        switch(this.size){case "Small"->this.price=1;case "Medium"->this.price=1.5;case "Large"->this.price=2;}
        if (!this.base.equals("None")){
            switch(this.size){case "Small"->this.price+=.5;case "Medium"->this.price+=1;case "Large"->this.price+=1.5;}
        }
        if(!this.milk.equals("None")){
            switch(this.size){case "Small"->this.price+=.25;case "Medium"->this.price+=.5;case "Large"->this.price+=.75;}
        }
        if (isAddShot()){this.price += .5*(this.numShots-2);}

        return this.price;
    }

    @Override
    public String displayDrink() {
        StringBuilder drink = new StringBuilder(String.format("Drink: %s Coffee%n\t\t- %s%n\t\t- %s%n\t\t- %s",this.temp,this.size,this.base,this.milk));
        if (isRoomForCream()){
            drink.append(String.format("%n\t\t- Room for Cream"));
        }
        if (isAddShot()){
            drink.append(String.format("%n\t\t- %d Extra Shots +$%.2f",this.numShots,(.5*numShots)));
        }
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

        drink.append(String.format("%n\tDrink Total: $%.2f",calculateTotal()));
        return drink.toString();
    }

}
