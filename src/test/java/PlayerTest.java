import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
	private Player player;
    private Team team;

	@Before
    public void setUp() {
	    Map.setMapType(Map.Type.SAFE);
        Map.getInstance().setMapSize(50, 4);
        Map.getInstance().generate();
        player = new Player(Map.getInstance());
        team = new Team();
    }

    @After
    public void tearDown() {
	    player = null;
        team = null;
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
	    boolean actualValue = player.moveIsOutOfMap('U', Map.getInstance());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestLeft() {
        boolean expectedValue = true;
        player.currentPosition = new Position(0, 0);
        boolean actualValue = player.moveIsOutOfMap('L', Map.getInstance());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestRight() {
        boolean expectedValue = true;
        player.currentPosition = new Position(Map.getInstance().size - 1, Map.getInstance().size - 1);
        boolean actualValue = player.moveIsOutOfMap('R', Map.getInstance());
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIsOutOfMapTestDown() {
        boolean expectedValue = true;
        player.currentPosition = new Position(Map.getInstance().size - 1, Map.getInstance().size - 1);
        boolean actualValue = player.moveIsOutOfMap('D', Map.getInstance());
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
    public void addPlayerToTeam(){
        team.addPlayer(player);
        player.setTeam(team);
        assertEquals(player.getTeam(), team);
    }

}

