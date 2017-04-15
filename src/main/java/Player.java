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

}
