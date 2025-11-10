package model;

import java.util.List;

public abstract class Drink extends MenuItem {
    protected String size,temp,base,milk;
    protected List<String> syrup,toppings;
    //some of these will have an assumed default, and we want the option for multiple syrups


    public Drink(double price, String item, String size, String base, String milk) {
        super(price, item);
        this.size = size;
        this.base = base;
        this.syrup = null;
        this.toppings = null;
        this.temp = "Hot";
        this.milk = milk;
        switch(this.size){case "Small"->this.price=1;case "Medium"->this.price=1.5;case "Large"->this.price=2;}
        if (!this.base.equals("None")){
            switch(this.size){case "Small"->this.price+=.5;case "Medium"->this.price+=1;case "Large"->this.price+=1.5;}
        }
        if(!this.milk.equals("None")){
            switch(this.size){case "Small"->this.price+=.25;case "Medium"->this.price+=.5;case "Large"->this.price+=.75;}
        }
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
}
