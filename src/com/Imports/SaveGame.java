package com.Imports;

import com.GameLogic.Game;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Scanner;

public class SaveGame {

    static Game game;

    private static void saveGame() {
        try {
            FileOutputStream ff = new FileOutputStream("src/com/JsonObjects/save.txt");
            ObjectOutputStream oos = new ObjectOutputStream(ff);
            oos.writeObject(game);
            oos.flush();
            oos.close();
            System.out.print("Your game has been saved\n");
        } catch (Exception e) {
            System.out.print("Data cannot be saved.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    private static void loadGame() {
        try {
            FileInputStream ss = new FileInputStream("src/com/JsonObjects/save.txt");
            ObjectInputStream oi = new ObjectInputStream(ss);
            game = (Game) oi.readObject();
            oi.close();
            System.out.print("\n---Game loaded---\n");
        } catch (Exception e) {
            System.out.print("Can't load data.\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) throws IOException, ParseException, UnsupportedAudioFileException, LineUnavailableException {
        BufferedReader in;
        String input;
        String output = "";
        game = new Game();
        in = new BufferedReader(new InputStreamReader(System.in));
        game.commandProcessor();
        do {
            game.look();
            game.detailedLook();
            System.out.println("What what do u wanna do? ");
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            game.runCommand(input);

            System.out.print("> ");

            switch (input) {
                case "save":
                    saveGame();
                    break;
                case "load":
                    loadGame();
                    break;
                default:
                    output = game.runCommand(input);
                    break;
            }
            if (!output.trim().isEmpty()) {
                game.showStr(output);
            }
        } while (!"n".equals(input));
    }

}
