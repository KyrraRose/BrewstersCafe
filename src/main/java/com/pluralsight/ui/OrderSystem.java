package com.pluralsight.ui;

import com.pluralsight.model.Inventory;
import com.pluralsight.model.MenuItem;
import com.pluralsight.model.drinks.*;

import java.util.List;

import static com.pluralsight.ui.MenuOptions.*;
import static com.pluralsight.ui.MenuUtility.*;

public class OrderSystem {
    private static final Inventory inventory = Inventory.getInstance();
    private static final Receipt<MenuItem> receipt = new Receipt<>();



    //drink methods
    public static void processOrderDrink(){
        String type = "" ;
        systemDialogue(TEAL,MINT,"You want to order a drink?...Coo.\n\tWhat would you like? We have...");
        do {
            systemDialogueLarge(BROWN, BEIGE, displayOptions(DESCRIPTIONS));
            switch (getUserInt()) {
                case 1 -> receipt.addToReceipt(buildLatte());
                case 2 -> receipt.addToReceipt(buildCoffee());
                case 3 -> receipt.addToReceipt(buildTea());
                case 4 -> receipt.addToReceipt(buildSteamer());
            }
            systemDialogue(TEAL, MINT, "I've added it to your tab..Coo.\n\tAny other drinks?..");
        }while(getYesNo("Type Here"));
    }
    //interaction methods
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

    //build the drinks
    public static Drink buildLatte(){
        String size, base, milk;

        while (true) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = askIngredient("Caffeine Base", "Would you like espresso..\n\tor something else?...Coo.");
            milk = askIngredient("Milks", "..Coo. You want pigeon milk in that?");

            Latte drink = new Latte("Latte", size, base, milk);
            //set extras
            drink.setTemp(askKind(TEMP,"Would you like your latte iced or frozen?..."));
            if (drink.getTemp().equals("Hot")){
                drink.setDry(askExtras("Dry (extra foam)","Coo...Would you like it dry?..","Type Here"));}

            systemDialogue(TEAL,MINT,"Any extra shots of espresso?...Coo..");
            if(getYesNo("Type here")){
                drink.setAddShot(askNum("Additional Shots: +$.50","Okay, how many shots?..."));}

            drink.setSyrup(askIngredient("Syrups","..Coo. Any flavored syrup?..\n\tThe sky is the limit.."));
            //there's probably a cleaner way to do this, but we're here now
            while (true) {
                systemDialogue(TEAL,MINT,"Any other flavors?...");
                if (getYesNo("Type Here")){
                    drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                }else{break;}
            }

            drink.setToppings(askIngredient("Toppings","..Coo. Any fun toppings?..\n\t..We have so many.."));
            //toppings loop
            while (true) {
                systemDialogue(TEAL, MINT, "Any other toppings?...");
                if (getYesNo("Type Here")) {
                    drink.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                } else {
                    break;
                }
            }


            if (askExtras(drink.displayDrink(),"Here is the drink you ordered...\n\tCoo..Does it look right?..","Type Here")) {
                return drink;
            }
        }

    }

    public static Drink buildCoffee(){
        String size, base, milk;

        while (true) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = askIngredient("Caffeine Base", "Would you like our House Blend..\n\tor something else?...Coo.");
            if(askExtras("Milk","..Coo. Would you like milk in that?..","Type Here")){milk =askIngredient("Milks", "..Coo. Here are your options. Want pigeon milk?..");
            }else{milk = "None";}

            SiphonCoffee drink = new SiphonCoffee("Coffee", size, base, milk);
            //set extras
            drink.setTemp(askKind(TEMP,"Would you like your coffee iced or frozen?..."));
            if(drink.getMilk().equals("None")){if(askExtras("Room for Cream","Coo..do you want room to add cream?","Type Here")){drink.setRoomForCream();}}

            systemDialogue(TEAL,MINT,"Any shots of espresso?...Coo..");
            if(getYesNo("Type here")){drink.setAddShot(askNum("Espresso Shots: +$.50","Okay, how many shots?..."));}

            drink.setSyrup(askIngredient("Syrups","..Coo. Any flavored syrup?..\n\tI prefer my coffee black.."));
            //there's probably a cleaner way to do this, but we're here now
            while (true) {
                systemDialogue(TEAL,MINT,"Any other flavors?...");
                if (getYesNo("Type Here")){
                    drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                }else{break;}
            }
            if(askExtras("Add toppings?","..Coo. Any fun toppings?..\n\t..We have so many..","Type Here")) {
                drink.setToppings(askIngredient("Toppings", "Here are our toppings..Coo."));
                //toppings loop
                while (true) {
                    systemDialogue(TEAL, MINT, "Any other toppings?...");
                    if (getYesNo("Type Here")) {
                        drink.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                    } else {
                        break;
                    }
                }
            }

            if (askExtras(drink.displayDrink(),"Here is the drink you ordered...\n\tCoo..Does it look right?..","Type Here")) {
                return drink;
            }
        }

    }

    public static Drink buildTea(){
        String size, base, milk;

        while (true) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = askIngredient("Caffeine Base", "Would you like our House Blend..\n\tor something else?...Coo.");
            if(askExtras("Milk","..Coo. Would you like milk in that?..","Type Here")){
                milk =askIngredient("Milks", "..Coo. Here are your options. Want pigeon milk?..");
            }else{milk = "None";}

            Tea drink = new Tea("Tea", size, base, milk);

            //set extras
            drink.setTemp(askKind(TEMP,"Would you like your tea iced or frozen?..."));
            if(!drink.getMilk().equals("None")){
                if(askExtras("Steamed Milk","Coo..do you want the milk steamed?","Type Here")){drink.setSteamMilk();}
            }
            if(askExtras("Extra Tea Bag?","Any extra tea bags?...Coo..\n\tYou already get one..","Type Here")){
                drink.setAddTeaBag(askNum("Additional Bags: +$.10","Okay, how many bags?..."));}


            drink.setSyrup(askIngredient("Syrups","..Coo. Any flavored syrup?.."));
            //there's probably a cleaner way to do this, but we're here now
            while (true) {
                systemDialogue(TEAL,MINT,"Any other flavors?...");
                if (getYesNo("Type Here")){
                    drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                }else{break;}
            }
            if(askExtras("Add toppings?","..Coo. Any fun toppings?..\n\t..We have so many..","Type Here")) {
                drink.setToppings(askIngredient("Toppings", "Here are our toppings..Coo."));
                //toppings loop
                while (true) {
                    systemDialogue(TEAL, MINT, "Any other toppings?...");
                    if (getYesNo("Type Here")) {
                        drink.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                    } else {
                        break;
                    }
                }
            }

            if (askExtras(drink.displayDrink(),"Here is the drink you ordered...\n\tCoo..Does it look right?..","Type Here")) {
                return drink;
            }
        }

    }

    public static Drink buildSteamer(){
        String size, base, milk;

        while (true) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = askIngredient("Caffeine Base", "Would you like our House Blend..\n\tor something else?...Coo.");
            milk = askIngredient("Milks", "..Coo. You want milk in that?");

            Steamer drink = new Steamer("Tea", size, base, milk);
            //set extras
            drink.setTemp(askKind(TEMP,"Would you like your tea iced or frozen?..."));
            if(drink.getTemp().equals("Hot")){if(askExtras("Kids Temperature (180 Degrees)","Coo..do you want to drink it right away?","Type Here")){drink.setKidsTemp();}}


            drink.setSyrup(askIngredient("Syrups","..Coo. Any flavored syrup?.."));
            //there's probably a cleaner way to do this, but we're here now
            while (true) {
                systemDialogue(TEAL,MINT,"Any other flavors?...");
                if (getYesNo("Type Here")){
                    drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                }else{break;}
            }

            if(askExtras("Add toppings?","..Coo. Any fun toppings?..\n\t..We have so many..","Type Here")) {
                drink.setToppings(askIngredient("Toppings", "Here are our toppings..Coo."));
                //toppings loop
                while (true) {
                    systemDialogue(TEAL, MINT, "Any other toppings?...");
                    if (getYesNo("Type Here")) {
                        drink.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                    } else {
                        break;
                    }
                }
            }

            if (askExtras(drink.displayDrink(),"Here is the drink you ordered...\n\tCoo..Does it look right?..","Type Here")) {
                return drink;
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
