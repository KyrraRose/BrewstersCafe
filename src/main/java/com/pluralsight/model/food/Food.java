package com.pluralsight.model.food;

import com.pluralsight.model.MenuItem;

public abstract class Food extends MenuItem {
    protected boolean toGo;

    public Food(String item) {
        super(item);
        this.toGo = false;
    }

    public boolean isToGo() {
        return toGo;
    }

    public void setToGo(boolean toGo) {
        this.toGo = toGo;
    }


}