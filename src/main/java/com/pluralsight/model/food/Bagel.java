package com.pluralsight.model.food;


public class Bagel extends Food {
    private String portionType;
    private boolean toasted;

    public Bagel( String item, boolean toGo, String portionType) {
        super(item);
        this.portionType = portionType;
        this.toasted = false;

    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public String getPortionType() {
        return portionType;
    }

    public void setPortionType(String portionType) {
        this.portionType = portionType;
    }

    @Override
    public double calculateTotal() {
        switch(this.portionType){case "Plain"->this.price=1;case "Honey Buttered"->this.price=1.5;case "Sandwich"->this.price=4;}
        return this.price;
    }

    public String displayItem() {
        StringBuilder bagel = new StringBuilder(String.format("Food: %s %s",this.portionType,this.item));
        if (isToGo()){
            bagel.append(String.format("%n\t\t- To Go"));
        }else{
            bagel.append(String.format("%n\t\t- For Here"));}
        if (isToasted()){
            bagel.append(String.format("%n\t\t- Toasted"));
        }

        bagel.append(String.format("%n\tItem Total: $%.2f",calculateTotal()));
        return bagel.toString();
    }
    public String formatForReceipt() {
        StringBuilder bagel = new StringBuilder(String.format("\t\t\t%s %s - $%.2f",this.portionType,this.item,calculateTotal()));
        if (isToGo()){
            bagel.append(String.format("%n\t\t\t\t- To Go"));
        }else{
            bagel.append(String.format("%n\t\t\t\t- For Here"));}
        if (isToasted()){
            bagel.append(String.format("%n\t\t\t\t- Toasted"));
        }
        return bagel.toString();
    }

}
