import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class MapTest {

    char[][] m;
    Map map;

    @Before
    public void setup(){

        map = new Map();
        map.setMapSize(50, 5);
        map.generate();
    }


    @After
    public void teardown(){
    }

    //setMapSize
    @Test
    public void setMapSizeTest1(){
        assertTrue(map.setMapSize(5, 2));
    }


    @Test
    public void setMapSizeTest2(){
        assertTrue(map.setMapSize(5, 4));
    }

    @Test
    public void setMapSizeTest3(){
        assertFalse(map.setMapSize(5, 5));
    }

    @Test
    public void setMapSizeTest4(){
        assertTrue(map.setMapSize(8, 7));
    }

    @Test
    public void setMapSizeTest5(){
        assertFalse(map.setMapSize(51, 3));
    }

    @Test
    public void setMapSizeTest6(){
        assertFalse(map.setMapSize(51, 8));
    }



    //generate
    @Test
    public void generateTest1()   {
        map.setMapSize(5, 2);
        map.generate();
        assertEquals(map.mapArray[0].length, 5);
        assertEquals(map.mapArray[4].length, 5);
    }


    @Test
    public void generateTest2(){
        map.setMapSize(5, 2);
        m = new char[5][5];
        map.generate();
        assertNotEquals(map.mapArray[0], null);
        assertNotEquals(map.mapArray[4], null);
    }







    @Test
    public void getTileTypeTest(){
        map.mapArray = new char[][]{{'g', 'w'}, {'w', 't'}};
        assertEquals(map.getTileType(0, 0), 'g');


    }

    @Test
    public void getRandomStartPositionMustReturnGrassTileTest() {
        char expectedTileType = 'g';
        Position startPosition = map.getRandomStartPosition();
        char actualTileType = map.getTileType(startPosition.getX(), startPosition.getY());
        assertEquals(expectedTileType, actualTileType);
    }
}
