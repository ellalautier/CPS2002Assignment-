import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Game {
    Player[] players;
    private final List<Team> teams = new ArrayList<Team>();
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 8;

    /**
     * Initialises the players array with size n.  Minimum number of players: 2, max: 8.
     * @param n Number of players
     * @return true if n is >= MIN_PLAYERS and <= MAX_PLAYERS, false otherwise
     */
    boolean setNumPlayers(final int n) {
        if (n >= MIN_PLAYERS && n <= MAX_PLAYERS) {
            players = new Player[n];
            return true;
        }

        return false;
    }

    /**
     * Generates HTML table code to represent the map as seen by the provided player.  Each cell in the table represents
     * a position on the map.  A circle is drawn on the tile the player is currently at.  CSS classes are used to change
     * the background colour of a tile according to its type - grey for undiscovered, blue for water, green for grass
     * and yellow for treasure.
     * @param player The player whose discovered part of the map this method will generate HTML table code for
     * @return String containing the HTML table code (including <table></table> tags)
     */
    private String getTableHTMLForPlayer(Player player) {
        StringBuilder table = new StringBuilder("<table>");

        for (int y = 0; y < Map.getInstance().size; y++) {
            StringBuilder row = new StringBuilder("<tr>");
            for (int x = 0; x < Map.getInstance().size; x++) {
                Position position = new Position(x, y);

                String cssClass;
                String cell;

                if (player.hasDiscovered(position)) {
                    switch (Map.getInstance().getTileType(x, y)) {
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
                row.append(cell);
            }
            row.append("</tr>");
            table.append(row);
        }

        table.append("</table>");

        return table.toString();
    }


    /**
     * Generates an HTML file for each player.  Filename: map_player_n.html (where n is the player number.)
     * Each HTML file contains a table representing the map from the perspective of the corresponding user.
     * i.e. each  player  sees  the  tiles he/she has discovered so far, as well as his/her current position.
     */
    private void generateHTMLFiles() {
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
    private boolean isValidDirection(String directionString) {
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
            } else if (player.moveIsOutOfMap(directionInput.charAt(0), Map.getInstance())) {
                System.out.print("This move would be out of the map.  Try another direction: ");
            } else {
                valid = true;
            }
        }

        return directionInput.charAt(0);
    }

    /**
     * The main game loop:  Generate HTML files, ask each player for a valid move, move players.  If player hits water
     * tile, reset to their start position.  Loops until at least one player has found the treasure.
     * @param scanner used for input
     */

    private void startGame(Scanner scanner) {
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
                    players[i].resetPosition();
                }
                if (players[i].hasFoundTreasure()) {
                    treasureFound = true;
                    winningPlayers.add(playerNo);
                }
            }

            round++;
        }

        generateHTMLFiles();
        System.out.println("Players " + winningPlayers.toString() + " found the treasure!");
    }


    /**
     * Asks user for number of players.  Retries until a valid number is given (within min and max range.)
     * Calls setNumPlayers( ) when a valid number is given.
     * @param scanner Used for user input
     */
    private void userSetNumOfPlayers(Scanner scanner) {
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

    /**
     * @return number of players in game
     */

    private int getNumOfPlayers() {
        return players.length;
    }


    /**
     * Asks user for map size.  Retries until a valid number is given.  Calls Map's setMapSize( ).
     * @param scanner Used for user input
     */

    private void userSetMapSize(Scanner scanner) {
        int mapSize;
        boolean success = false;
        System.out.print("Input map size n (for an n x n map): ");
        while (!success) {
            try {
                mapSize = Integer.parseInt(scanner.nextLine());
                success = Map.getInstance().setMapSize(mapSize, getNumOfPlayers());

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
    private void initialisePlayers() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(Map.getInstance());
        }
    }

    /**
     * Asks user for map type.  Retries until a valid option is given.  Calls Map's setMapType( ).
     * @param scanner Used for user input
     */
    private void userSetMapType(Scanner scanner){
        int mapType;
        boolean success = false;

        System.out.print("Input 0 for safe map or 1 for hazardous map: ");

        while (!success) {
            try {
                mapType = Integer.parseInt(scanner.nextLine());
                success = mapType >= 0 && mapType < Map.Type.values().length;

                if (success)
                    Map.setMapType(mapType == 0 ? Map.Type.SAFE : Map.Type.HAZARDOUS);
                else
                    System.err.print("Invalid input, try again: ");


            } catch (NumberFormatException e) {
                System.err.print("Not a valid number. Try again: ");
            }
        }
    }

    /**
     * Asks user if team mode is to be used.  Retries until a valid number of teams is given - there must be at least
     * two teams, and more teams than players.  If so, distributes players into teams in turn eg. if there are 3 players
     * and 2 teams, Player 1 is assigned to Team 1, Player 2 to Team 2, and Player 3 to Team 1.
     * @param scanner Used for user input
     */
    private void teamOption(Scanner scanner){
        int input;

        System.out.println("Would you like to play in team mode. 1 for yes, 0 for no");
        input  = Integer.parseInt(scanner.nextLine());
        while(input !=1 && input != 0){
            input  = Integer.parseInt(scanner.nextLine());
            System.out.println("Error: Invalid input");
        }

        if(input == 1){
            int teams = -1;
            System.out.println("How many teams shall the players be split up in?");
            teams  = Integer.parseInt(scanner.nextLine());
            while(!(teams >= 2) && !(teams<players.length)){
                System.out.println("Invalid input. Try again: ");
                teams  = Integer.parseInt(scanner.nextLine());
            }

            for(int i = 0; i<teams; i++){
                this.teams.add(new Team());
            }

            for(int i = 0; i<players.length; i++){
                int teamNum = i% this.teams.size();
                players[i].setTeam(this.teams.get(teamNum));

            }

        }

    }

    /**
     * The program entry-point.  Gets map type, number of players, map size, team option from the user.  Instantiates
     * and generates the map, initialises the players, and starts the game.
     * @param args Program arguments are not used
     */
    public static void main(String[] args){
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Treasure Game");

        // set up game parameters (user inputted)
        game.userSetMapType(scanner);
        game.userSetNumOfPlayers(scanner);
        game.userSetMapSize(scanner);

        // set up map, initialise players to starting positions
        Map.getInstance().generate();
        game.initialisePlayers();

        game.teamOption(scanner);

        if(!game.teams.isEmpty()){
            for(int i = 0; i<game.players.length; i++){
                game.players[i].getTeam().sendPosition(game.players[i].startPosition);
            }
        }

        game.startGame(scanner);
    }

}
