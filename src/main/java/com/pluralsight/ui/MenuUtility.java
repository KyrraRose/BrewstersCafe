package com.pluralsight.ui;

import java.util.*;

public class MenuUtility {
    //I looked up constants after borrowing some of these from the advanced VIS system
    private static final int INVALID_INPUT = -1;
    public static final String WELCOME_MESSAGE = "\tWelcome to The Roost!\n\tMy name is Brewster....\n\tWhat can I get started for you?...Coo.";
    private static final String RETURN_TO_MENU_MESSAGE = "Press [ENTER] to return to menu";
    private static final String EXIT_MESSAGE = "Thank you...for visiting The Roost!\n\tCome back soon...Coo.";
    private static final Scanner scanner = new Scanner(System.in);

    //console colors! "\033[38;2;<R>;<G>;<B>m"
    public static final String RESET = "\033[0m";

    public static final String TEAL = "\033[38;2;0;128;128m";
    public static final String BROWN = "\033[38;2;111;78;55m";
    public static final String BEIGE = "\033[38;2;245;245;220m";
    public static final String MINT = "\033[38;2;152;255;152m";

    //Borders
    public static final String BORDER_T = "╔═══════════ .✾. ════════════════════ THE ROOST ═════════════════════════════════════════╗";
    public static final String BORDER_B = "╚════════════════════════════════════════════════════════════════════════ .✾. ═══════════╝\n";
    public static final String ALT_BORDER_T = "╔══════ .✾. ════════════════ THE ROOST ═════════════╗";
    public static final String ALT_BORDER_B = "╚════════════════════════════════════════ .✾. ══════╝\n";
    public static void border(String border,String color){
        System.out.printf("%s%s%s%n",color,border,MenuUtility.RESET);
    }

    public static void systemDialogue(String borderColor,String textColor, String message){
        border(ALT_BORDER_T,borderColor);
        System.out.printf("%s\t%s %n",textColor,message);
        border(ALT_BORDER_B,borderColor);
    }
    public static void systemDialogueLarge(String borderColor,String textColor, String message){
        border(BORDER_T,borderColor);
        System.out.printf("%s\t"+message+"%n",textColor);
        border(BORDER_B,borderColor);
    }


    public static int getUserInt(){

        System.out.printf("%sType Here:%s ",BEIGE,MenuUtility.RESET);
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_INPUT;
        }
    }
    public static String getUserString(String prompt){
        System.out.printf("%s%s%s ",BEIGE,prompt,MenuUtility.RESET);
        return scanner.nextLine().trim();
    }
    public static boolean getYesNo(String prompt){
        String input = getUserString(String.format("%s %s(yes/no):%s ",BEIGE,prompt,MenuUtility.RESET));
        boolean yesNo = false;
        switch(input){case "Yes","Y","y","yes","yeah","yep","yea","ok","alright"->yesNo=true;}
        return yesNo;
    }
    public static void pressEnter() {
        System.out.println("\n" + RETURN_TO_MENU_MESSAGE);
        scanner.nextLine();
    }

    public static void exit() {
        System.out.printf("%s%s%n\t%s%s%n%s%s%n",TEAL,ALT_BORDER_T,MINT,EXIT_MESSAGE,TEAL,ALT_BORDER_B);
        System.out.flush();
        scanner.close();
        System.exit(0);
    }

    private MenuUtility() {
        throw new AssertionError("Cannot instantiate constants class");
    }

}
