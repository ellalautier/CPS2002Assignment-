import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
	private Player player;
	int boardSize;
	Position position;
	
	
	@Before
    public void setUp() {
        player = new Player();
        boardSize = 5;    
    }
<<<<<<< HEAD



=======
	
	@Test
	public void setPositionTestNotOutOfBounds() {
		position = new Position(0, 0);
		
		assertTrue(player.setPosition(position));
		assertFalse(player.setPosition(new Position(6, 6)));
	}
	
	
>>>>>>> 4bfda66e1030e4c30c034e4397415ba44dd41f2e
}
