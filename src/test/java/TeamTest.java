import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TeamTest {

    private Player player1;
    private Player player2;
    private Team team;
    private Position p;

    @Before
    public void setUp() {
        Map.setMapType(Map.Type.SAFE);
        Map.getInstance().setMapSize(50, 4);
        Map.getInstance().generate();
        player1 = new Player(Map.getInstance());
        player2 = new Player(Map.getInstance());
        team = new Team();
        p = new Position(0,0);
    }

    @After
    public void tearDown() {
        player1 = null;
        player2 = null;
        team = null;
        p = null;
    }

    @Test
    public void sendPositionTest(){
        player1.setTeam(team);
        player2.setTeam(team);
        team.sendPosition(p);

        assertTrue(player1.hasDiscovered(p));
        assertTrue(player2.hasDiscovered(p));

    }

}


