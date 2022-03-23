package com.Rooms;

import com.Items.Item;
import com.Items.Key;
import com.Items.Sword;
import com.Players.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Entrance extends BaseRoom{

    public Entrance(Player player) {
        items.add(new Sword());
        items.add(new Key());
        System.out.println(player.getName() + " has walked through the entrance.");

        players.add(player);
    }

    public Item releaseItem(String itemToRelease ) {
        List<Item> itemsInRoom = items.stream().filter(item -> item.getName() == itemToRelease).collect(Collectors.toList());
        System.out.println("You found a: " + itemsInRoom.get(0).getName());
        return itemsInRoom.get(0);
    }


}
