public class HazardousMap extends Map{
    private HazardousMap() {}

    public static Map getInstance() {
        if (!Map.isIntantiated())
            return new HazardousMap();
        else
            return null;
    }

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
        int numWaterTiles = (int)((Math.random()*.1 +.25 )* size * size);

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
