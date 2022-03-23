package com.Rooms;

import java.util.ArrayList;
import java.util.Collection;
import com.Players.Player;
import com.Items.Item;

public abstract class BaseRoom {
    public Collection<Player> players = new ArrayList<>();
    public Collection<Item> items = new ArrayList<>();

}
