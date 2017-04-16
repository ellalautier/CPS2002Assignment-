public class Player {
    Position currentPosition;
    public Position startPosition;
    public boolean[][] discoveredTiles;  // true means the tile at this position in the map has been discovered by this player

    /**
     * Marks a position as discovered - sets the boolean corresponding to this position in the discoveredTiles array
     * to true.
     * @param position Position in the map to discover.
     */
    private void discover(Position position) {
        discoveredTiles[position.x][position.y] = true;
    }

    /**
     * Initialises the player to a random starting position (grass tile) on the provided map.
     * @param map Map on which to place player.
     */
    public Player(Map map) {
        discoveredTiles = new boolean[map.size][map.size];  // default values are false (no tiles discovered so far)
        startPosition = map.getRandomStartPosition();
        currentPosition = startPosition;
        discover(currentPosition);
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