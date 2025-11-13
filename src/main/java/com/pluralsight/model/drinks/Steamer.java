package com.pluralsight.model.drinks;

public class Steamer extends Drink {
    private boolean kidsTemp;

    public Steamer(String item, String size, String base, String milk) {
        super(item, size, base, milk);
        this.kidsTemp = false;
    }

    public boolean isKidsTemp() {
        return kidsTemp;
    }

    public void setKidsTemp() {
        this.kidsTemp = true;
    }
    //I wanted the steamer to be special somehow, so temperature gets to transform it
    @Override
    public void setTemp(String temp) {
        this.temp = temp;
        switch(temp){case"Iced"->this.item="Flavored Milk";case"Frozen"->this.item="Milkshake";}
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

        return this.price;
    }


    public String displayItem() {
        StringBuilder drink = new StringBuilder(String.format("Drink: %s %s %s%n\t\t- %s%n\t\t- %s",this.size,this.temp,this.item,this.base,this.milk));
        if (isKidsTemp()){
            drink.append(String.format("%n\t\t- Kid's Temperature (180 Degrees)"));
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

        drink.append(String.format("\t%nDrink Total: $%.2f",calculateTotal()));
        return drink.toString();
    }
    public String formatForReceipt() {
        StringBuilder drink = new StringBuilder(String.format("Drink: %s %s %s - $%.2f%n\t\t- %s%n\t\t- %s",this.size,this.temp,this.item,calculateTotal(),this.base,this.milk));
        if (isKidsTemp()){
            drink.append(String.format("%n\t\t- Kid's Temperature (180 Degrees)"));
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

        return drink.toString();
    }
}
