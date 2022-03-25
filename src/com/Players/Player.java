package com.Players;

import com.Items.Item;
import com.Rooms.Room;

import java.util.ArrayList;
import java.util.Collection;


public class Player {
    private String name;
    private long hp;
    private long attack;
    private Collection<Item> items = new ArrayList<>();
    private Room currentRoom;


    public Player(String name, long hp, long attack) {
        setName(name);
        setHp(hp);
        setAttack(attack);
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public long getAttack() {
        return attack;
    }

    public void setAttack(long attack) {
        this.attack = attack;
    }

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


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                '}';
    }
}
