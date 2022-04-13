package com.Imports;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Music {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        File file = new File("src/com/JsonObjects/group4.wav");
        //Music path
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/com/JsonObjects/group4.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);


        String response = "";


        while (!response.equals("Q")) {
            System.out.println("P = play, S = stop, Q = Quit");
            System.out.println("Enter your choice: ");

            response = scanner.next();
            response = response.toUpperCase();

            switch (response) {
                case ("P"):
                    clip.start();
                    break;
                case ("S"):
                    clip.stop();
                    break;
                case ("Q"):
                    clip.close();
                    break;
                default:
                    System.out.println("Invalid input");

            }
        }

    }
}