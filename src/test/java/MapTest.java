import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
    @Before
    public void setUp() {
        Map.setMapType(Map.Type.SAFE);  // in this test class, we're testing behaviour common to all Map subtypes, so
        // the type doesn't really make a difference here
        //Map.getInstance().setMapSize(50, 5);
        /*Map.getInstance().setMapSize(20, 2);
        Map.getInstance().generate();
        Map.getInstance().generate();*/
    }


    @After
    public void tearDown() {
    }

    @Test
    public void setMapSizeTest1() {
        assertTrue(Map.getInstance().setMapSize(5, 2));
    }


    @Test
    public void setMapSizeTest2() {
        assertTrue(Map.getInstance().setMapSize(5, 4));
    }

    @Test
    public void setMapSizeTest3() {
        assertFalse(Map.getInstance().setMapSize(5, 5));
    }

    @Test
    public void setMapSizeTest4() {
        assertTrue(Map.getInstance().setMapSize(8, 7));
    }

    @Test
    public void setMapSizeTest5() {
        assertFalse(Map.getInstance().setMapSize(51, 3));
    }

    @Test
    public void setMapSizeTest6() {
        assertFalse(Map.getInstance().setMapSize(51, 8));
    }

    //get tile type

    @Test
    public void getTileTypeTest1() {
        Map.getInstance().mapArray = new char[][]{{'g', 'w'}, {'w', 't'}};
        assertEquals(Map.getInstance().getTileType(0, 0), 'g');

    }

    //random start position

    @Test
    public void getRandomStartPositionTest() {
        char expectedTileType = 'g';
        Position startPosition = Map.getInstance().getRandomStartPosition();
        char actualTileType = Map.getInstance().getTileType(startPosition.getX(), startPosition.getY());
        assertEquals(expectedTileType, actualTileType);
    }

    @Test
    public void isOutOfBoundsTestXNegative() {
        assertTrue(Map.getInstance().isOutOfBounds(new Position(-1, 0)));
    }

    @Test
    public void isOutOfBoundsTestYNegative() {
        assertTrue(Map.getInstance().isOutOfBounds(new Position(0, -1)));
    }

    @Test
    public void isOutOfBoundsTestXOut() {
        assertTrue(Map.getInstance().isOutOfBounds(new Position(50, 0)));
    }

    @Test
    public void isOutOfBoundsTestYOut() {
        assertTrue(Map.getInstance().isOutOfBounds(new Position(0, 50)));
    }

    @Test
    public void isOutOfBoundsTestWithinBounds() {
        Map.getInstance().setMapSize(50, 5);
        Map.getInstance().generate();
        assertFalse(Map.getInstance().isOutOfBounds(new Position(30, 30)));
    }
}
