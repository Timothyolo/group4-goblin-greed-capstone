package WorkingFiles;

import com.GameLogic.Game;
import com.Story.Story;
import com.Utility.Printer;
import com.Utility.TextParser;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyGui {

    //TextParser parser;
    Game newGame;
    JFrame frame;
    Container cp;
    Font titleFont;
    JPanel titlePanel;
    JPanel buttonPanel;
    JLabel gameTitle;
    JButton playButton;
    JButton infoButton;

    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;
    JLabel bottomTextLabel;
    static JTextField bottomTf;
    static JTextArea mainTextArea;
    JButton audioOnButton;

    static Clip clip;

    JScrollPane scroll;

    static String newline;


    StartGameHandler sgHandler = new StartGameHandler();
    InputTextHandler itHandler = new InputTextHandler();
    SoundHandler soundHandler = new SoundHandler();

    /*public static void main(String[] args) throws IOException, ParseException {

        new MyGui();
    }*/

    /**
     * Initial GUI screen with game title, and two buttons - Play and More Info
     */
    public MyGui() throws IOException, ParseException {

        //parser = new TextParser();
        newGame = new Game();
        frame = new JFrame("Goblin's Greed");
        cp = frame.getContentPane();
        titleFont = new Font("Times New Roman", Font.PLAIN, 90);
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        gameTitle = new JLabel("Goblin's Greed");
        playButton = new JButton("Play");
        infoButton = new JButton("More Info");

        newline = "\n";
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
        //frame.add(scroll);

        frame.setVisible(true);


    }

    /**
     * Handler for Play button, will display the output JTextArea and JTextField at bottom for command inputs
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


            //JLabel sample = new JLabel("Goblin's Greed");
            //mainTextArea = new JTextArea("This is the main text area. ");
            //mainTextArea.setBackground(Color.white);
            mainTextArea.setLineWrap(true);
            mainTextArea.setWrapStyleWord(true);
            mainTextArea.setEditable(false);
            mainTextArea.setBounds(100, 100, 600, 400);
            scroll = new JScrollPane (mainTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scroll.setPreferredSize(new Dimension(600, 400));

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


        }


    }

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


    }

    private void btnPlaySoundCLick() throws LineUnavailableException, IOException, UnsupportedAudioFileException{

        File soundFile = new File("src/com/music/group4.wav");
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(sound);

        clip.addLineListener(new LineListener() {
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    System.out.println("stop");
                    event.getLine().close();
                }
            }
        });

        clip.start();

    }

    /**
     * Event handler for JTextField
     */
    public class InputTextHandler implements ActionListener {

        //takes input from JTextField at bottom
        @Override
        public void actionPerformed(ActionEvent e) {
            /*String text = mainTextArea.getText();
            mainTextArea.append(text + newline);
            mainTextArea.selectAll();*/
            outputTextArea(bottomTf.getText());
            //if(e.getSource() == bottomTf){
                String str = bottomTf.getText();
                //String[] input = bottomTf.getText().split(" ");
                Game.storeText(str);
                //Game.storeText(str);
                /*if(!str.equals("")){
                    //commandHandler.handle(str);
                }*/
            bottomTf.setText("");
            //bottomTf.requestFocusInWindow();
            //}
        }
    }

    /*public void run(){
        ui.print(commandHandler.listCommands());

        while(true){
            if(commandHandler.continue()){
                ui.print(commandHandler.events());
            }
        }
    }*/

    /**
     * Method for outputting to JTextArea in center
     * @param output
     */
    public static void outputTextArea(String output) {
        //output to JTextArea in center
        mainTextArea.append(output + newline);
        //mainTextArea.setText(output);
    }





}
