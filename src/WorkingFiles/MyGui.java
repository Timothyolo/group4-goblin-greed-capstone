package WorkingFiles;

import com.GameLogic.BattleMechanics;
import com.GameLogic.Game;
import com.GameLogic.PlayerMechanics;
import com.Imports.ImportJSON;
import com.Players.Player;
import com.Rooms.Room;
import com.Story.Story;
import com.Utility.Printer;
import com.Utility.TextParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyGui {

    //TextParser parser;
    Game newGame;
    private JFrame frame;
    private Container cp;
    private Font titleFont;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel gameTitle;
    private JButton playButton;
    private JButton infoButton;

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JLabel bottomTextLabel;
    private static JTextField bottomTf;
    private static JTextArea mainTextArea;
    private JButton audioOnButton;

    private static Clip clip;

    private JScrollPane scroll;
    private static boolean textReceived;

    StartGameHandler sgHandler = new StartGameHandler();
    InputTextHandler itHandler = new InputTextHandler();
    SoundHandler soundHandler = new SoundHandler();

    /*public static void main(String[] args) throws IOException, ParseException {

        new MyGui();
    }*/

    /**
     * Initial GUI screen with game title, and two buttons - Play and More Info
     */
    public MyGui() throws IOException, ParseException, InterruptedException {

        //parser = new TextParser();
        newGame = new Game();

        //newGame.beginGame();
        frame = new JFrame("Goblin's Greed");
        cp = frame.getContentPane();
        titleFont = new Font("Times New Roman", Font.PLAIN, 90);
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        gameTitle = new JLabel("Goblin's Greed");
        playButton = new JButton("Play");
        infoButton = new JButton("More Info");


        clip = null;

        mainTextArea = new JTextArea();
        //scroll = new JScrollPane (mainTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //StartGameHandler sgHandler = new StartGameHandler();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        cp.setBackground(Color.GRAY);
        gameTitle.setFont(titleFont);

        titlePanel.setBounds(100, 100, 600, 150);
        buttonPanel.setBounds(300, 400, 200, 100);
        playButton.setBackground(Color.GREEN);
        infoButton.setBackground(Color.GREEN);
        playButton.addActionListener(sgHandler);
        infoButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, Story.tutorial()));

        titlePanel.add(gameTitle);
        buttonPanel.add(playButton);
        buttonPanel.add(infoButton);

        frame.add(buttonPanel);
        frame.add(titlePanel);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Game newGame = new Game();
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

        frame.setVisible(true);


    }

    /**
     * Event Listener for Play button, will display the output JTextArea and JTextField at bottom for command inputs
     */
    public class StartGameHandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            startGameScreen();
        }

        private void startGameScreen() {
            buttonPanel.setVisible(false);
            titlePanel.setVisible(false);

            cp.setBackground(Color.GRAY);

            topPanel = new JPanel();
            centerPanel = new JPanel();
            bottomPanel = new JPanel();
            bottomTextLabel = new JLabel("Enter your command here: ");
            bottomTf = new JTextField(30);
            audioOnButton = new JButton("Sound");

            mainTextArea.setLineWrap(true);
            mainTextArea.setWrapStyleWord(true);
            mainTextArea.setEditable(false);
            mainTextArea.setBounds(100, 100, 600, 400);
            scroll = new JScrollPane (mainTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setPreferredSize(new Dimension(600, 400));

            scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
            });


            bottomTf.addActionListener(itHandler);
            audioOnButton.addActionListener(soundHandler);


            //centerPanel.add(mainTextArea);
            centerPanel.add(scroll);
            bottomPanel.add(bottomTextLabel);
            bottomPanel.add(bottomTf);
            bottomPanel.add(audioOnButton);


            //frame.add(scroll);
            cp.add(BorderLayout.NORTH, topPanel);
            cp.add(BorderLayout.CENTER, centerPanel);
            cp.add(BorderLayout.SOUTH, bottomPanel);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //Game newGame = new Game();
                        newGame.beginGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }


    }

    /**
     * Event Listener for sound button
     */
    public class SoundHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                JButton b=(JButton) e.getSource();
                // play the sound clip
                if(b.getText().equals("Sound")){
                    b.setText("Stop");
                    btnPlaySoundCLick();
                } else if(b.getText().equals("Stop")) {
                    b.setText("Sound");
                    clip.stop();
                }
            } catch (LineUnavailableException | IOException
                    | UnsupportedAudioFileException ex) {

                ex.printStackTrace();
            }
        }

        private void btnPlaySoundCLick() throws LineUnavailableException, IOException, UnsupportedAudioFileException{

            File soundFile = new File("src/com/music/Just Breathing (Instrumental) - NEFFEX.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(sound);

            clip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        //System.out.println("stop");
                        event.getLine().close();
                    }
                }
            });

            clip.start();

        }


    }

    /*private void btnPlaySoundCLick() throws LineUnavailableException, IOException, UnsupportedAudioFileException{

        File soundFile = new File("src/com/music/group4.wav");
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(sound);

        clip.addLineListener(new LineListener() {
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    //System.out.println("stop");
                    event.getLine().close();
                }
            }
        });

        clip.start();

    }*/

    /**
     * Event Listener for JTextField
     */
    public class InputTextHandler implements ActionListener {

        //takes input from JTextField at bottom
        @Override
        public void actionPerformed(ActionEvent e) {

            outputTextArea(bottomTf.getText());
            textReceived = true;
            synchronized (bottomTf) {
                // notify game loop thread which is waiting on this event
                bottomTf.notifyAll();
            }
        }
    }

    /**
     * Method that is synchronized with JTextField input
     * @return
     */
    public static String requestInput() {
        //bottomTf.setEnabled(true);
        bottomTf.requestFocusInWindow();
        // wait on text field till UI thread signals a user input event
        synchronized (bottomTf) {
            while (!textReceived) {
                try {
                    bottomTf.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        String input = bottomTf.getText();
        bottomTf.setText("");
        //bottomTf.setEnabled(false);
        textReceived = false;
        return input;
    }


    /**
     * Method for outputting to JTextArea in center
     * @param output
     */
    public static void outputTextArea(String output) {
        mainTextArea.append(output + "\n");

    }



}
