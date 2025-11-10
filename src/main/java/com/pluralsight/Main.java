package com.pluralsight;

import model.*;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        inventory.displayInventory("Drinks");
    }
}
