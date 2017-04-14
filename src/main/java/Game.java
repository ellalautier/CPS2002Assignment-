public class Game {
    int turns;
    Player[] players;
    Map map;

    /**
     * Initialises the players array with size n.  Minimum number of players: 2, max: 8.
     * @param n Number of players
     * @return true if n is >= minimum number of players, and <= max
     */
    boolean setNumPlayers(final int n) {
        final int min = 2;
        final int max = 8;

        if (n >= min && n <= max) {
            players = new Player[n];
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
