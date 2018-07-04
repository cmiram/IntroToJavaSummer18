import java.util.Scanner;

/***
 * This class is the entry point for running pacman
 * Game runs until user quits and then shows game stats
 */

public class Pacman
{

    // used to simplify keeping track of pacman's direction
    private enum Direction
    {
        LEFT, RIGHT, UP, DOWN
    }

    // used to make identifying board objects easier
    private enum GameObject
    {
        PACMAN, COOKIE, EMPTY, VISITED
    }

    // used with random number generator to set average number of
    //cookies on the board. Some boards may have more, some less
    private static final double COOKIE_PERCENT = 0.08;

    // stores width of the board
    private static int xDim;

    // stores height of the board
    private static int yDim;

    // tracks cookies eaten by pacman
    private static int cookiesEaten;

    // keeps track of pacman's location on board
    private static int[] pacmanLoc;

    // tracks pacman's current direction
    private static Direction pacmanDirection;

    // tracks move count. increments on successful and failed moves
    private static int moveCount;

    // stores values for what items are on the board
    private static GameObject[][] board;

    public static void main(String[] args)
    {
        // reads in input from console
        Scanner input = new Scanner(System.in);

        // init command to -1 so we enter while loop
        int menuCommand = -1;

        // prompts user to enter their board size
        getGameDims(input);

        // starts a new game by setting class variables
        handleStartNewGame();

        // write out menu initially to show user their options
        writeMenu();

        // run game until user exits
        while(menuCommand != 5)
        {
            // only write full menu when user wants it
            if(menuCommand == 1)
            {
                writeMenu();
            }

            // next draw game board every iteration
            drawGameBoard();

            // ask user for next menu command
            menuCommand = getMenuCommand(input);

            // handle their choice
            handleMenuCommand(menuCommand);
        }

        // calculate moves per cookie casting ints to doubles
        double movesPerCookie = (double) moveCount / (double) cookiesEaten;

        // write out game statistics for the user
        System.out.println("Cookies Eaten: " + cookiesEaten);
        System.out.println("Moves Made: " + moveCount);
        System.out.println(String.format("Moves Per Cookie: %.2f",
                movesPerCookie));

        // close input before exiting program
        input.close();
    }

    private static void getGameDims(Scanner input)
    {
        // set dims to -1 initially to enter loops
        xDim = -1;
        yDim = -1;

        // prompt user for board width until they enter a valid option
        while(xDim < 1) {
            System.out.print("Enter X-Dimension for your Pacman board: ");
            xDim = input.nextInt();
            // make sure board width is positive non-zero int
            if(xDim < 1) {
                System.out.println("Invalid dimension. Please enter a " +
                        "positive integer.");
            }
        }

        // prompt user for board height until they enter a valid option
        while(yDim < 1) {
            System.out.print("Enter Y-Dimension for your Pacman board: ");
            yDim = input.nextInt();
            // make sure board height is positive non-zero int
            if(yDim < 1) {
                System.out.println("invalid dimension. Please enter a " +
                        "positive integer.");
            }
        }
    }

    private static void handleStartNewGame()
    {
        // set initial game state variables
        cookiesEaten = 0;
        pacmanLoc = new int[]{0, 0};
        pacmanDirection = Direction.LEFT;
        moveCount = 0;
        // build game board from dims and intial constants
        board = buildInitialGameBoard();
    }

    private static void writeMenu()
    {
        System.out.println("1) Menu");
        System.out.println("2) Turn Left");
        System.out.println("3) Turn Right");
        System.out.println("4) Move");
        System.out.println("5) Exit");
    }

    private static int getMenuCommand(Scanner input)
    {
        // init to -1 so we enter loop
        int menuCommand = -1;
        // loop until user enters a valid game command
        while(isNotValidMenuOption(menuCommand)) {
            System.out.print("Please enter an int 1-5 menu command: ");
            menuCommand = input.nextInt();
            // prompt user if they entered invalid command
            if(isNotValidMenuOption(menuCommand))
            {
                System.out.println("Invalid menu command. Please try again.");
            }
        }
        return menuCommand;
    }

    private static boolean isNotValidMenuOption(int menuCommand)
    {
        // valid commands are 1, 2, 3, 4, 5
        return menuCommand < 1 || menuCommand > 5;
    }

    private static void handleMenuCommand(int menuCommand)
    {
        // handle all valid menu commands
        switch(menuCommand)
        {
            case 1:
                // only want to show menu so just return and handle in main
                return;
            case 2:
                // turn pacman left
                turnPacmanLeft();
                return;
            case 3:
                // turn pacman right
                turnPacmanRight();
                return;
            case 4:
                // move pacman and handle effects of that
                movePacman();
                return;
            case 5:
                // immediately return if user ends game
                return;
             default:
                 // should never happen
                 // but throw error if we try to handle unknown command
                 throw new IllegalArgumentException("Unknown Menu Command");
        }
    }

    private static void turnPacmanLeft()
    {
        switch(pacmanDirection)
        {
            case LEFT:
                pacmanDirection = Direction.DOWN;
                return;
            case RIGHT:
                pacmanDirection = Direction.UP;
                return;
            case UP:
                pacmanDirection = Direction.LEFT;
                return;
            case DOWN:
                pacmanDirection = Direction.RIGHT;
                return;
             default:
                 // handle case that enum is extended and turn function
                 // is not updated with new values
                 throw new IllegalArgumentException("Unknown Direction");
        }
    }

    private static void turnPacmanRight()
    {
        switch(pacmanDirection)
        {
            case LEFT:
                pacmanDirection = Direction.UP;
                return;
            case RIGHT:
                pacmanDirection = Direction.DOWN;
                return;
            case UP:
                pacmanDirection = Direction.RIGHT;
                return;
            case DOWN:
                pacmanDirection = Direction.LEFT;
                return;
            default:
                // handle case that enum is extended and turn function
                // is not updated with new values
                throw new IllegalArgumentException("Unknown Direction");
        }
    }

    private static void movePacman() {
        // always increment move count regardless if valid move or not
        moveCount++;

        // check if move would put pacman outside board boundaries
        if(canMove())
        {
            // store off pacman's current location
            int currX = pacmanLoc[0];
            int currY = pacmanLoc[1];

            // set new location to current loc for now since only 1 is
            // updated per move
            int newX = currX;
            int newY = currY;

            // if moving left or right update X position
            // otherwise update Y position
            if(pacmanDirection == Direction.LEFT ||
                    pacmanDirection == Direction.RIGHT)
            {
                newX = getNewDimension();
            }
            else
            {
                newY = getNewDimension();
            }

            // if new position contains a cookie update counter
            if(board[newY][newX] == GameObject.COOKIE)
            {
                System.out.println("Nom Nom Nom Cookie Eaten! :D");
                cookiesEaten++;
            }

            // update pacman loc with new position
            pacmanLoc = new int[]{newX, newY};

            // update board position with pacman's new loc
            board[newY][newX] = GameObject.PACMAN;

            // set pacman's previous position to visited in board
            board[currY][currX] = GameObject.VISITED;
        }
    }

    private static boolean canMove()
    {
        // pacman can move left or right if new x value is > 0 and < width
        if(pacmanDirection == Direction.LEFT ||
                pacmanDirection == Direction.RIGHT)
        {
            return getNewDimension() >= 0 && getNewDimension() < xDim;
        }
        // pacman can move up or down if new y value is > 0 and < height
        else {
            return getNewDimension() >= 0 && getNewDimension() < yDim;
        }
    }

    private static int getNewDimension() {
        // get updated pacman location value
        switch(pacmanDirection) {
            case LEFT:
                return pacmanLoc[0] - 1;
            case RIGHT:
                return pacmanLoc[0] + 1;
            case UP:
                return pacmanLoc[1] - 1;
            case DOWN:
                return pacmanLoc[1] + 1;
            default:
                throw new IllegalArgumentException("Unknown Direction");
        }
    }

    private static String getPacman(Direction direction) {
        switch(direction) {
            case UP: return "V";
            case DOWN: return "^";
            case LEFT: return ">";
            case RIGHT: return "<";
            default: throw new IllegalArgumentException("Unknown direction");
        }
    }

    private static String getGameObject(GameObject object, Direction direction) {
        switch(object) {
            case EMPTY: return ".";
            case VISITED: return " ";
            case COOKIE: return "O";
            // pacman's visual depends on direction
            case PACMAN: return getPacman(direction);
            // should never happen but throw if game objects is updated
            // and this function isn't updated
            default: throw new IllegalArgumentException("Unknown object");
        }
    }

    private static GameObject[][] buildInitialGameBoard() {
        // make new 2-d array based on user defined board dimensions
        GameObject[][] board = new GameObject[yDim][xDim];

        // loop over x and y arrays
        for(int i=0; i<yDim; i++) {
            for(int j=0; j<xDim; j++) {
                // if random number falls within desired cookie percentage
                // make position a cookie otherwise it is empty
                board[i][j] = Math.random() < COOKIE_PERCENT ?
                        GameObject.COOKIE : GameObject.EMPTY;
            }
        }

        // pacman always starts in upper left corner
        board[0][0] = GameObject.PACMAN;
        return board;
    }

    private static void drawGameBoard() {
        StringBuilder boardBuilder = new StringBuilder();

        // build line of hyphens as a border same length as other lines
        String border = new String(new char[xDim+1])
                .replace("\0", " -");

        boardBuilder.append(border).append("\n");
        // iterate over array of arrays to draw board line by line
        for(GameObject[] row : board) {
            // add pipe as far left border
            boardBuilder.append("| ");
            for(GameObject obj : row) {
                // get visual for game object occupying this board space
                boardBuilder.append(getGameObject(obj, pacmanDirection));
                // put a space between items for visual clarity
                boardBuilder.append(" ");
            }
            // add pip as far right border
            boardBuilder.append(" |\n");
        }

        boardBuilder.append(border);
        System.out.println(boardBuilder.toString());
    }
}
