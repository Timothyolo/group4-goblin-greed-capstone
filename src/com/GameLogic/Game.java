package com.GameLogic;
import com.Art.ASCII_Art;
import com.Items.Item;
import com.Players.Player;
import com.Imports.ImportJSON;
import com.Rooms.Room;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private Player player;
    ImportJSON assets = new ImportJSON();
    ArrayList<Room> map = (ArrayList<Room>) assets.getMap();
    ArrayList<Player> npcs = (ArrayList<Player>) assets.getNpcs();
    ArrayList<Item> items = (ArrayList<Item>) assets.getItems();
    ASCII_Art artWork = new ASCII_Art();
    private Collection<Item> currentItems = new ArrayList<>();

    public Collection<Item> getCurrentItems() {
        return currentItems;
    }

    public void setCurrentItems(Collection<Item> currentItems) {
        this.currentItems = currentItems;
    }

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
        player1.setItems(player1.getItems());
        System.out.println(player1.getName() + " is at the " + player1.getCurrentRoom().getName());
        System.out.println(player1.getCurrentRoom().getDesc());
        Scanner in = new Scanner(System.in);
        System.out.println("What you would like to do?");
        System.out.println("Type 'help' for more infomration");
        String[] location = in.nextLine().split(" ");
        try {
            if ("quit".equalsIgnoreCase(location[0])) {
                System.out.println("Thanks for playing!");
                System.exit(130);
            } else if ("help".equalsIgnoreCase(location[0])){
                System.out.println("Tutorial: Type 'GO [room name]' to go to the rooms in the game. Type 'LOOK MAP' to look at your \n" +
                        "map and get a list of available rooms. When in a room you can type 'LOOK AROUND' to \n" +
                        "see the items in the room and then you may type 'GET [item name]' to add that item to your inventory. As the player \n" +
                        "you can also type 'CHECK INVENTORY' to see what you have.");

            } else if (location.length != 2) {
                System.out.println("If you are not 'quit'ing the game, you need 2 inputs of a verb and noun\n like 'look' or 'get', then the noun you want to interact with.\n");
            } else if ("go".equalsIgnoreCase(location[0])) {
                moveRoom(location[1]);
            } else if ("look".equalsIgnoreCase(location[0]) && "around".equalsIgnoreCase(location[1]) || "room".equalsIgnoreCase(location[1])) {
                lookAround();
            }
            else if("look".equalsIgnoreCase(location[0]) && "map".equalsIgnoreCase(location[1])){
                lookAtMap();
            }else if ("look".equalsIgnoreCase(location[0])) {
                lookItem(location[1], player1.getCurrentRoom().getItems(),player1.getItems());
            } else if ("get".equalsIgnoreCase(location[0])) {
                getItem(location[1], player1.getCurrentRoom().getItems(), player1.getItems());
            } else if ("check".equalsIgnoreCase(location[0]) && "inventory".equalsIgnoreCase(location[1])) {
                checkInventory(getPlayer());
            } else {
                System.out.println("Invalid input, your action are 'go' to a location and 'look' to see what is around");
                playGame(player1);
            }
        } catch (IndexOutOfBoundsException e) {
            playGame(player1);
        }
    }

    public void moveRoom(String location) throws IOException, ParseException {
        Player player = getPlayer();
        List<Room> rooms = map.stream().filter(room -> room.getName().equalsIgnoreCase(location)).collect(Collectors.toList());
        if (rooms.size() == 0) {
            System.out.println("There is no room of that name.");
            playGame(getPlayer());
        }
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

    public void lookItem(String item, Collection<Item> roomItems, Collection<Item> playerItems){
        List<Item> itemsInInventory = playerItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        if(itemsInInventory.size() > 0) {
            Item lookedAt = itemsInInventory.get(0);
            System.out.println("This is a " + lookedAt.getName()+"." + lookedAt.getDesc());
        }
        List<Item> itemToLookAt = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item lookedAt = itemToLookAt.get(0);
        System.out.println("This is a " + lookedAt.getName()+"." + lookedAt.getDesc());

    }




    public void getItem(String item, Collection<Item> roomItems, Collection<Item> playerItems) {
        List<Item> itemToGrab = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item taken = itemToGrab.get(0);
        roomItems.remove(taken);
        playerItems.add(taken);
        setCurrentItems(getPlayer().getItems());
        System.out.println("You picked up the " + taken.getName() + "!");

    }

    public boolean checkInventory(Player player1) throws IOException, ParseException {
        player1.setItems(getCurrentItems());
        for (Item item: player1.getItems()
        ) {
            if (player1.getItems().size() == 0) {
                System.out.println("You have no items in your inventory");
                return true;
            } else {
                System.out.println(item.getName());
            }

        }
        return true;

    }

    public void lookAtMap() {
        for (Room room: map){
            System.out.println(room.getName());
        }
    }

}
