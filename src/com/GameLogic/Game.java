package com.GameLogic;
import WorkingFiles.MyGui;
import com.Players.Player;
import com.Imports.ImportJSON;
import com.Rooms.Room;
import com.Utility.Printer;
import com.Story.Story;
import com.Utility.TextParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;


// Definition of what is a game
public class Game {
    private Player player;
    private TextParser parser;
    private MyGui gui;

    ArrayList<Room> map = (ArrayList<Room>) ImportJSON.getMap();


    // Constructor for an instance of the game
    public Game() throws IOException, ParseException {
        gui = new MyGui();
        parser = new TextParser();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }




    // Method for creating a game
    /*public boolean beginGame() throws IOException, InterruptedException {
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

    }*/

    //gui version
    public boolean beginGame() throws IOException, InterruptedException {
        Printer.print(Story.beginGameText());

        //String name = MyGui.InputTextHandler;
        Player you = new Player("Test",100,15);

        //Scanner in2 = new Scanner(System.in);
        //System.out.println("Ok, " + you.getName() + " this isn't going to be an easy adventure are you ready? (yes/no)");
        //String startGame = in2.nextLine();
        //if ("start".equalsIgnoreCase(startGame) || "yes".equalsIgnoreCase(startGame) || "y".equalsIgnoreCase(startGame)) {
            setPlayer(you);
            Player player = getPlayer();
            player.setCurrentRoom(map.get(0));
            System.out.println(getPlayer());
            gui.outputTextArea(player.toString());
            return true;
        //}
        //else {
        //    return false;
        //}

    }

    //Method for running the game

    public void playGame(Player player1) throws IOException, ParseException, InterruptedException {
        player1.setItems(player1.getItems());
        System.out.println("\n" + player1.getName() + " is at the " + player1.getCurrentRoom().getName());
        gui.outputTextArea(player1.getName() + " is at the " + player1.getCurrentRoom().getName());

        System.out.println(player1.getCurrentRoom().getDesc());
        gui.outputTextArea(player1.getCurrentRoom().getDesc());

        Scanner in = new Scanner(System.in);

        Printer.print(Story.promptPlayerMessage());
        gui.outputTextArea(Story.promptPlayerMessage());

        String[] location = in.nextLine().split(" ");
        List<String> validCommand = parser.ParseCommand(location);
        commandProcessor(validCommand, player1);
        //send String[] location to TextParser to validate command
        //ImportJSON will parse CommandList - store in ArrayList verbList
        //TextParser check if location[0] is contained in verbList, then process the noun based on the verb

        /*try {
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
        }*/
    }

    public void commandProcessor(List<String> validCommand, Player player1) throws IOException, InterruptedException, ParseException {

        try {
            if (validCommand.get(0).equals("go")){
                //move engine
                PlayerMechanics.moveRoom(validCommand.get(1), this);
            }
            else if (validCommand.get(0).equals("get")) {
                //get engine
                PlayerMechanics.getItem(validCommand.get(1), player1.getCurrentRoom().getItems(), player1.getItems());
            }
            else if (validCommand.get(0).equals("look")) {
                //look engine
                if (validCommand.get(1).equals("around")) {
                    PlayerMechanics.lookAround(this);
                }
                else if (validCommand.get(1).equals("map")) {
                    PlayerMechanics.lookAtMap(this);
                }
                else if (validCommand.get(1).equals("inventory")) {
                    PlayerMechanics.checkInventory(getPlayer());
                }
                else if (validCommand.get(1).equals("stats")) {
                    //help engine
                    PlayerMechanics.stats(getPlayer());
                }
                else {
                    PlayerMechanics.lookItem(validCommand.get(1), player1.getCurrentRoom().getItems(),player1.getItems());
                }
            }
            else if (validCommand.get(0).equals("quit")) {
                //quit engine
                Printer.print(Story.quitMessage());
                MyGui.outputTextArea(Story.quitMessage());

                System.exit(130);
            }
            else if (validCommand.get(0).equals("help")) {
                //help engine
                Printer.print(Story.tutorial());
                MyGui.outputTextArea(Story.tutorial());

            }
            else if (validCommand.get(0).equals("attack")) {
                //help engine
                //check player's room if monster is available
                try {
                    String enemy = player1.getCurrentRoom().getEnemy().getName().toLowerCase();
                    if (validCommand.get(1).equals(enemy)){
                        BattleMechanics.fight(validCommand.get(1), getPlayer());
                    }
                    else {
                        Printer.print(Story.invalidEntryMessage2());
                        MyGui.outputTextArea(Story.invalidEntryMessage2());

                    }
                } catch (NullPointerException e) {
                    Printer.print(Story.invalidEntryMessage2());
                    MyGui.outputTextArea(Story.invalidEntryMessage2());

                }

            }
            else if (validCommand.get(0).equals("drop")) {
                //help engine
                PlayerMechanics.dropItem(validCommand.get(1), player1.getCurrentRoom().getItems(), player1.getItems());
            }
            else if (validCommand.get(0).equals("equip")) {
                //help engine
                if(PlayerMechanics.checkInstance(getPlayer(),validCommand.get(1))) {
                    PlayerMechanics.equipWeapon(getPlayer(),validCommand.get(1));
                } else {
                    PlayerMechanics.equipArmor(getPlayer(),validCommand.get(1));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            playGame(player1);
        }
    }


    public void commandProcessor() {
    }

    public void look() {
    }

    public void detailedLook() {
    }

    public String runCommand(String input) {
        return input;
    }

    public void showStr(String output) {
    }
}
