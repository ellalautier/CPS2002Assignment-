
public class Map {
    public char[][] mapArray;
    public int size;

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


    void generate(){
        mapArray = new char[size][size];
        int t_x = (int)(Math.random()*size);
        int t_y = (int)(Math.random()*size);
        mapArray[t_x][t_y] = 't';

        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++)
                if (mapArray[i][j] != 't') {
                    int v = (int) (Math.random()*2);
                    if (v == 0) mapArray[i][j] = 'g';
                    else mapArray[i][j] = 'w';
                }
        }


        //this loop is used to ensure every grass tile can reach the treasure


        for(int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++)
                if (mapArray[i][j] == 'g' || mapArray[i][j] == 't') {
                    int[] adj = {i - 1, i + 1, j - 1, j + 1};
                    int adjNo = 0;
                    for (int k = 0; k < 4; k++) {
                        if (k < 2) {
                            if ((adj[k] >= 0 && adj[k] < size) && (mapArray[adj[k]][j] != 'w')) adjNo++;
                        } else {
                            if ((adj[k] >= 0 && adj[k] < size) && (mapArray[i][adj[k]] != 'w')) adjNo++;
                        }
                    }

                    if (adjNo < 2) {
                        int q = adjNo;
                        while (q != 2) {
                            int p = (int) (Math.random() * 4);
                            if (p < 2) {
                                if ((adj[p] >= 0 && adj[p] < size) && (mapArray[adj[p]][j] == 'w')) {
                                    mapArray[adj[p]][j] = 'g';
                                    q++;
                                }
                            } else {
                                if ((adj[p] >= 0 && adj[p] < size) && (mapArray[i][adj[p]] == 'w')) {
                                    mapArray[i][adj[p]] = 'g';
                                    q++;
                                }
                            }
                        }
                    }

                }
        }


        /*
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++) {
                System.out.print(mapArray[i][j] + " ");
            }
            System.out.println();
        }
        */

    }

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
