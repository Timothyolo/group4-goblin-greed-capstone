package com.Rooms;

import java.util.ArrayList;
import java.util.Collection;
import com.Players.Player;
import com.Items.Item;
import com.Art.ASCII_Art;

public class Room {
    private Collection<Item> items = new ArrayList<>();
    private Player player;
    private ASCII_Art artwork;
    private int north, south, west, east;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ASCII_Art getArtwork() {
        return artwork;
    }

    public void setArtwork(ASCII_Art artwork) {
        this.artwork = artwork;
    }


    @Override
    public String toString() {
        return "Room{" +
                "items=" + items +
                ", player=" + player +
                ", artwork=" + artwork +
                '}';
    }
}
