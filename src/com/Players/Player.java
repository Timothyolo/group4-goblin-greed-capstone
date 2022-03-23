package com.Players;

import com.Items.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    public int hp;
    public int attack;
    public Collection<Item> items = new ArrayList<>();

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
