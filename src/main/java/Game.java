import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    int turns;
    Player[] players;
    Map map = new Map();
    static final int MIN_PLAYERS = 2;
    static final int MAX_PLAYERS = 8;

    /**
     * Initialises the players array with size n.  Minimum number of players: 2, max: 8.
     * @param n Number of players
     * @return true if n is >= minimum number of players, and <= max
     */
    boolean setNumPlayers(final int n) {
        if (n >= MIN_PLAYERS && n <= MAX_PLAYERS) {
            players = new Player[n];
            return true;
        }

        return false;
    }

<<<<<<< HEAD


    public static void main(String[] args){
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
=======
    /**
     * Generates an HTML file for each player.  Filename: map_player_n.html (where n is the player number.)
     * Each HTML file contains a table representing the map from the perspective of the corresponding user.
     * i.e. each  player  sees  the  tiles  he/she has discovered so far, as well as his/her current position.
     */
    void generateHTMLFiles() {
    }


    void startGame() {
        // loop:
        // generateHTMLFiles();
            /* for each player:
                ask for direction (U, D, L, R)
                ensure not out of map
               for each player:
                uncover target tile
                if treasure - player wins
                if grass - player moves
                if water - player dies, moves back to starting position
               if no winner - continue
             */
    }

    private void askUserForNumOfPlayers(Scanner scanner) {
>>>>>>> 140aa0c378725ee5d8c82cce691414620064be22
        int numOfPlayers;
        boolean success = false;

        System.out.print("Input number of players (minimum " + MIN_PLAYERS + ", maximum " + MAX_PLAYERS + "): ");
        while (!success) {
            try {
                numOfPlayers = Integer.parseInt(scanner.nextLine());
                success = setNumPlayers(numOfPlayers);

                if (!success)
                    System.err.print("Minimum number of players " + MIN_PLAYERS + ", maximum " + MAX_PLAYERS
                            + ". Try again: ");

            } catch (NumberFormatException e) {
                System.err.print("Not a valid number. Try again: ");
            }
        }
    }

    private int getNumOfPlayers() {
        return players.length;
    }

    private void askUserForMapSize(Scanner scanner) {
        int mapSize;
        boolean success = false;
        System.out.print("Input map size n (for an n x n map): ");
        while (!success) {
            try {
                mapSize = Integer.parseInt(scanner.nextLine());
                success = map.setMapSize(mapSize, getNumOfPlayers());

                if (!success)
                    System.err.print("2-4 players: minimum map size is 5x5.  5-8 players: minimum map size is 8x8.  Try again: ");

            } catch (NumberFormatException e) {
                System.err.print("Not a valid number. Try again: ");
            }
        }
    }

<<<<<<< HEAD
=======
    public static void main(String[] args){
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        Map m = new Map();

        m.size = 30;
        m.generate();

        System.out.println("Treasure Game");
        game.askUserForNumOfPlayers(scanner);
        game.askUserForMapSize(scanner);

        // for each player, generate a random starting position on the map - has to be grass
        game.startGame();
    }
>>>>>>> 140aa0c378725ee5d8c82cce691414620064be22
}
