class SafeMap extends Map {
    /**
     * Constructor made private to ensure that there can only be one instance of map at any given time (singleton).
     */
    private SafeMap() { }

    /**
     * @return New SafeMap if a map has not already been instantiated, otherwise null.
     */
    public static Map getInstance() {
        if (!Map.isInstantiated())
            return new SafeMap();
        else
            return null;
    }

    /**
     * Generates map with a  maximum  of  10%  water  tiles.
     */
    @Override
    void generate(){
        mapArray = new char[size][size];

        //fill map with grass tiles only
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                mapArray[i][j] = 'g';
            }
        }

        int t_x = (int)(Math.random()*size);
        int t_y = (int)(Math.random()*size);
        mapArray[t_x][t_y] = 't';

        //find how many tiles are going to be set to water tiles (between 0% and 10%)
        int numWaterTiles = (int)(Math.random()*.1 * size * size);

        while(numWaterTiles!=0){
            int w_x = (int)(Math.random()*size);
            int w_y = (int)(Math.random()*size);
            if(mapArray[w_x][w_y] == 'g'){
                mapArray[w_x][w_y] = 'w';
                numWaterTiles--;
            }
        }

    }
}
