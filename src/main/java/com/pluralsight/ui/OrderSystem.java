package com.pluralsight.ui;

import com.pluralsight.model.Inventory;
import com.pluralsight.model.drinks.*;

import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.ui.MenuOptions.*;
import static com.pluralsight.ui.MenuUtility.*;

public class OrderSystem {
    private static final Inventory inventory = Inventory.getInstance();



    //drink methods
    public static void processOrderDrink(){
        String type = "" ;
        switch(getUserInt()){
            case 1 -> {
                buildLatte();
            }
            case 2 -> type = "Siphon Coffee";
            case 3 -> type = "Tea";
            case 4 -> type = "Steamer";
        }
    }
    public static String askIngredient(String ingredient,String dialogue){
        systemDialogue(TEAL,MINT,dialogue);
        systemDialogue(BROWN,BEIGE,inventory.displayInventory(ingredient));
        return inventory.getInventory().get(ingredient).get(getUserInt()-1);

    }
    public static boolean askExtras(String extra,String dialogue,String prompt){
       systemDialogue(TEAL,MINT,dialogue);
       systemDialogue(BROWN,BEIGE,extra);
       return getYesNo(prompt);
    }
    public static int askNum(String extra,String dialogue){
        systemDialogue(TEAL,MINT,dialogue);
        systemDialogue(BROWN,BEIGE,extra);
        return getUserInt();
    }

    public static String askKind(List<String> menu,String dialogue){
        systemDialogue(TEAL,MINT,dialogue);
        systemDialogue(BROWN,BEIGE,displayOptions(menu));
        return menu.get(getUserInt());
    }
    public static String askKindWithPrice(List<String> menu,String dialogue){
        systemDialogue(TEAL,MINT,dialogue);
        systemDialogue(BROWN,BEIGE,displaySizePrice(menu));
        return menu.get(getUserInt());
    }
    public static void buildLatte(){
        String size, base, milk;
        boolean keepGoing=true;
        while (keepGoing) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = askIngredient("Caffeine Base", "Would you like espresso..\n\tor something else?...Coo.");
            milk = askIngredient("Milks", "..Coo. You want pigeon milk in that?");

            Latte latte = new Latte("Latte", size, base, milk);
            //set extras
            latte.setTemp(askKind(TEMP,"Would you like your latte iced or frozen?..."));
            if (latte.getTemp().equals("Hot")){latte.setDry(askExtras("Dry (extra foam)","Coo...Would you like it dry?..","Type Here"));}

            systemDialogue(TEAL,MINT,"Any extra shots of espresso?...Coo..");
            if(getYesNo("Type here")){latte.setAddShot(askNum("Additional Shots: +$.50","Okay, how many shots?..."));}

            latte.setSyrup(askIngredient("Syrups","..Coo. Any flavored syrup?..\n\tThe sky is the limit.."));
            //there's probably a cleaner way to do this, but we're here now
            while (keepGoing) {
                systemDialogue(TEAL,MINT,"Any other flavors?...");
                if (getYesNo("Type Here")){
                    latte.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                }else{keepGoing=false;}
            }
            keepGoing = true;
            latte.setToppings(askIngredient("Toppings","..Coo. Any fun toppings?..\n\t..We have so many.."));
            //toppings loop
            while (keepGoing) {
                systemDialogue(TEAL, MINT, "Any other toppings?...");
                if (getYesNo("Type Here")) {
                    latte.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                } else {
                    keepGoing = false;
                }
            }
            keepGoing = true;

            systemDialogue(TEAL, MINT, "Here is the drink you ordered...\n\tCoo..Does it look right?..");
            systemDialogue(BROWN, BEIGE, latte.displayDrink());
            if (getYesNo("Type Here")) {

                keepGoing = false;
            }
        }

    }

    public static void processOrderFood(){
        System.out.println("Processing!");
    }
    public static void processAddCookie(){
        System.out.println("Processing!");
    }
    public static void checkout(){
        System.out.println("Processing!");
    }

    public void displayAllChoices(){
        for (String type : inventory.getInventory().keySet()) {
            System.out.printf("%s%s:%s%n",MenuUtility.MINT,type,MenuUtility.RESET);
            inventory.displayInventory(type);
        }
    }

}
