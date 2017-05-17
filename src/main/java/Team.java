
import javafx.geometry.Pos;

import java.util.ArrayList;

public class Team {



    private int id;
    ArrayList<Player> playerList = new ArrayList<Player>();


    public Team (int id){
        this.id = id;
    }

    public int addPlayer(Player p){
        playerList.add(p);
        return playerList.size();
    }

    void sendPosition(Position p){
            for(int i = 0; i<playerList.size(); i++){
                    playerList.get(i).discover(p);

            }
    }

}
