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
        assertTrue(player.discoveredPositions.contains(player.startPosition));
    }

    @Test
    public void playerConstructorTestStartingPositionEqualsCurrentPosition() {
        assertEquals(player.startPosition, player.currentPosition);
    }

	/*@Test
	public void setPositionTestNotOutOfBounds() {
		position = new Position(0, 0);
		
		assertTrue(player.setPosition(position));
		assertFalse(player.setPosition(new Position(6, 6)));
	}*/

}