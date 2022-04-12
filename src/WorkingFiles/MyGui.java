package WorkingFiles;

import com.Story.Story;
import com.Utility.Printer;

import javax.swing.*;
import java.awt.*;

import static com.Utility.Printer.print;

public class MyGui {

    public static void main(String[] args) {

        //new startGame();
        JFrame frame = new JFrame("Goblin's Greed");
        Container cp = frame.getContentPane();
        Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JLabel gameTitle = new JLabel("Goblin's Greed");
        JButton playButton = new JButton("Play");
        JButton infoButton = new JButton("More Info");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        cp.setBackground(Color.GRAY);
        gameTitle.setFont(titleFont);

        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));

        titlePanel.setBounds(100, 100, 600, 150);
        buttonPanel.setBounds(300, 400, 200, 100);
        playButton.setBackground(Color.GREEN);
        infoButton.setBackground(Color.GREEN);
        infoButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, Story.tutorial()));

        titlePanel.add(gameTitle);
        buttonPanel.add(playButton);
        buttonPanel.add(infoButton);

        frame.add(buttonPanel);
        frame.add(titlePanel);


        frame.setVisible(true);
        

    }


}
