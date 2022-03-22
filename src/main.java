import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;


public class main {

    enum Commands {
        START,
        QUIT,
        HELP
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("What do you wanna do: ");
            String name = scan.nextLine();
            if(name.equalsIgnoreCase(Commands.START.name())){
                System.out.println("Let the game begin.");
            }
            else if(name.equalsIgnoreCase(Commands.QUIT.name())) {
                System.out.println("You forfeited, traitor!");
                break;
            }
            else if(name.equalsIgnoreCase(Commands.HELP.name())) {
                for (Commands comm: Commands.values()) {
                    System.out.println(comm);
                }
            }
        }

    }
}
