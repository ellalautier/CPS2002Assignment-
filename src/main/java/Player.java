import java.util.ArrayList;

class Player {
    private int playerID;
    private final Map map;
    private Team team = null;
    Position currentPosition;
    public final Position startPosition;
    public final boolean[][] discoveredTiles;  // true means the tile at this position in the map has been discovered by this player

    /**
     * @param position Method checks if player is currently at this position.
     * @return True if player's current position matches given position, false otherwise.
     */
    public boolean isAt(Position position) {
        return position.x == currentPosition.x && position.y == currentPosition.y;
    }

    /**
     * @param position Method checks if the player has discovered the tile at this position.
     * @return True if player has discovered tile at given position, false otherwise.
     */
    public boolean hasDiscovered(Position position) {
        return discoveredTiles[position.x][position.y];
    }

    /**
     * Marks a position as discovered - sets the boolean corresponding to this position in the discoveredTiles array
     * to true.
     *
     * @param position Position in the map to discover.
     */
    public void discover(Position position) {
        discoveredTiles[position.x][position.y] = true;

    }

    /**
     * Resets current position to where the player started (startPosition)
     */
    public void resetPosition() {
        setPosition(startPosition);
    }

    /**
     * Initialises the player to a random starting position (grass tile) on the provided map.
     *
     * @param map Map on which to place player.
     */
    public Player(Map map) {
        this.map = map;
        discoveredTiles = new boolean[map.size][map.size];  // default values are false (no tiles discovered so far)
        startPosition = map.getRandomStartPosition();
        setPosition(startPosition);
        discover(currentPosition);
        this.team = null;

    }

    /**
     * Moves the player by one tile in the direction specified.
     *
     * @param direction Direction to move in - 'U' -> up, 'D, -> down, 'L' -> left, 'R' -> right
     */
    void move(char direction) {
        if (!moveIsOutOfMap(direction, map)) {
            switch (direction) {
                case 'U':
                    currentPosition.y--;
                    break;
                case 'D':
                    currentPosition.y++;
                    break;
                case 'L':
                    currentPosition.x--;
                    break;
                case 'R':
                    currentPosition.x++;
                    break;
                default:
                    break;
            }
        }
        discover(currentPosition);
        if(team!=null) {
            sendPosition(currentPosition);
        }

    }

    /**
     * @param direction Direction to move in - 'U' -> up, 'D, -> down, 'L' -> left, 'R' -> right
     * @return true if moving in the provided direction sends player out of the map, false otherwise
     */
    boolean moveIsOutOfMap(char direction, Map map) {
        switch (direction) {
            case 'U':
                return currentPosition.y == 0;
            case 'D':
                return currentPosition.y == map.size - 1;
            case 'L':
                return currentPosition.x == 0;
            case 'R':
                return currentPosition.x == map.size - 1;
            default:
                return false;
        }
    }


    private void sendPosition(Position p){
        team.sendPosition(p);
    }

    /**
     * Sets the player's position.
     *
     * @param p Position to set.
     * @return true: setting position was successful, false: not successful - position out of bounds of the map.
     */
    public boolean setPosition(Position p) {
        if (map.isOutOfBounds(p)) {
            return false;
        } else {
            currentPosition = new Position(p);

            return true;
        }
    }

    /**
     * @return true if player is at a treasure tile, false otherwise
     */
    public boolean hasFoundTreasure() {
        return map.getTileType(currentPosition.x, currentPosition.y) == 't';
    }

    /**
     * @return true if player is at a water tile, false otherwise
     */
    public boolean hasDied() {
        return map.getTileType(currentPosition.x, currentPosition.y) == 'w';
    }


    public int getPlayerID(){
        return playerID;
    }

    public void setTeam(Team team){
        this.team = team;
        team.addPlayer(this);
    }

    public Team getTeam(){
        return this.team;
    }

}