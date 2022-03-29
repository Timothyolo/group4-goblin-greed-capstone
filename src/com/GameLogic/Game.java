package com.GameLogic;
import com.Art.ASCII_Art;
import com.Items.Item;
import com.Players.Player;
import com.Imports.ImportJSON;
import com.Rooms.Room;
import com.Utility.Printer;
import com.Story.Story;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;


// Definition of what is a game
public class Game {
    private Player player;
    ImportJSON assets = new ImportJSON();
    ArrayList<Room> map = (ArrayList<Room>) assets.getMap();
    ArrayList<Player> npcs = (ArrayList<Player>) assets.getNpcs();
    ArrayList<Item> items = (ArrayList<Item>) assets.getItems();
    ASCII_Art artWork = new ASCII_Art();


    // Constructor for an instance of the game
    public Game() throws IOException, ParseException {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    // Method for creating a game
    public boolean beginGame() {
        Scanner in = new Scanner(System.in);
        System.out.println(artWork.title_screen_image());
        System.out.println("Welcome to Goblin's Greed great warrior! What is your name: ");
        String name = in.nextLine();
        Player you = new Player(name,100,15);
        Scanner in2 = new Scanner(System.in);
        System.out.println("Ok, " + you.getName() + " this isn't going to be an easy adventure are you ready?");
        String startGame = in2.nextLine();
        if ("start".equalsIgnoreCase(startGame) || "yes".equalsIgnoreCase(startGame)) {
            setPlayer(you);
            Player player = getPlayer();
            player.setCurrentRoom(map.get(0));
            System.out.println(getPlayer());
            return true;
        }
        else {
            return false;
        }

    }

    //Method for running the game

    public void playGame(Player player1) throws IOException, ParseException, InterruptedException {
        player1.setItems(player1.getItems());
        System.out.println(player1.getName() + " is at the " + player1.getCurrentRoom().getName());
        System.out.println(player1.getCurrentRoom().getDesc());
        Scanner in = new Scanner(System.in);
        System.out.println("What you would like to do?");
        System.out.println("Type 'help' for more information");
        String[] location = in.nextLine().split(" ");
        try {
            if ("quit".equalsIgnoreCase(location[0])) {
                System.out.println("Thanks for playing!");
                System.exit(130);
            } else if ("help".equalsIgnoreCase(location[0])){
                System.out.println(Story.tutorial());

            } else if (location.length != 2) {
                System.out.println("If you are not 'quit'ing the game, you need 2 inputs of a verb and noun\n like 'look' or 'get', then the noun you want to interact with.\n");
            } else if ("go".equalsIgnoreCase(location[0])) {
                PlayerMechanics.moveRoom(location[1], this);
            } else if ("look".equalsIgnoreCase(location[0]) && "around".equalsIgnoreCase(location[1]) || "room".equalsIgnoreCase(location[1])) {
                PlayerMechanics.lookAround(this);
            }
            else if("look".equalsIgnoreCase(location[0]) && "map".equalsIgnoreCase(location[1])){
                PlayerMechanics.lookAtMap(this);
            }else if ("look".equalsIgnoreCase(location[0])) {
                PlayerMechanics.lookItem(location[1], player1.getCurrentRoom().getItems(),player1.getItems());
            } else if ("get".equalsIgnoreCase(location[0])) {
                PlayerMechanics.getItem(location[1], player1.getCurrentRoom().getItems(), player1.getItems());
            } else if ("check".equalsIgnoreCase(location[0]) && "inventory".equalsIgnoreCase(location[1])) {
                PlayerMechanics.checkInventory(getPlayer());
            } else {
                System.out.println("Invalid input, your action are 'go' to a location and 'look' to see what is around");
                playGame(player1);
            }
        } catch (IndexOutOfBoundsException e) {
            playGame(player1);
        }
    }

}
