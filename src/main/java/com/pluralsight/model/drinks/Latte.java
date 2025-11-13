package com.pluralsight.model.drinks;

public class Latte extends Drink {
    private boolean dry,addShot,addMilk;
    private int numShots;

    public Latte(String item, String size, String base, String milk) {
        super(item,size,base,milk);
        this.numShots = 2;
        this.addShot = false;
    }

    //dry means extra foam, like a cappuccino
    public boolean isDry() {
        return dry;
    }

    public void setDry(boolean dry) {
        this.dry = dry;
    }

    public boolean isAddShot() {
        return addShot;
    }

    public void setAddShot(int numShots) {
        this.addShot = true;
        this.numShots += numShots;
    }

    public boolean isAddMilk() {
        return addMilk;
    }

    public void setAddMilk(boolean addMilk) {
        this.addMilk = addMilk;
    }

    //keeping these in case customer wants to change their order
    public String getMilk() {
        return milk;
    }

    public void setMilk(String milk) {
        this.milk = milk;
    }
    @Override
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
        StringBuilder drink = new StringBuilder(String.format("Drink: %s %s%n\t\t- %s%n\t\t- %s%n\t\t- %s",this.temp,this.item,this.size,this.base,this.milk));
        if (isDry()){
            drink.append(String.format("%n\t\t- Dry"));
        }
        if (isAddShot()){
            drink.append(String.format("%n\t\t- %d Extra Shots +$%.2f",this.numShots-2,(.5*(numShots-2))));
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
