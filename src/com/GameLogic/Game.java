package com.GameLogic;
import com.Art.ASCII_Art;
import com.Items.Item;
import com.Players.Player;
import com.Imports.ImportJSON;
import com.Rooms.Room;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
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
        if ("start".equalsIgnoreCase(startGame)) {
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
        System.out.println("Enter 'go' and the room name to go to the room ex: 'go Corridor'");
        String[] location = in.nextLine().split(" ");
        if("quit".equalsIgnoreCase(location[0])){
            System.out.println("Thanks for playing!");
            System.exit(130);
        }
        else if("go".equalsIgnoreCase(location[0])){
            moveRoom(location[1]);
        }
        else if("look".equalsIgnoreCase(location[0])){
            lookAround();
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
            System.out.println(roomItems.get(x));
        }


    }

}
