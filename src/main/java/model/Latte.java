package model;

public class Latte extends Drink{
    private boolean dry,addShot,addMilk;

    public Latte(double price, String item, String size, String base) {
        super(price, item, size, base,milk);
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

    public void setAddShot(boolean addShot,int numShots) {
        this.addShot = addShot;
        this.price += .5*numShots;
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
}
