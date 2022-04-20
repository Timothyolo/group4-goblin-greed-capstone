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

    private static String text;
    private static boolean continueGame;

    ArrayList<Room> map = (ArrayList<Room>) ImportJSON.getMap();

    // Constructor for an instance of the game
    public Game() throws IOException, ParseException {

        parser = new TextParser();
        text = "";
        continueGame = false;

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    //gui version
    public void beginGame() throws IOException, InterruptedException, ParseException {
        Printer.print(Story.beginGameText());
        MyGui.outputTextArea(Story.beginGameText());



                String name = MyGui.requestInput();
                Player you = new Player(name, 50, 15);

                setPlayer(you);
                Player player = getPlayer();
                player.setCurrentRoom(map.get(0));
                System.out.println(getPlayer());
                MyGui.outputTextArea(player.toString());

                playGame(player);


    }


    //Method for running the game
    public void playGame(Player player1) throws IOException, ParseException, InterruptedException {
        //continueGame = false;
        System.out.println("Playing game...");
        while (true) {
            //if (continueGame()) {
                //System.out.println("Playing game...");
                player1.setItems(player1.getItems());
                System.out.println("\n" + player1.getName() + " is at the " + player1.getCurrentRoom().getName());
                MyGui.outputTextArea(player1.getName() + " is at the " + player1.getCurrentRoom().getName());

                System.out.println(player1.getCurrentRoom().getDesc());
                MyGui.outputTextArea(player1.getCurrentRoom().getDesc());

                //Scanner in = new Scanner(System.in);

                Printer.print(Story.promptPlayerMessage());
                MyGui.outputTextArea(Story.promptPlayerMessage());

                //String[] location = in.nextLine().split(" ");
                //String[] location = text.split(" ");
                String[] location = MyGui.requestInput().split(" ");
                //while (continueGame) {
                List<String> validCommand = parser.ParseCommand(location);

                //    Arrays.fill( location, null );
                commandProcessor(validCommand, player1);

       }

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
                Thread.sleep(1000);
                System.exit(0);
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
            else if (validCommand.get(0).equals("use")) {
                if (validCommand.get(1).equals("potion") || validCommand.get(1).equals("bread") || validCommand.get(1).equals("wine")) {
                    PlayerMechanics.healPlayer(getPlayer(), validCommand.get(1));
                }
                else {
                    MyGui.outputTextArea("You cannot use this item.");
                }
            }
            /*else if (validCommand.get(0).equals("heal")) {

                PlayerMechanics.healPlayer(getPlayer(), "");
            }*/
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
