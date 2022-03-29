package com.GameLogic;

import com.Items.Item;
import com.Players.Player;
import com.Rooms.Room;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerMechanics {

    public static void moveRoom(String location, Game game) throws IOException, ParseException, InterruptedException {
        Player player = game.getPlayer();
        List<Room> rooms = game.map.stream().filter(room -> room.getName().equalsIgnoreCase(location)).collect(Collectors.toList());
        if (rooms.size() == 0) {
            System.out.println("There is no room of that name.");
            game.playGame(game.getPlayer());
        }
        player.setCurrentRoom(rooms.get(0));
    }

    public static void lookAround(Game game){
        Player player = game.getPlayer();
        Room currentRoom = player.getCurrentRoom();
        System.out.println("You see: \n");
        ArrayList<Item> roomItems = (ArrayList<Item>) currentRoom.getItems();
        for(int x=0; x< currentRoom.getItems().size(); x++){
            Item s = roomItems.get(x);
            System.out.println(s.getName());
        }

    }

    public static void lookItem(String item, Collection<Item> roomItems, Collection<Item> playerItems){
        List<Item> itemsInInventory = playerItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        if(itemsInInventory.size() > 0) {
            Item lookedAt = itemsInInventory.get(0);
            System.out.println("This is a " + lookedAt.getName()+"." + lookedAt.getDesc());
        }
        List<Item> itemToLookAt = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item lookedAt = itemToLookAt.get(0);
        System.out.println("This is a " + lookedAt.getName()+"." + lookedAt.getDesc());
    }




    public static void getItem(String item, Collection<Item> roomItems, Collection<Item> playerItems) {
        List<Item> itemToGrab = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item taken = itemToGrab.get(0);
        roomItems.remove(taken);
        playerItems.add(taken);
        System.out.println("You picked up the " + taken.getName() + "!");
    }

    public static void checkInventory(Player player1) throws IOException, ParseException {
        for (Item item: player1.getItems()
        ) {
            if (player1.getItems().size() == 0) {
                System.out.println("You have no items in your inventory");
            } else {
                System.out.println(item.getName());
            }

        }

    }

    public static void lookAtMap(Game game) {
        for (Room room: game.map){
            System.out.println(room.getName());
        }
    }


}
