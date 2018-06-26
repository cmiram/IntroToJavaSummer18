import java.util.Scanner;

/***
 * This class is the entry point for running pacman
 */

enum Direction {
    LEFT, RIGHT, UP, DOWN
}

enum GameObject {
    PACMAN, COOKIE, EMPTY, VISITED
}

public class Pacman {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int xDim, yDim;
    }

}

class PacmanModel {
    private int xDim;
    private int yDim;
    private GameObject[][] board;
    private int[] pacmanLoc;
    private Direction direction;


    public PacmanModel(int xDim, int yDim) {
        this.xDim = xDim;
        this.yDim = yDim;
        this.pacmanLoc = new int[]{0, 0};
        this.direction = Direction.LEFT;
        this.board = buildInitialGameBoard();
    }

    private GameObject[][] buildInitialGameBoard() {
        GameObject[][] board = new GameObject[xDim][yDim];
        board[0][0] = GameObject.PACMAN;
        for(int i=1; i<xDim; i++) {
            for(int j=0; j<yDim; j++) {
                board[i][j] = Math.random() < 0.08 ? GameObject.COOKIE : GameObject.EMPTY;
            }
        }
        return board;
    }


}

class PacmanView {

    public PacmanView() {

    }

    public void drawGameBoard(GameObject[][] board, Direction pacmanDirection) {
        StringBuilder boardBuilder = new StringBuilder();
        for(GameObject[] row : board) {
            for(GameObject obj : row) {
                boardBuilder.append(getGameObject(obj, pacmanDirection));
            }
            boardBuilder.append("\n");
        }
        System.out.println(boardBuilder.toString());
    }

    private String getPacman(Direction direction) {
        switch(direction) {
            case UP: return "V";
            case DOWN: return "^";
            case LEFT: return ">";
            case RIGHT: return "<";
            default: throw new IllegalArgumentException("Unknown direction");
        }
    }

    private String getGameObject(GameObject object, Direction direction) {
        switch(object) {
            case EMPTY: return ".";
            case VISITED: return " ";
            case COOKIE: return "O";
            case PACMAN: return getPacman(direction);
            default: throw new IllegalArgumentException("Unknown object");
        }
    }
}

class PacmanController {

    private PacmanModel model;
    private PacmanView view;

    public PacmanController(PacmanModel model, PacmanView view) {
        this.model = model;
        this.view = view;
    }
}
