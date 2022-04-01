package com.Rooms;

import com.Items.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.Imports.ImportJSON;
import com.Players.Player;
import org.json.simple.parser.ParseException;

public class Room {
  private String name;
  private String desc;
  private Collection<Item> items = new ArrayList<>();
  private ArrayList<Player> enemies = (ArrayList<Player>) ImportJSON.getNpcs();
  private Player enemy = enemies.get(getRandomNumber(0,enemies.size()));


  ArrayList<Item> roomItems = (ArrayList<Item>) ImportJSON.getItems();
  ArrayList<Item> weapons = (ArrayList<Item>) ImportJSON.getWeapons();
  ArrayList<Item> armor = (ArrayList<Item>) ImportJSON.getArmor();


  public int getRandomNumber(int min, int max) {
      return (int) ((Math.random() * (max - min)) + min);
  }

  public Room(String name, String desc) throws IOException, ParseException {
      setName(name);
      setDesc(desc);
      generateItems();
  }

  public void generateItems(){
      roomItems.addAll(weapons);
      roomItems.addAll(armor);
      for (int i=0; i<4; i++) {
          addItem(roomItems.get(getRandomNumber(0,roomItems.size())));
      }

  }

    public Player getEnemy() {
        return enemy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public void defeatEnemy() {
      setEnemy(null);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Collection<Item> getItems() {
        return items;
    }



    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
