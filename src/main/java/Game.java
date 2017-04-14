public class Game {
    int turns;
    Player[] players;
    Map map;

    public static void main(String[] args){
        Map m = new Map();
        m.size = 30;
        m.generate();
    }

    boolean setNumPlayers(int n) {
        return true;
    }
}
