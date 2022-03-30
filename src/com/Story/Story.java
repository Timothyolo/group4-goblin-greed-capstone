package com.Story;

import com.Art.ASCII_Art;

public class Story {

    public static String beginGameText(){
        return ASCII_Art.title_screen_image() + "\nWelcome to Goblin's Greed great warrior! What is your name: ";
    }



    public static String tutorial(){
        return "Tutorial: Type 'GO [room name]' to go to the rooms in the game.\n" +
                "Type 'LOOK MAP' to look at your map and get a list of available rooms.\n" +
                "When in a room you can type 'LOOK AROUND' to see the items in the room.\n" +
                "Then you may type 'GET [item name]' to add that item to your inventory.\n" +
                "As the player you can also type 'CHECK INVENTORY' to see what you have.";
    }


    public static String promptPlayerMessage(){
        return "What you would like to do?\n" +
                "Type 'help' for more information\n" +
                "________________________________________________________________________________________________________";
    }

    public static String quitMessage(){
        return "Thanks for playing!";
    }

    public static String invalidEntryMessage1(){
        return "If you are not 'QUIT'ing the game, you need type two words: a verb and a noun.\n" +
                "Like 'LOOK' or 'GET', then the noun you want to interact with.";
    }

    public static String invalidEntryMessage2(){
        return "Invalid input, your action are 'GO' to a location, 'LOOK' to see what is around, and 'GET' items.";
    }

    public static String noRoomMessage(){
        return "There is no room of that name.";
    }

    public static String nothingInInventory(){
        return "You have no items in your inventory";
    }

}
