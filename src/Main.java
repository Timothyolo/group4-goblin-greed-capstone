import WorkingFiles.MyGui;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import com.GameLogic.Game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {

        EventQueue.invokeLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       try {
                                           new MyGui();

                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       } catch (ParseException e) {
                                           e.printStackTrace();
                                       } catch (InterruptedException e) {
                                           e.printStackTrace();
                                       }
                                   }
                               });

    }

}

