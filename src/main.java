import java.util.Scanner;

public class main {

    enum Commands {
        START,
        QUIT,
        HELP
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = scan.nextLine();
        if(name.equalsIgnoreCase(Commands.START.name())){
            System.out.println("Let the game begin.");
        }
    }
}
