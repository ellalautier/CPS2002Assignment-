import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

    private SafeMap sMap;
    private HazardousMap hMap;
    private SafeMap sMap2;
    private HazardousMap hMap2;

    @Before
    public void setUp() {

        sMap = new SafeMap();
        hMap = new HazardousMap();
        sMap2 = new SafeMap();
        hMap2 = new HazardousMap();
        sMap2.setMapSize(50, 5);
        hMap2.setMapSize(20, 2);
        sMap2.generate();
        hMap2.generate();
    }


    @After
    public void tearDown() {
        sMap = null;
        hMap = null;
        sMap2 = null;
        hMap2 = null;
    }

    //setMapSize
    @Test
    public void setMapSizeTest1() {
        assertTrue(sMap.setMapSize(5, 2));
    }


    @Test
    public void setMapSizeTest2() {
        assertTrue(hMap.setMapSize(5, 4));
    }

    @Test
    public void setMapSizeTest3() {
        assertFalse(sMap.setMapSize(5, 5));
    }

    @Test
    public void setMapSizeTest4() {
        assertTrue(hMap.setMapSize(8, 7));
    }

    @Test
    public void setMapSizeTest5() {
        assertFalse(sMap.setMapSize(51, 3));
    }

    @Test
    public void setMapSizeTest6() {
        assertFalse(hMap.setMapSize(51, 8));
    }


    //generate
    @Test
    public void generateTest1() {
        sMap.setMapSize(5, 2);
        sMap.generate();
        assertEquals(sMap.mapArray[0].length, 5);
        assertEquals(sMap.mapArray[4].length, 5);
    }

    @Test
    public void generateTest2() {
        hMap.setMapSize(5, 2);
        hMap.generate();
        assertEquals(hMap.mapArray[0].length, 5);
        assertEquals(hMap.mapArray[4].length, 5);
    }


    @Test
    public void generateTest3() {
        sMap.setMapSize(5, 2);
        sMap.generate();
        assertNotEquals(sMap.mapArray[0], null);
        assertNotEquals(sMap.mapArray[4], null);
    }

    @Test
    public void generateTest4() {
        hMap.setMapSize(5, 2);
        hMap.generate();
        assertNotEquals(hMap.mapArray[0], null);
        assertNotEquals(hMap.mapArray[4], null);
    }


    //get tile type

    @Test
    public void getTileTypeTest1() {
        sMap2.mapArray = new char[][]{{'g', 'w'}, {'w', 't'}};
        assertEquals(sMap2.getTileType(0, 0), 'g');

    }

    @Test
    public void getTileTypeTest2() {
        hMap2.mapArray = new char[][]{{'g', 'w'}, {'w', 't'}};
        assertEquals(hMap2.getTileType(0, 0), 'g');

    }

    //random start position

    @Test
    public void getRandomStartPositionTest1() {
        char expectedTileType = 'g';
        Position startPosition = sMap2.getRandomStartPosition();
        char actualTileType = sMap2.getTileType(startPosition.getX(), startPosition.getY());
        assertEquals(expectedTileType, actualTileType);
    }

    @Test
    public void getRandomStartPositionTest2() {
        char expectedTileType = 'g';
        Position startPosition = hMap2.getRandomStartPosition();
        char actualTileType = hMap2.getTileType(startPosition.getX(), startPosition.getY());
        assertEquals(expectedTileType, actualTileType);
    }

    @Test
    public void isOutOfBoundsTestXNegative() {
        assertTrue(sMap2.isOutOfBounds(new Position(-1, 0)));
    }

    @Test
    public void isOutOfBoundsTestYNegative() {
        assertTrue(hMap2.isOutOfBounds(new Position(0, -1)));
    }

    @Test
    public void isOutOfBoundsTestXOut() {
        assertTrue(sMap2.isOutOfBounds(new Position(50, 0)));
    }

    @Test
    public void isOutOfBoundsTestYOut() {
        assertTrue(hMap2.isOutOfBounds(new Position(0, 50)));
    }

    @Test
    public void isOutOfBoundsTestWithinBounds() {
        sMap.setMapSize(50, 5);
        sMap.generate();
        assertFalse(sMap.isOutOfBounds(new Position(30, 30)));
    }
}
