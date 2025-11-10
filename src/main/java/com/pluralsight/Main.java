package com.pluralsight;

import com.pluralsight.model.*;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();

        inventory.displayInventory("Bagels");
    }
}
