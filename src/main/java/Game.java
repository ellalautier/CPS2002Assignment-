import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Generates HTML table code to represent the map as seen by the provided player.
     * @param player The player whose map
     * @return String containing the HTML table code
     */
    String getTableHTMLForPlayer(Player player) {
        String table = "<table>";

        for (int y = 0; y < map.size; y++) {
            String row = "<tr>";
            for (int x = 0; x < map.size; x++) {
                Position position = new Position(x, y);
                String cssClass;
                String cell;

                if (player.hasDiscovered(position)) {
                    switch (map.getTileType(x, y)) {
                        case 'g':
                            cssClass = "grass";
                            break;
                        case 'w':
                            cssClass = "water";
                            break;
                        case 't':
                            cssClass = "treasure";
                            break;
                        default:
                            cssClass = "";  // should not occur
                            break;
                    }
                } else {
                    cssClass = "hidden";
                }

                cell = "<td class = \"" + cssClass + "\">" + (player.isAt(position) ? "O" : "") + "</td>";
                row += cell;
            }
            row += "</tr>";
            table += row;
        }

        table += "</table>";

        return table;
    }

    /**
     * Generates an HTML file for each player.  Filename: map_player_n.html (where n is the player number.)
     * Each HTML file contains a table representing the map from the perspective of the corresponding user.
     * i.e. each  player  sees  the  tiles  he/she has discovered so far, as well as his/her current position.
     */
    void generateHTMLFiles() {
        String beforeTitle = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <style>\n" +
                "        td.uncovered { background-color: lightgrey; }\n" +
                "        td.treasure { background-color: gold; }\n" +
                "        td.grass { background-color: ForestGreen; }\n" +
                "        td.water { background-color: LightSkyBlue; }\n" +
                "        td {\n" +
                "            border: 1px solid #999;\n" +
                "            padding: 0;\n" +
                "            height: 30px;\n" +
                "            width: 30px;\n" +
                "            text-align: center;\n" +
                "            font-family: sans-serif;\n" +
                "            font-size: 30px;\n" +
                "            line-height: 30px;\n" +
                "        }\n" +
                "        table {\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "    </style>\n";
        String afterTitleUpToBody = "</head>\n" + "<body>";
        String afterBody = "</body>\n" + "</html>";

        for (int i = 0; i < players.length; i++) {
            int playerNo = i + 1;
            String title = "<title>Player " + playerNo + " - Treasure Game Map</title>";
            String filename = "map_player_" + playerNo + ".html";
            String innerBody = getTableHTMLForPlayer(players[i]);

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                bw.write(beforeTitle);
                bw.write(title);
                bw.write(afterTitleUpToBody);
                bw.write(innerBody);
                bw.write(afterBody);
                bw.close();
            } catch (IOException e) {
                System.err.println("Error writing " + filename);
                e.printStackTrace();
            }
        }
    }

    /**
     * @param directionString Should contain character representing direction ('U' -> up, 'D, -> down, 'L' -> left, 'R' -> right)
     * @return true if valid direction ('U', 'D', 'L', or 'R'), false otherwise.
     */
    boolean isValidDirection(String directionString) {
        char direction;
        if (directionString.length() != 1) {
            return false;
        } else {
            direction = directionString.toUpperCase().charAt(0);
            return direction == 'U' || direction == 'D' || direction == 'L' || direction == 'R';
        }

    }

    /**
     * Asks the player for a direction to move in.  Retries until a valid move is given i.e. a valid direction
     * ('U', 'D', 'L', or 'R') which does not send the player out of the map.
     * @param player Player to move.
     * @param playerNo Player number - used to display message so users know whose turn it is.)
     * @param scanner Scanner object - used to get character representing desired move direction from user.
     */
    private char getValidMoveFromPlayer(Player player, int playerNo, Scanner scanner) {
        String directionInput = " "; // dummy initial value

        boolean valid = false;
        System.out.print("Player " + playerNo + "'s move: " );

        while (!valid) {
            directionInput = scanner.nextLine().toUpperCase();

            if (!isValidDirection(directionInput)) {
                System.out.print("Move can only be U (up), D (down), L (left), or R (right).  Try again: ");
            } else if (player.moveIsOutOfMap(directionInput.charAt(0), map)) {
                System.out.print("This move would be out of the map.  Try another direction: ");
            } else {
                valid = true;
            }
        }

        return directionInput.charAt(0);
    }


    void startGame(Scanner scanner) {
        boolean treasureFound = false;
        List<Integer> winningPlayers = new ArrayList<Integer>();
        int round = 1;
        generateHTMLFiles();
        System.out.println("Game starting ...");

        while (!treasureFound) {
            System.out.println("Round " + round);
            generateHTMLFiles();

            for (int i = 0; i < players.length; i++) {
                int playerNo = i + 1;
                char direction = getValidMoveFromPlayer(players[i], playerNo, scanner);
                players[i].move(direction);
                if (players[i].hasDied()) {
                    System.out.println("You died! You go back to where you began.");
                    players[i].setPosition(players[i].startPosition);
                }
                if (players[i].hasFoundTreasure()) {
                    treasureFound = true;
                    winningPlayers.add(playerNo);
                }
            }

            round++;
        }

        System.out.println("Players " + winningPlayers.toString() + " found the treasure!");
    }

    private void askUserForNumOfPlayers(Scanner scanner) {

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


    /**
     * Instantiates the Player objects - each player gets a starting position on a random grass tile.
     */
    void initialisePlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(map);
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Treasure Game");
        // set up game parameters (user inputted)
        game.askUserForNumOfPlayers(scanner);
        game.askUserForMapSize(scanner);
        // set up map, initialise players to starting positions
        game.map.generate();
        game.initialisePlayers();

        game.startGame(scanner);
    }

}
