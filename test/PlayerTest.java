import com.Players.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    Player player1;
    Player player2;
    Player enemy1;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("Tim", 101,5);
        player2 = new Player("Justin", 102, 3);
        enemy1 = new Player("Presontasion", 500,50,4);
    }

    // make sure Player class can create an enemy character
    @Test
    public void testPlayer(){assertTrue(enemy1 instanceof Player);}

    //test getHp()
    @Test
    public void testGetHp(){
    player1.getHp();
    player2.getHp();
    enemy1.getHp();

    long player1Hp = player1.getHp();
    long player2Hp = player2.getHp();
    long enemy1Hp = enemy1.getHp();

    long expectedPlayer1Hp = 101;
    assertEquals(expectedPlayer1Hp, player1Hp);
    long expectedPlayer2Hp = 102;
    assertEquals(expectedPlayer2Hp, player2Hp);
    long expectedEnemy1Hp = 500;
    assertEquals(expectedEnemy1Hp, enemy1Hp);

    System.out.println(player1);
    System.out.println(player2);
    System.out.println(enemy1);

    }


}
