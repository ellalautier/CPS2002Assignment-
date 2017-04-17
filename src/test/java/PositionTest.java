import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private Position p1;
    private Position p2;
    private Position p3;

    @Before
    public void setUp() {
        p1 = new Position(2,2);
        p2 = new Position(2,2);
        p3 = new Position(1,2);
    }

    @After
    public void tearDown() {
        p1 = null;
        p2 = null;
        p3 = null;
    }

    @Test
    public void equalsTest1(){
        assertTrue(p1.equals(p2));
    }

    @Test
    public void equalsTest2(){
        assertFalse(p1.equals(p3));
    }

}
