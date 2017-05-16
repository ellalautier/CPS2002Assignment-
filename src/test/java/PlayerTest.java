import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private SafeMap sMap;
    private HazardousMap hMap;
	private Player player1;
    private Player player2;

	@Before
    public void setUp() {
	    sMap = new SafeMap();
        sMap.setMapSize(5, 2);
        sMap.generate();
        hMap = new HazardousMap();
	    hMap.setMapSize(50, 4);
	    hMap.generate();
        player1 = new Player(sMap);
        player1 = new Player(hMap);
    }

    @After
    public void tearDown() {
	    sMap = null;
        hMap = null;
	    player1 = null;
        player2 = null;
    }

    @Test
    public void playerConstructorTestStartingPositionAddedToDiscoveredPositions() {
	    assertTrue(player1.discoveredTiles[player1.startPosition.x][player1.startPosition.y]);
    }

    @Test
    public void playerConstructorTestStartingPositionEqualsCurrentPosition() {
        assertEquals(player2.startPosition, player2.currentPosition);
    }

    @Test
    public void moveIsOutOfMapTestUp() {
	    boolean expectedValue = true;
        player1.currentPosition = new Position(0, 0);
	    boolean actualValue = player1.moveIsOutOfMap('U', sMap);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestLeft() {
        boolean expectedValue = true;
        player2.currentPosition = new Position(0, 0);
        boolean actualValue = player2.moveIsOutOfMap('L', hMap);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestRight() {
        boolean expectedValue = true;
        player1.currentPosition = new Position(sMap.size - 1, sMap.size - 1);
        boolean actualValue = player1.moveIsOutOfMap('R', sMap);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestDown() {
        boolean expectedValue = true;
        player2.currentPosition = new Position(hMap.size - 1, hMap.size - 1);
        boolean actualValue = player2.moveIsOutOfMap('D', hMap);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveUpTest() {
	    Position expectedCurrentPosition = new Position(30, 29);
        player2.currentPosition = new Position(30, 30);
        player2.move('U');
        assertEquals(expectedCurrentPosition, player2.currentPosition);
    }
    @Test
    public void moveDownTest() {
        Position expectedCurrentPosition = new Position(30, 30);
        player1.currentPosition = new Position(30, 29);
        player1.move('D');
        assertEquals(expectedCurrentPosition, player1.currentPosition);
    }
    @Test
    public void moveLeftTest() {
        Position expectedCurrentPosition = new Position(29, 30);
        player2.currentPosition = new Position(30, 30);
        player2.move('L');
        assertEquals(expectedCurrentPosition, player2.currentPosition);
    }
    @Test
    public void moveRightTest() {
        Position expectedCurrentPosition = new Position(31, 30);
        player1.currentPosition = new Position(30, 30);
        player1.move('R');
        assertEquals(expectedCurrentPosition, player1.currentPosition);
    }

}

