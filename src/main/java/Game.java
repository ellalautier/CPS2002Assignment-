import java.util.InputMismatchException;
import java.util.Scanner;

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
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        int numOfPlayers;

        Map m = new Map();

        m.size = 30;
        m.generate();

        System.out.println("Treasure Game");

        System.out.print("Input number of players (minimum 2, maximum 8): ");
        boolean success = false;
        while (!success) {
            try {
                numOfPlayers = Integer.parseInt(scanner.nextLine()) ;
                success = game.setNumPlayers(numOfPlayers);

                if (!success)
                    System.err.print("Minimum number of players 2, maximum 8. Try again: ");

            } catch (NumberFormatException e) {
                System.err.print("Not a valid number. Try again: ");
            }
        }
    }

}
