public class Game {
    int turns;
    Player[] players;
    Map map;

    boolean setNumPlayers(int n) {
        if (n >= 2 && n <= 8) {
            return true;
        }

        return false;
    }

    public static void main(String[] args){
        Map m = new Map();
        m.size = 30;
        m.generate();
    }
}
