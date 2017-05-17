abstract class Map {
    public char[][] mapArray;
    public int size;
    private static Map theMap = null;  // Singleton Map instance.
    public enum Type {SAFE, HAZARDOUS}
    private static Type mapType;

    /**
     * @return true if the map has been instantiated, false otherwise
     */
    static boolean isInstantiated() {
        return !(theMap == null);
    }

    /**
     * Tears down the map instance.  NB: This is used for testing purposes only (in SafeMapTest and HazardousMapTest)
     */
    public static void tearDown() {
        theMap = null;
    }

    /**
     * Factory method to instantiate the map for the first time.
     * @param mapType Map type
     * @return New instance of Map subclass according to the given type, or null if the Map has already been
     * instantiated or if an invalid type is given.
     */
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
     * Returns the map instance.  Creates a map instance before returning it, if one has not already been created and
     * provided a map type has been set.
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
     * Checks if given Position is out of the bounds of the map.
     * @param position Position to be tested
     * @return True if out of bounds, false if not
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
     * Generates map by filling the 2D character array mapArray with characters to represent different tile types:
     * 'g' - grass, 'w' - water, 't' - treasure.
     */
    abstract void generate();

    /**
     * Returns the type of the tile in the map array at the given coordinates.
     * @param x horizontal coordinate (0 based)
     * @param y vertical coordinate (0 based)
     * @return Type of tile: either 'g', 'w' or 't' for grass, water, treasure respectively.
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
