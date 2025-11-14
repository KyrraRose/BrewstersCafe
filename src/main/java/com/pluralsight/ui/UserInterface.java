package com.pluralsight.ui;

import com.pluralsight.model.Inventory;

import static com.pluralsight.ui.MenuOptions.*;
import static com.pluralsight.ui.MenuUtility.*;
import static com.pluralsight.ui.OrderSystem.*;


public class UserInterface {


    public void displayWelcome(){
        systemDialogue(TEAL,MINT,WELCOME_MESSAGE);
        pressEnter();
    }
    public void displayMainMenu(){
        systemDialogue(BROWN,BEIGE,displayMenuWithBack(MAIN));
    }
    public void handleMainSelection(){
        switch(getUserInt()){
            case 1 -> {
                while(true) {
                    displayOrderMenu();
                    handleMenuSelection();
                }
            }
            case 0 -> exit() ;
        }
    }
    public void displayOrderMenu(){
        systemDialogue(TEAL,MINT,"You're placing an order?...Coo.\n\t....What would you like?...");
        systemDialogue(BROWN,BEIGE,displayMenuWithBack(ORDER));
    }


    public void handleMenuSelection(){
        switch(getUserInt()){
            case 1 ->{
                processOrderDrink();
                pressEnter();
            }
            case 2 -> {
                systemDialogue(TEAL,MINT,"You are hungry?...We have bagels...Coo.");
                processOrderFood();
                pressEnter();
            }
            case 3 ->{
                processAddCookie();
                pressEnter();
            }
            case 4 ->{
                checkout();
                pressEnter();
            }
            case 0 ->{ exit();}
        }
    }
    public void run(){
       displayWelcome();
       displayMainMenu();
       handleMainSelection();

    }

}
