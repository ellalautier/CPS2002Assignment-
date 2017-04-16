import java.lang.Math;
import java.util.ArrayList;

public class Player {
    Position currentPosition;
    public Position startPosition;
    ArrayList<Position> discoveredPositions = new ArrayList<Position>();

    public Player(Map map) {
        setPosition(map.getRandomStartPosition());
    }

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