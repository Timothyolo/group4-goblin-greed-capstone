package com.GameLogic;

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

    public static void fight(String enemy,Player player) throws IOException, ParseException {

        List<Player> enemyToFight = ImportJSON.getNpcs().stream().filter(ene -> ene.getName().equalsIgnoreCase(enemy)).collect(Collectors.toList());
        Player enemyFighter = enemyToFight.get(0);
        System.out.println("You started a battle with: "+enemyFighter.getName());
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Type: 'attack' to deal damage and 'run' to escape from the battle.");
            if ("attack".equalsIgnoreCase(in.nextLine())){
                player.battle(enemyFighter);
                enemyFighter.battle(player);
                System.out.println("Enemy HP is now: "+ enemyFighter.getHp());
                if(checkFight(player,enemyFighter) != null) {
                    break;
                };
            }else if("run".equalsIgnoreCase(in.nextLine())) {
                break;
            }
        }
    }

    public static List<Player> checkFight(Player player,Player enemy) throws IOException, ParseException {
        if(player.getHp() <= 0) {
            System.out.println("You have died!");
            System.exit(130);
        }
        else if(enemy.getHp() <= 0) {
            System.out.println("You have defeated the: "+ enemy.getName());
            List<Player> enemyToRemove = ImportJSON.getNpcs().stream().filter(ene -> ene.getName().equalsIgnoreCase(enemy.getName())).collect(Collectors.toList());
            List<Player> enemies = new ArrayList<>(ImportJSON.getNpcs());
            enemies.remove(enemyToRemove.get(0));
            player.getCurrentRoom().defeatEnemy();
            return enemies;


        }
        return null;
    }
}
