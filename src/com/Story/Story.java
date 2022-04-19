package com.Story;

import com.Art.ASCII_Art;

public class Story {

    public static String beginGameText(){
        return "\nWELCOME TO GOBLIN's GREED, GREAT WARRIOR! PLEASE TELL US YOUR NAME: ";
    }



    public static String tutorial(){
        return "Tutorial: The standard command verbs are 'GO', 'GET', 'LOOK', 'ATTACK', 'EQUIP', \n" +
                "'DROP', 'HELP', and 'QUIT'. You can also try different synonyms such as \n" +
                "'VIEW', 'GRAB', 'MOVE' or 'FIGHT'.\n" +
                "Type 'GO [room name]' to go to the rooms in the game.\n" +
                "Type 'LOOK MAP' to look at your map and get a list of available rooms.\n" +
                "When in a room you can type 'LOOK AROUND' to see the items in the room.\n" +
                "Then you may type 'GET [item name]' to add that item to your inventory.\n" +
                "As the player you can also type 'LOOK INVENTORY' to see what you have.\n" +
                "Remove items from your inventory by typing 'DROP [item name]'.\n"+
                "Equip weapon or armor from your inventory by typing 'EQUIP [item name]'.\n"+
                "Type 'LOOK STATS' to see your current status.\n"+
                "If you see an enemy in the room type 'ATTACK' to start a battle.\n"+
                "Quit the game at anytime by typing 'QUIT'.\n";
    }


    public static String promptPlayerMessage(){
        return "What you would like to do?\n" +
                "Type 'help' for more information\n" +
                ASCII_Art.text_Spacer();
    }

    public static String quitMessage(){
        return "Thanks for playing!";
    }

    public static String invalidEntryMessage1(){
        return "If you are not 'QUIT'ing the game, you need type two words: a verb and a noun.\n" +
                "Like 'LOOK' or 'GET', then the noun you want to interact with.";
    }

    public static String invalidEntryMessage2(){
        return "Invalid input, your actions should be verbs such as 'GO' to a location, 'LOOK' to see what is around, 'GET' items, or 'ATTACK' monster.";
    }

    public static String noRoomMessage(){
        return "There is no room of that name.";
    }

    public static String nothingInInventory(){
        return "You have no items in your inventory";
    }

}
