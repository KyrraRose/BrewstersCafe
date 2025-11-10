package model;

import java.util.ArrayList;

public class SiphonCoffee extends Drink{
    private boolean roomForCream,addShot,addMilk,addSweetener;
    private ArrayList<String> syrup;

    public SiphonCoffee(double price, String item, String size, String base, String milk) {
        super(price, item, size, base, milk);
        this.roomForCream = false;
        this.addShot = false;
        this.addMilk = false;
        this.addSweetener = false;
        this.syrup=null;
    }

    public boolean isAddShot() {
        return addShot;
    }

    public void setAddShot(boolean addShot,int numShots) {
        this.addShot = addShot;
        this.price += .5*numShots;
    }

    public void setRoomForCream(){
        this.roomForCream = true;
    }
}
