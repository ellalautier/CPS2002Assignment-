import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testSetNumPlayers2Min() {
        assertFalse(game.setNumPlayers(1));
        assertFalse(game.setNumPlayers(0));
        assertFalse(game.setNumPlayers(-1));
    }

    @Test
    public void testSetNumPlayers8Max() {
        assertFalse(game.setNumPlayers(9));
    }

    @Test
    public void testSetNumPlayersBetween2And8() {
        assertTrue(game.setNumPlayers(5));
    }

    @Test
    public void testPlayersArrayLengthAfterSetNumPlayers() {
        int numOfPlayers = 5;
        game.setNumPlayers(numOfPlayers);
        int actualLength = game.players.length;

        assertEquals(numOfPlayers, actualLength);
    }

    @After
    public void tearDown(){
        game = null;
    }
}
