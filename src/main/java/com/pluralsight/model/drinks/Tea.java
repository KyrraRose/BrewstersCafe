package com.pluralsight.model.drinks;

public class Tea extends Drink {
    private boolean steamMilk,addTeaBag;
    private int teaBags;

    public Tea(String item, String size, String base, String milk) {
        super(item, size, base, milk);
        this.steamMilk = false;
        this.addTeaBag = false;
        this.teaBags = 1;
        if (this.size.equals("Large")){this.teaBags=2;}
    }

    public boolean isSteamMilk() {
        return steamMilk;
    }

    public void setSteamMilk() {
        this.steamMilk = true;
    }

    public int getTeaBags() {
        return teaBags;
    }

    public void setTeaBags(int teaBags) {
        this.teaBags = teaBags;
    }

    //maybe we want extra tea bags
    public boolean isAddTeaBag() {
        return addTeaBag;
    }

    public void setAddTeaBag(int numBags) {
        this.addTeaBag = true;
        this.teaBags += numBags;
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
        if (isAddTeaBag()){this.price += .1*(this.teaBags-1);}

        return this.price;
    }

    @Override
    public String displayDrink() {
        StringBuilder drink = new StringBuilder(String.format("Drink: %s %s Tea%n\t\t- %s",this.size,this.temp,this.base));
        if (isSteamMilk()){
            drink.append(String.format("%n\t\t- Steamed %s",this.milk));
        }
        if (this.addTeaBag){
            drink.append(String.format("%n\t\t- %d Tea Bags",this.teaBags));
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

        drink.append(String.format("%nDrink Total: $%.2f",calculateTotal()));
        return drink.toString();
    }
}
