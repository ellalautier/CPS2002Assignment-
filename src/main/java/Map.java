import java.lang.Math.*;
public class Map {


    public char[][] mapArray;
    public int size;


    boolean setMapSize(int x, int y){           //x - no of players, y - length
        if(x<2 || x>8){
            return false;
        }else{
            if(x>4 && y < 8){
                return false;
            }else if(y>50 || y<5){
                return false;
            }
        }
        size = x;
        return true;
    }



    void generate(){                        //creates and populates array
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



       // int x_1, x_2, y_1, y_2;

        for(int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++)
                if (mapArray[i][j] == 'g' || mapArray[i][j] == 't') {
                    int[] adj = {i - 1, i + 1, j - 1, j + 1};
                    int adjNo = 0;
                    for (int k = 0; k < 4; k++) {
                        if (k < 2) {
                            if ((adj[k] > 0 && adj[k] < size) && (mapArray[adj[k]][j] != 'w')) adjNo++;
                        } else {
                            if ((adj[k] > 0 && adj[k] < size) && (mapArray[i][adj[k]] != 'w')) adjNo++;
                        }
                    }

                    if (adjNo < 2) {
                        int q = adjNo;
                        while (q != 2) {
                            int p = (int) (Math.random() * 4);
                            if (p < 2) {
                                if ((adj[p] > 0 && adj[p] < size) && (mapArray[adj[p]][j] == 'w')) {
                                    mapArray[adj[p]][j] = 'g';
                                    q++;
                                }
                            } else {
                                if ((adj[p] > 0 && adj[p] < size) && (mapArray[i][adj[p]] == 'w')) {
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


    char getTileType(int x, int y){
        return mapArray[x][y];
    }

}
