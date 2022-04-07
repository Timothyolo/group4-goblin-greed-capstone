package com.GameLogic;
import com.Items.Item;
import com.Players.Player;
import com.Imports.ImportJSON;
import com.Rooms.Room;
import com.Utility.Printer;
import com.Story.Story;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;


// Definition of what is a game
public class Game {
    private Player player;

    ArrayList<Room> map = (ArrayList<Room>) ImportJSON.getMap();


    // Constructor for an instance of the game
    public Game() throws IOException, ParseException {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }




    // Method for creating a game
    public boolean beginGame() throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        Printer.print(Story.beginGameText());
        String name = in.nextLine();
        Player you = new Player(name,100,15);
        Scanner in2 = new Scanner(System.in);
        System.out.println("Ok, " + you.getName() + " this isn't going to be an easy adventure are you ready? (yes/no)");
        String startGame = in2.nextLine();
        if ("start".equalsIgnoreCase(startGame) || "yes".equalsIgnoreCase(startGame) || "y".equalsIgnoreCase(startGame)) {
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

    //Method for running the game

    public void playGame(Player player1) throws IOException, ParseException, InterruptedException {
        player1.setItems(player1.getItems());
        System.out.println("\n" + player1.getName() + " is at the " + player1.getCurrentRoom().getName());
        System.out.println(player1.getCurrentRoom().getDesc());
        Scanner in = new Scanner(System.in);
        Printer.print(Story.promptPlayerMessage());
        String[] location = in.nextLine().split(" ");
        try {
            if ("quit".equalsIgnoreCase(location[0])) {
                Printer.print(Story.quitMessage());
                System.exit(130);
            } else if ("help".equalsIgnoreCase(location[0]) || "h".equalsIgnoreCase(location[0])) {
                Printer.print(Story.tutorial());

            } else if ("stats".equalsIgnoreCase(location[0])) {
                PlayerMechanics.stats(getPlayer());
            }
            else if (location.length != 2) {
                Printer.print(Story.invalidEntryMessage1());
            } else if ("go".equalsIgnoreCase(location[0])) {
                PlayerMechanics.moveRoom(location[1], this);
            }  else if ("attack".equalsIgnoreCase(location[0])) {
                BattleMechanics.fight(location[1],getPlayer());
            }else if ("look".equalsIgnoreCase(location[0]) && "around".equalsIgnoreCase(location[1]) || "room".equalsIgnoreCase(location[1])) {
                PlayerMechanics.lookAround(this);
            }
            else if("look".equalsIgnoreCase(location[0]) && "map".equalsIgnoreCase(location[1])){
                PlayerMechanics.lookAtMap(this);
            }else if ("look".equalsIgnoreCase(location[0])) {
                PlayerMechanics.lookItem(location[1], player1.getCurrentRoom().getItems(),player1.getItems());
            } else if ("get".equalsIgnoreCase(location[0])) {
                PlayerMechanics.getItem(location[1], player1.getCurrentRoom().getItems(), player1.getItems());
            } else if ("drop".equalsIgnoreCase(location[0])) {
                PlayerMechanics.dropItem(location[1], player1.getCurrentRoom().getItems(), player1.getItems());
            }
            else if ("equip".equalsIgnoreCase(location[0])) {
                if(PlayerMechanics.checkInstance(getPlayer(),location[1])) {
                PlayerMechanics.equipWeapon(getPlayer(),location[1]);
                } else {
                    PlayerMechanics.equipArmor(getPlayer(),location[1]);
                }
            }else if ("check".equalsIgnoreCase(location[0]) && "inventory".equalsIgnoreCase(location[1])) {
                PlayerMechanics.checkInventory(getPlayer());
            } else {
                Printer.print(Story.invalidEntryMessage2());
                playGame(player1);
            }
        } catch (IndexOutOfBoundsException e) {
            playGame(player1);
        }
    }

}
