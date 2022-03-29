package com.Utility;

import java.io.IOException;

public class Printer {

    public static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }



}
