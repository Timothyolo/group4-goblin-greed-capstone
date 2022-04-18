import WorkingFiles.MyGui;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import com.GameLogic.Game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        /*EventQueue.invokeLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       try {
                                           new MyGui();
                                           //Game newGame = new Game();
                                           //newGame.beginGame();
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       } catch (ParseException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               });*/
        new MyGui();
        Game newGame = new Game();
        newGame.beginGame();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Game newGame = new Game();
                    newGame.beginGame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/


        //Game newGame = new Game();


        /*boolean startGame = newGame.beginGame();

        while (true) {

            if (startGame) {
                newGame.playGame(newGame.getPlayer());
            }
            else {
                break;
            }

        }*/
    }

}

