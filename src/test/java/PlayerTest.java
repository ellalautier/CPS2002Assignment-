import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Map map;
	private Player player;

	@Before
    public void setUp() {
	    map = new Map();
	    map.setMapSize(50, 4);
	    map.generate();
        player = new Player(map);
    }

    @After
    public void tearDown() {
	    map = null;
	    player = null;
    }

    @Test
    public void playerConstructorTestStartingPositionAddedToDiscoveredPositions() {
	    assertTrue(player.discoveredTiles[player.startPosition.x][player.startPosition.y]);
    }

    @Test
    public void playerConstructorTestStartingPositionEqualsCurrentPosition() {
        assertEquals(player.startPosition, player.currentPosition);
    }

    @Test
    public void moveIsOutOfMapTestUp() {
	    boolean expectedValue = true;
        player.currentPosition = new Position(0, 0);
	    boolean actualValue = player.moveIsOutOfMap('U', map);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestLeft() {
        boolean expectedValue = true;
        player.currentPosition = new Position(0, 0);
        boolean actualValue = player.moveIsOutOfMap('L', map);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestRight() {
        boolean expectedValue = true;
        player.currentPosition = new Position(map.size - 1, map.size - 1);
        boolean actualValue = player.moveIsOutOfMap('R', map);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestDown() {
        boolean expectedValue = true;
        player.currentPosition = new Position(map.size - 1, map.size - 1);
        boolean actualValue = player.moveIsOutOfMap('D', map);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveUpTest() {
	    Position expectedCurrentPosition = new Position(30, 29);
        player.currentPosition = new Position(30, 30);
        player.move('U');
        assertEquals(expectedCurrentPosition, player.currentPosition);
    }
    @Test
    public void moveDownTest() {
        Position expectedCurrentPosition = new Position(30, 30);
        player.currentPosition = new Position(30, 29);
        player.move('D');
        assertEquals(expectedCurrentPosition, player.currentPosition);
    }
    @Test
    public void moveLeftTest() {
        Position expectedCurrentPosition = new Position(29, 30);
        player.currentPosition = new Position(30, 30);
        player.move('L');
        assertEquals(expectedCurrentPosition, player.currentPosition);
    }
    @Test
    public void moveRightTest() {
        Position expectedCurrentPosition = new Position(31, 30);
        player.currentPosition = new Position(30, 30);
        player.move('R');
        assertEquals(expectedCurrentPosition, player.currentPosition);
    }

	@Test
	public void setPositionTestNotOutOfBounds() {
		//position = new Position(0, 0);
		
		//assertTrue(player.setPosition(position));
		//assertFalse(player.setPosition(new Position(6, 6)));
	}

}