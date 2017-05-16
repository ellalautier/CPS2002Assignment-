
abstract class Map {
    public char[][] mapArray;
    public int size;
    private static Map theMap = null;  // Singleton Map instance.
    public enum Type {SAFE, HAZARDOUS};
    private static Type mapType;

    public static boolean isIntantiated() {
        return !(theMap == null);
    }

    private static Map makeMap(Type mapType){
        switch (mapType){
            case SAFE:
                return SafeMap.getInstance();
            case HAZARDOUS:
                return HazardousMap.getInstance();
            default:
                return null;
        }
    }

    //Map.getInstance();

    /**
     * Sets the map type.  Can only be set before the map has been instantiated.
     * @param mapType type to set
     * @return true if successful, false if not - i.e. if map has already been instantiated, so cannot change the type.
     */
    public static boolean setMapType(Type mapType) {
        if (theMap == null) {
            Map.mapType = mapType;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the map instance.  Creates a map instance before returning it if one has not already been created and a
     * map type has been set.
     * @return The map instance, or null if a type has not been set
     */
    public static Map getInstance() {
        if (mapType == null)
            return null;
        else if (theMap == null) {
            theMap = makeMap(mapType);
        }

        return theMap;
    }

    /**
     * Checks if Position object is out of bounds
     * @param position Position to be tested
     * @return true if out of range, false if valid
     */

    boolean isOutOfBounds(Position position) {
        return position.x < 0 || position.y < 0 || position.x >= size || position.y >= size;
    }

    /**
     * Sets the map's size. 2-4 players: minimum map size is 5x5.  5-8 players: minimum map size is 8x8.
     * Maximum map size is 50x50.
     * @param size Map size to set. (Result - size x size map, eg. for a 50x50 map this variable should be 50.)
     * @param numOfPlayers Number of players in game.
     * @return true if successful, false if not - size greater than maximum, or less than minimum for no. of players.
     */
    boolean setMapSize(int size, int numOfPlayers) {
        if (numOfPlayers<2 || numOfPlayers>8) {
            return false;
        } else {
            if (numOfPlayers>4 && size < 8) {
                return false;
            } else if (size>50 || size<5) {
                return false;
            }
        }

        this.size = size;
        return true;
    }

    /**
     * Creates map in the form of a 2D character array
     * first selects the treasure map coordinates then fills the rest randomly with water and grass tiles
     */
    abstract void generate();

    /**
     * returns the type of tile
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @return type of tile: either 'g', 'w' or 't'÷
     */

    char getTileType(int x, int y){
        return mapArray[x][y];
    }

    /**
     * Returns a position of a random grass tile from which a player can start the game from.
     * @return Position of random grass tile
     */
    Position getRandomStartPosition() {
        int x = (int)(Math.random()*size);
        int y = (int)(Math.random()*size);
        while(getTileType(x,y)!='g'){             //ensures grass tile
            x = (int)(Math.random()*size);
            y = (int)(Math.random()*size);
        }
        return new Position(x, y);
    }
}
