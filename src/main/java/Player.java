import java.lang.Math;
public class Player {


    public Position p;
    public Position startPos;


    public void startPosition(Map m){
        int x = (int)(Math.random()*m.size);
        int y = (int)(Math.random()*m.size);
        while(m.getTileType(x,y)!='g'){             //ensures grass tile
            x = (int)(Math.random()*m.size);
            y = (int)(Math.random()*m.size);
        }
        startPos.setX(x);
        startPos.setY(y);
        p = startPos;
    }

=======
    /**
     * Moves the player by one tile in the direction specified.
     * @param direction Direction to move in - 'U' -> up, 'D, -> down, 'L' -> left, 'R' -> right
     */
    void move(char direction) {

    }

    /**
     * Sets the player's position.
     * @param p  Position to set.
     * @return true: setting position was successful, false: not successful - position out of bounds of the map.
     */
    boolean setPosition(Position p) {
        return false;
    }

}
