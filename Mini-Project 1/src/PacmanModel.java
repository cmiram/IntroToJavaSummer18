/***
 * Contains methods for maintaining game state info
 */

public class PacmanModel {
    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public enum GameObject {
        PACMAN, COOKIE, EMPTY, VISITED
    }

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
