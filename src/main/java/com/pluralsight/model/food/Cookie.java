package com.pluralsight.model.food;

import com.pluralsight.model.Food;

public class Cookie extends Food {
    public Cookie(String item, boolean toGo) {
        super(item, toGo);
        this.price = .75;
    }
    public String formatForReceipt() {
        StringBuilder cookie = new StringBuilder(String.format("%s - $.75%n\t\t",this.item));
        if (isToGo()){
            cookie.append(String.format("%n\t\t- To Go"));
        }else{
            cookie.append(String.format("%n\t\t- For Here"));}
        return cookie.toString();
    }
}
