package com.images;
import javax.swing.*;
import java.util.Objects;

public class Image extends JFrame{
    JFrame frame;
    JLabel displayField;
    ImageIcon image;
    JButton startButton;

    public Image() {
        frame = new JFrame("Goblin Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            image = new ImageIcon(Objects.requireNonNull(getClass().getResource("goblin.jpg")));
            displayField = new JLabel(image);
            frame.add(displayField);

            startButton = new JButton("PLay");
            startButton.setBounds(50, 50, 100, 50);

            displayField.add(startButton);
        } catch(Exception e) {
            ;
            System.out.println("Image can't be found");

        }

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Image i = new Image();
    }




}

