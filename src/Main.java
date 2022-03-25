import org.json.simple.parser.ParseException;
import java.io.IOException;
import com.GameLogic.Game;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Game newGame = new Game();

        boolean startGame = newGame.beginGame();
        while(true) {

            if(startGame){
                newGame.playGame(newGame.getPlayer());
            }
            else{
                break;
            }

        }









    }
}
