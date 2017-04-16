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
	    boolean actualValue = player.moveIsOutOfMap('U');
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestLeft() {
        boolean expectedValue = true;
        player.currentPosition = new Position(0, 0);
        boolean actualValue = player.moveIsOutOfMap('L');
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestRight() {
        boolean expectedValue = true;
        player.currentPosition = new Position(map.size, map.size);
        boolean actualValue = player.moveIsOutOfMap('R');
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestDown() {
        boolean expectedValue = true;
        player.currentPosition = new Position(map.size, map.size);
        boolean actualValue = player.moveIsOutOfMap('D');
        assertEquals(expectedValue, actualValue);
    }

	/*@Test
	public void setPositionTestNotOutOfBounds() {
		position = new Position(0, 0);
		
		assertTrue(player.setPosition(position));
		assertFalse(player.setPosition(new Position(6, 6)));
	}*/

}