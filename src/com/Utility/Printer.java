package com.Utility;

import java.io.IOException;

public class Printer {

//    public static void clearScreen() throws IOException, InterruptedException {
//        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//    }


    public static void print(String text) throws IOException, InterruptedException {
        System.out.println("\n");
        System.out.println(text);
    }

    public static String printJText(String text) {
        return text;
    }


}
