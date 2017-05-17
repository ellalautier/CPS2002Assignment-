import java.util.ArrayList;
import java.util.List;

public class Team {
    private final List<Player> playerList = new ArrayList<Player>();

    /**
     * Adds a player to the team.
     * @param p Player to add to the team.
     * @return Number of players in the team after adding the given player.
     */
    public int addPlayer(Player p){
        playerList.add(p);
        return playerList.size();
    }

    /**
     * Sends the given position to each player in the team - each player discovers this position.  (Mediator pattern)
     * @param p The position to send (to be discovered) by each player.
     */
    public void sendPosition(Position p){
        for(int i = 0; i<playerList.size(); i++){
            playerList.get(i).discover(p);
        }
    }

}
