package com.pluralsight.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    private static Inventory instance; //We're making this singleton, we only want one inventory
    private HashMap<String,ArrayList<String>> inventory;
    private ArrayList<String> base,milks,syrups,toppings,tempTypes,drinkTypes,bagels,cookies;
    private HashMap<String,String> drinkTypesDescription;

    private Inventory() {

        base = new ArrayList<>(List.of("Espresso","Decaf","House Blend","Earl Grey","Matcha","Chai","None"));
        milks = new ArrayList<>(List.of("2% Milk","Whole Milk","Oat Milk","Pigeon Milk","None"));
        syrups = new ArrayList<>(List.of("Chocolate","Vanilla","Caramel","Toffeenut","Cherry","Hazelnut","Pumpkin Spice","Peppermint","Maple Pecan"));
        toppings = new ArrayList<>(List.of("Whipped Cream","Toffee Sprinkles","Chocolate Shavings","Cinnamon Streusel","Cookie Crumbles"));
        tempTypes = new ArrayList<>(List.of("Hot","Iced","Frozen"));
        drinkTypes = new ArrayList<>(List.of("Latte","Siphon Coffee","Tea","Steamer"));
        bagels = new ArrayList<>(List.of("Plain","Honey Buttered","Sandwich"));
        cookies = new ArrayList<>(List.of("Brewster's Sable Shortbread","Homemade Chocolate Chip","Double Chocolate Cookie","Oatmeal Raisin","Snickerdoodle"));

        inventory = new HashMap<>(){{put("Caffeine Base",base);
            put("Milks",milks);
            put("Syrups",syrups);
            put("Toppings",toppings);
            put("Temperature",tempTypes);
            put("Drinks",drinkTypes);
            put("Bagels",bagels);
            put("Cookies",cookies);
        }};

        // drinks with descriptions, as a map
         drinkTypesDescription = new HashMap(){{
             put("Latte", "Rich espresso balanced with steamed milk and a light layer of foam. Available Hot, Iced, or Frozen.");
             put("Siphon Coffee", "Full-bodied coffee brewed with hot water through a siphon. Available Hot, Iced, or Frozen.");
             put("Tea", "Smooth, earthy teas brewed gently with hot water. Milk optional. Available Hot or Iced.");
             put("Steamer", "Comforting warm,steamed milk with optional flavors. Available Hot, Iced, or Frozen.");
         }};

    }
    public HashMap<String,ArrayList<String>> getInventory(){
        return inventory;
    }

    public void displayInventory(String type){
        System.out.println(type+":");
        for (int i=1;i<=inventory.get(type).size();i++){
            System.out.printf("[%d] %s\n",i,inventory.get(type).get(i-1));
        }
    }

    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }
}
