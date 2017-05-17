import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SafeMapTest {
    @Before
    public void setUp() {
        Map.setMapType(Map.Type.SAFE);
    }

    @Test
    public void generateTest1() {
        Map.getInstance().setMapSize(5, 2);
        Map.getInstance().generate();
        assertEquals(Map.getInstance().mapArray[0].length, 5);
        assertEquals(Map.getInstance().mapArray[4].length, 5);
    }


    @Test
    public void generateTest2() {
        Map.getInstance().setMapSize(5, 2);
        Map.getInstance().generate();
        assertNotEquals(Map.getInstance().mapArray[0], null);
        assertNotEquals(Map.getInstance().mapArray[4], null);
    }

    @After
    public void tearDown() {
        Map.tearDown();
    }
}
