package com.GameLogic;

import WorkingFiles.MyGui;
import com.Imports.ImportJSON;
import com.Players.Player;
import com.Story.Story;
import com.Utility.Printer;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BattleMechanics {
    private static int score = 0;

    public static void fight(String enemy,Player player) throws IOException, ParseException, InterruptedException {

        List<Player> enemyToFight = ImportJSON.getNpcs().stream().filter(ene -> ene.getName().equalsIgnoreCase(enemy)).collect(Collectors.toList());
        Player enemyFighter = enemyToFight.get(0);

        System.out.println("You started a battle with: "+enemyFighter.getName());
        MyGui.outputTextArea("You started a battle with: "+enemyFighter.getName());

        //Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Type: 'attack' to deal damage or 'run' to escape from the battle.");
            MyGui.outputTextArea("Type: 'attack' to deal damage or 'run' to escape from the battle.");

            //String input = in.nextLine().strip();
            String input = MyGui.requestInput().strip();
            if(input.equalsIgnoreCase("attack") || input.equalsIgnoreCase("fight") || input.equalsIgnoreCase("strike")){
                player.battle(enemyFighter);
                enemyFighter.battle(player);

                System.out.println("Enemy HP is now: "+ enemyFighter.getHp());
                MyGui.outputTextArea("Enemy HP is now: "+ enemyFighter.getHp());

                if(checkFight(player,enemyFighter) != null) {
                    break;
                }
            }
            else if(input.equalsIgnoreCase("run")){
                System.out.println("You are running away");
                MyGui.outputTextArea("You are running away");
                break;
            }

        }

    }

    public static List<Player> checkFight(Player player,Player enemy) throws IOException, ParseException, InterruptedException {
        if(player.getHp() <= 0) {

            System.out.println("You have died!");
            MyGui.outputTextArea("You have died!");
            MyGui.outputTextArea("Your final score was: " + player.calculateScore());
            MyGui.outputTextArea(Story.quitMessage());
            Thread.sleep(5000);
            System.exit(0);

        }
        else if(enemy.getHp() <= 0) {

            System.out.println("You have defeated the: "+ enemy.getName());
            MyGui.outputTextArea("You have defeated the: "+ enemy.getName());

            List<Player> enemyToRemove = ImportJSON.getNpcs().stream().filter(ene -> ene.getName().equalsIgnoreCase(enemy.getName())).collect(Collectors.toList());
            List<Player> enemies = new ArrayList<>(ImportJSON.getNpcs());
            enemies.remove(enemyToRemove.get(0));
            player.getCurrentRoom().defeatEnemy();
            score += enemy.getValue();
            return enemies;


        }
        return null;
    }


    public static int calculateScore()  {
        return score;
    }
}
