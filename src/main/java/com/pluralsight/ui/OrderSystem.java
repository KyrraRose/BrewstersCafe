package com.pluralsight.ui;

import com.pluralsight.model.Inventory;
import com.pluralsight.model.MenuItem;
import com.pluralsight.model.drinks.*;
import com.pluralsight.model.food.*;

import java.util.List;

import static com.pluralsight.ui.MenuOptions.*;
import static com.pluralsight.ui.MenuUtility.*;

public class OrderSystem {
    private static final Inventory inventory = Inventory.getInstance();
    private static final Receipt<MenuItem> receipt = new Receipt<>();


    //interaction methods
    public static String askIngredient(String ingredient,String dialogue){
        systemDialogue(TEAL,MINT,dialogue);
        systemDialogue(BROWN,BEIGE,inventory.displayInventory(ingredient));
        return inventory.getInventory().get(ingredient).get(getUserInt()-1);

    }
    public static boolean askConfirm(String extra, String dialogue){
       systemDialogue(TEAL,MINT,dialogue);
       systemDialogue(BROWN,BEIGE,extra);
       return getYesNo("Type Here");
    }
    public static boolean askExtras(String dialogue){
        systemDialogue(TEAL,MINT,dialogue);
        return getYesNo("Type Here");
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

    //drink methods
    public static void processOrderDrink(){
        systemDialogue(TEAL,MINT,"You want to order a drink?...Coo.\n\tWhat would you like? We have...");
        do {
            systemDialogueLarge(BROWN, BEIGE, displayMenuWithBack(DESCRIPTIONS));
            switch (getUserInt()) {
                case 1 -> receipt.addToReceipt(buildLatte());
                case 2 -> receipt.addToReceipt(buildCoffee());
                case 3 -> receipt.addToReceipt(buildTea());
                case 4 -> receipt.addToReceipt(buildSteamer());
            }
            systemDialogue(TEAL, MINT, "I've added it to your tab..Coo.\n\tAny other drinks?..");
        }while(getYesNo("Type Here"));
    }

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
                drink.setDry(askExtras("Coo...Would you like it dry?..\n\tThat means...you want extra foam."));}


            if(askExtras("Any extra shots of espresso?...Coo..")){
                drink.setAddShot(askNum("Additional Shots: +$.50","Okay, how many shots?..."));}

            if(askExtras("..Coo. Any flavored syrup?..\n\tThey can really shake things up...")) {
                drink.setSyrup(askIngredient("Syrups","Okay..What flavor?...Coo."));
                //toppings loop
                while (true) {
                    if (askExtras("Any...other flavors?...")){
                        drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                    }else{break;}
                }

            }

            if(askExtras("..Coo. Any fun toppings?..\n\t..We have so many..")) {
                drink.setToppings(askIngredient("Toppings", "Here are our toppings..Coo."));
                //toppings loop
                while (true) {
                    if (askExtras("Any other toppings?...")) {
                        drink.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                    } else {
                        break;
                    }
                }
            }


            if (askConfirm(drink.displayItem(),"Here is the drink you ordered...\n\tCoo..Does it look right?..")) {
                return drink;
            }
        }

    }

    public static Drink buildCoffee(){
        String size, base, milk;

        while (true) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = askIngredient("Caffeine Base", "Would you like our House Blend..\n\tor something else?...Coo.");
            if(askExtras("..Coo. Would you like milk in that?..")){milk =askIngredient("Milks", "..Coo. Here are your options. Want pigeon milk?..");
            }else{milk = "None";}

            SiphonCoffee drink = new SiphonCoffee("Coffee", size, base, milk);
            //set extras
            drink.setTemp(askKind(TEMP,"Would you like your coffee iced or frozen?..."));
            if(drink.getMilk().equals("None")){if(askExtras("Coo..do you want room to add cream?")){drink.setRoomForCream();}}

            systemDialogue(TEAL,MINT,"Any shots of espresso?...Coo..");
            if(getYesNo("Type here")){drink.setAddShot(askNum("Espresso Shots: +$.50","Okay, how many shots?..."));}


            //there's probably a cleaner way to do this, but we're here now

            if(askExtras("..Coo. Any flavored syrup?..\n\tI prefer my coffee black..")) {
                drink.setSyrup(askIngredient("Syrups","Okay..What flavor?...Coo."));
                //toppings loop
                while (true) {

                    if (askExtras("Any...other flavors?...")){
                        drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                    }else{break;}
                }

            }
            if(askExtras("..Coo. Any fun toppings?..\n\t..We have so many..")) {
                drink.setToppings(askIngredient("Toppings", "Here are our toppings..Coo."));
                //toppings loop
                while (true) {
                    if (askExtras("Any other toppings?...")) {
                        drink.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                    } else {
                        break;
                    }
                }
            }

            if (askConfirm(drink.displayItem(),"Here is the drink you ordered...\n\tCoo..Does it look right?..")) {
                return drink;
            }
        }

    }

    public static Drink buildTea(){
        String size, base, milk;

        while (true) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = askIngredient("Caffeine Base", "Would you like our House Blend..\n\tor something else?...Coo.");
            if(askExtras("..Coo. Would you like milk in that?..")){
                milk =askIngredient("Milks", "..Coo. Here are your options. Want pigeon milk?..");
            }else{milk = "None";}

            Tea drink = new Tea("Tea", size, base, milk);

            //set extras
            drink.setTemp(askKind(TEMP,"Would you like your tea iced or frozen?..."));
            if(!drink.getMilk().equals("None") && drink.getTemp().equals("Hot")){
                if(askExtras("Coo..do you want the milk steamed?")){drink.setSteamMilk();}
            }
            if(askExtras("Any extra tea bags?...Coo..\n\tYou already get one..")){
                drink.setAddTeaBag(askNum("Additional Bags: +$.10","Okay, how many bags?..."));}


            if(askExtras("..Coo. Any flavored syrup?..\n\tI'm all out of sugar cubes..")) {
                drink.setSyrup(askIngredient("Syrups","Okay..What flavor?...Coo."));
                //toppings loop
                while (true) {

                    if (askExtras("Any...other flavors?...")){
                        drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                    }else{break;}
                }

            }
            if(askExtras("..Coo. Any fun toppings?..\n\t..We have so many..")) {
                drink.setToppings(askIngredient("Toppings", "Here are our toppings..Coo."));
                //toppings loop
                while (true) {
                    if (askExtras("Any other toppings?...")) {
                        drink.setToppings(askIngredient("Toppings", "What would you also like?...Coo."));
                    } else {
                        break;
                    }
                }
            }

            if (askConfirm(drink.displayItem(),"Here is the drink you ordered...\n\tCoo..Does it look right?..")) {
                return drink;
            }
        }

    }

    public static Drink buildSteamer(){
        String size, base, milk;

        while (true) {
            size = askKindWithPrice(SIZE, "Coo...What size would you like?...");
            base = "None";
            milk = askIngredient("Milks", "..Coo. You want milk in that?");

            Steamer drink = new Steamer("Steamer", size, base, milk);
            //set extras
            drink.setTemp(askKind(TEMP,"Would you like your steamer iced or frozen?..."));
            if(drink.getTemp().equals("Hot")){if(askExtras("Coo..do you want to drink it right away?..\n\tI can make it at kid's temperature..180 Degrees..")){drink.setKidsTemp();}}


            if(askExtras("..Coo. Any flavored syrup?..\n\tVanilla is very comforting..")) {
                drink.setSyrup(askIngredient("Syrups","Okay..What flavor?...Coo."));
                //toppings loop
                while (true) {

                    if (askExtras("Any...other flavors?...")){
                        drink.setSyrup(askIngredient("Syrups","What would you also like?...Coo."));
                    }else{break;}
                }

            }

            if(askExtras("..Coo. Any fun toppings?..\n\t..We have so many..")) {
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

            if (askConfirm(drink.displayItem(),"Here is the drink you ordered...\n\tCoo..Does it look right?..")) {
                return drink;
            }

        }

    }

    //food methods
    public static void processOrderFood(){
        systemDialogue(TEAL,MINT,"You want to order something to eat?...Coo.\n\tWhat would you like? We have...");
        do {
            receipt.addToReceipt(buildBagel());
            systemDialogue(TEAL, MINT, "I've added it to your tab..Coo.\n\tAny other bagels?..");
        }while(getYesNo("Type Here"));
    }
    public static Bagel buildBagel(){
        while(true) {
            systemDialogue(BROWN, BEIGE, displayFoodPrice(BAGELS));
            String portionType = BAGELS.get(getUserInt());
            boolean toGo = askExtras("Coo..Do you want that to go?...\n\t......Coo.");
            Bagel bagel = new Bagel("Bagel", toGo, portionType);
            bagel.setToasted(askExtras("Would you like that...toasted?..Coo."));

            if (askConfirm(bagel.displayItem(), "Here is the bagel you ordered...\n\tCoo..Does it look right?..")) {
                return bagel;
            }
        }
    }

    public static void processAddCookie(){
        if(askConfirm("Roost Sablé Shortbread Cookie - $.75","Coo...You would like a cookie?...\n\tI make them myself..")){

            receipt.addToReceipt(new Cookie("Roost Sablé Shortbread Cookie"));
        }
    }

    //Checkout
    public static void checkout(){
        systemDialogue(MINT,BEIGE,receipt.displayReceipt());
        if(askExtras("Does this look correct?...")){
            receipt.saveReceipt();
            exit();
        }else{
            systemDialogue(TEAL,MINT,"Okay...We can start over..Coo.");
            receipt.clearItems();
        }

    }


}
