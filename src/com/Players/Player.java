package com.Players;

import com.Items.Item;
import com.Rooms.Room;

import java.util.ArrayList;
import java.util.Collection;


public abstract class Player {
    public int hp;
    public int attack;
    public Collection<Item> items = new ArrayList<>();
    public Room currentRoom;
    public String name;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
