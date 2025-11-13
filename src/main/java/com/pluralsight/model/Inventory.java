package com.pluralsight.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Inventory {
    private static Inventory instance; //We're making this singleton, we only want one inventory
    private final LinkedHashMap<String,ArrayList<String>> inventory;
    private final ArrayList<String> base,milks,syrups,toppings,tempTypes,drinkTypes,bagels,cookies;

    private Inventory() {
        drinkTypes = new ArrayList<>(List.of("Latte","Siphon Coffee","Tea","Steamer"));
        base = new ArrayList<>(List.of("Espresso","Decaf","House Blend","Earl Grey","Matcha","Chai"));
        milks = new ArrayList<>(List.of("2% Milk","Whole Milk","Oat Milk","Pigeon Milk","Heavy Whipping Cream"));
        syrups = new ArrayList<>(List.of("Chocolate","Vanilla","Caramel","Toffeenut","Cherry","Hazelnut","Pumpkin Spice","Peppermint","Maple Pecan"));
        toppings = new ArrayList<>(List.of("Whipped Cream","Toffee Sprinkles","Chocolate Shavings","Cinnamon Streusel","Cookie Crumbles"));
        tempTypes = new ArrayList<>(List.of("Hot","Iced","Frozen"));
        bagels = new ArrayList<>(List.of("Plain","Honey Buttered","Sandwich"));
        cookies = new ArrayList<>(List.of("Brewster's Sable Shortbread","Homemade Chocolate Chip","Double Chocolate Cookie","Oatmeal Raisin","Snickerdoodle"));

        inventory = new LinkedHashMap<>(){{
            put("Drinks",drinkTypes);
            put("Caffeine Base",base);
            put("Milks",milks);
            put("Syrups",syrups);
            put("Toppings",toppings);
            put("Temperature",tempTypes);
            put("Bagels",bagels);
            put("Cookies",cookies);
        }};


    }
    public HashMap<String,ArrayList<String>> getInventory(){
        return inventory;
    }

    public String displayInventory(String type){
        final String itemFormat = "\n\t\t[%d] %s";
        StringBuilder display = new StringBuilder();
        display.append(String.format("✾%s:",type));
        for (int i=1;i<=inventory.get(type).size();i++){
            display.append(String.format(itemFormat,i,inventory.get(type).get(i-1)));
        }
        return display.toString();
    }

    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }
}
