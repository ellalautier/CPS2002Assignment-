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
    }


    @After
    public void teardown(){
    }

    //setMapSize
    @Test
    public void setMapSizeTest1(){
        assertTrue(map.setMapSize(2, 5));
    }


    @Test
    public void setMapSizeTest2(){
        assertTrue(map.setMapSize(4, 5));
    }

    @Test
    public void setMapSizeTest3(){
        assertFalse(map.setMapSize(5, 5));
    }

    @Test
    public void setMapSizeTest4(){
        assertTrue(map.setMapSize(7, 8));
    }

    @Test
    public void setMapSizeTest5(){
        assertFalse(map.setMapSize(3, 51));
    }

    @Test
    public void setMapSizeTest6(){
        assertFalse(map.setMapSize(8, 51));
    }



    //generate
    @Test
    public void generateTest1()   {
        map.setMapSize(2, 5);
        map.generate();
        assertEquals(map.mapArray[0].length, 5);
        assertEquals(map.mapArray[4].length, 5);
    }


    @Test
    public void generateTest2(){
        map.setMapSize(2,5);
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

}
