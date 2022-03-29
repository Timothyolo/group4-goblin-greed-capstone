package com.GameLogic;
import com.Art.ASCII_Art;
import com.Items.Item;
import com.Players.Player;
import com.Imports.ImportJSON;
import com.Rooms.Room;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    private Player player;
    ImportJSON assets = new ImportJSON();
    ArrayList<Room> map = (ArrayList<Room>) assets.getMap();
    ArrayList<Player> npcs = (ArrayList<Player>) assets.getNpcs();
    ArrayList<Item> items = (ArrayList<Item>) assets.getItems();
    ASCII_Art artWork = new ASCII_Art();

    public Game() throws IOException, ParseException {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


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

    public void playGame(Player player1) throws IOException, ParseException {
        System.out.println(player1.getName() + " is at the " + player1.getCurrentRoom().getName());
        System.out.println(player1.getCurrentRoom().getDesc());
        Scanner in = new Scanner(System.in);
        System.out.println("What you would like to do?");
        System.out.println("Tutorial: You can 'go' to the rooms in the game and when in a room you can 'look around' to see the items in the room and then you may 'get' that specific item.\nAs the player you can also 'check inventory' to see what you have.");
        String[] location = in.nextLine().split(" ");
        if("quit".equalsIgnoreCase(location[0])){
            System.out.println("Thanks for playing!");
            System.exit(130);
        }
        else if(location.length != 2){
            System.out.println("If you are not 'quit'ing the game, you need 2 inputs of a verb and noun\n like 'look' or 'get', then the noun you want to interact with.\n");
        }
        else if("go".equalsIgnoreCase(location[0])){
            moveRoom(location[1]);
        }
        else if("look".equalsIgnoreCase(location[0]) && "around".equalsIgnoreCase(location[1]) || "room".equalsIgnoreCase(location[1])){
            lookAround();
        }
        else if("look".equalsIgnoreCase(location[0])){
            lookItem(location[1],player1.getCurrentRoom().getItems());
        }
        else if("get".equalsIgnoreCase(location[0])) {
            getItem(location[1],player1.getCurrentRoom().getItems(),player1.getItems());
        }
        else if("check".equalsIgnoreCase(location[0]) && "inventory".equalsIgnoreCase(location[1])) {
            for (Item item: player.getItems()
                 ) {
                System.out.println(item);

            }
        }

        else{
            System.out.println("Invalid input, your action are 'go' to a location and 'look' to see what is around");
            playGame(player1);
        }
    }

    public void moveRoom(String location) {
        Player player = getPlayer();
        List<Room> rooms = map.stream().filter(room -> room.getName().equalsIgnoreCase(location)).collect(Collectors.toList());
        player.setCurrentRoom(rooms.get(0));

    }

    public void lookAround(){
        Player player = getPlayer();
        Room currentRoom = player.getCurrentRoom();
        System.out.println("You see: \n");
        ArrayList<Item> roomItems = (ArrayList<Item>) currentRoom.getItems();
        for(int x=0; x< currentRoom.getItems().size(); x++){
            Item s = roomItems.get(x);
            System.out.println(s.getName());
        }

    }

    public void lookItem(String item, Collection<Item> roomItems){
        List<Item> itemToLookAt = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item lookedAt = itemToLookAt.get(0);
        System.out.println("This is a " + lookedAt.getName()+"." + lookedAt.getDesc());

    }




    public void getItem(String item, Collection<Item> roomItems, Collection<Item> playerItems) {
        List<Item> itemToGrab = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item taken = itemToGrab.get(0);
        roomItems.remove(taken);
        playerItems.add(taken);
        System.out.println("You picked up the " + taken.getName() + "!");

    }

}
