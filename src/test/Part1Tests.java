import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Part1Tests {

    private Part1 part1;

    @Before
    public void setUp() {
        part1 = new Part1();
    }

    @Test
    public void addTest(){

        assertEquals(3, c.add(1,2));
        assertEquals(10, c.add(5,5));
    }

    @Test
    public void subtractTest(){

        assertEquals(-1, c.subtract(1,2));
        assertEquals(2, c.subtract(6,4));
    }

    @Test
    public void multiplyTest(){

        assertEquals(0, c.multiply(2,0));
        assertEquals(4, c.multiply(2,2));
    }
}
