package com.pluralsight.model.food;


public class Cookie extends Food {
    @Override
    public String displayItem() {
        return "";
    }

    public Cookie(String item) {
        super(item);
        this.price = .75;
    }
    public String formatForReceipt() {
        return String.format("\t\t\t%s - $.75", this.item);
    }
}
