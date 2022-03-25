package com.Rooms;

import com.Items.Item;

import java.util.ArrayList;
import java.util.Collection;

public class Room {
  private String name;
  private String desc;
  private Collection<Item> items = new ArrayList<>();

  public Room(String name, String desc) {
      setName(name);
      setDesc(desc);
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

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }


    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
