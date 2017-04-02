import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Part1Test {

    private Part1 part1;

    @Before
    public void setUp() {
        part1 = new Part1();
    }

    @Test
    public void addTest(){

        assertEquals(3, part1.add(1,2));
        assertEquals(10, part1.add(5,5));
    }

    @Test
    public void subtractTest(){

        assertEquals(-1, part1.subtract(1,2));
        assertEquals(2, part1.subtract(6,4));
    }

    @Test
    public void multiplyTest(){

        assertEquals(0, part1.multiply(2,0));
        assertEquals(4, part1.multiply(2,2));
    }
}
