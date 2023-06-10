import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Shape {
    private Random random = new Random();
    private Color color;

    private int x, y;

    private long time, lastTime;

    private int normal = 600, fast = 50, hard = 400, extreme = 200;

    private int delay;

    private int[][] coords;

    private int[][] reference;

    private int deltaX;

    private Board board;
    private boolean booster;
    long deltaTime;


    private boolean collision = false, finalCollision = false, moveX = false;

    private int timePassedFromCollision = -1;

    public Shape(int[][] coords, Board board, Color color, boolean booster) {
        this.coords = coords;
        this.board = board;
        this.color = color;
        this.booster = booster;
        deltaX = 0;
        x = 4;
        y = 0;
        if(board.level == 1)
            delay = normal;
        else if(board.level == 2)
            delay = hard;
        else if (board.level == 3)
            delay = extreme;
        time = 0;
        lastTime = System.currentTimeMillis();
        reference = new int[coords.length][coords[0].length];

        System.arraycopy(coords, 0, reference, 0, coords.length);

    }


    public void update() {
        moveX = true;
        deltaTime = System.currentTimeMillis() - lastTime;
        time += deltaTime;
        lastTime = System.currentTimeMillis();

        if (collision && timePassedFromCollision > 400) {

            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[0].length; col++) {
                    if (coords[row][col] != 0) {
                        board.getBoard()[y + row][x + col] = color;
                    }
                }
            }
            if(!booster)
                WindowGame.musicPlayer.playSound(WindowGame.musicPlayer.landingSound);
            if (booster) {
                board.applyBoost();
            }
            checkLine();
            board.setCurrentShape();
            timePassedFromCollision = -1;
        }

        if (!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0)) {

            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[row].length; col++) {
                    if (coords[row][col] != 0) {
                        if (board.getBoard()[y + row][x + deltaX + col] != null) {
                            moveX = false;
                        }

                    }
                }
            }

            if (moveX) {
                x += deltaX;
            }

        }

        if (timePassedFromCollision == -1) {
            if (!(y + 1 + coords.length > 20)) {

                for (int row = 0; row < coords.length; row++) {
                    for (int col = 0; col < coords[row].length; col++) {
                        if (coords[row][col] != 0) {

                            if (board.getBoard()[y + 1 + row][x + col] != null) {
                                collision();
                            }
                        }
                    }
                }
                if (time > delay) {
                    y++;
                    time = 0;
                }
            } else {
                collision();
            }
        } else {
            timePassedFromCollision += deltaTime;
            if(timePassedFromCollision>=400)
                checkFinalCollision();
        }

        deltaX = 0;
    }
    private void checkFinalCollision(){
        if (!(y + 1 + coords.length > 20))
            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[row].length; col++) {
                    if (coords[row][col] != 0) {
                        if (board.getBoard()[y + 1 + row][x + col] != null) {
                            finalCollision = true;
                        }
                    }
                }
            }
        if (y + 1 + coords.length > 20)
            finalCollision = true;
        if (!finalCollision) {
            collision = false;
            timePassedFromCollision = -1;
        }
    }

    void collision() {
        collision = true;
        timePassedFromCollision = 0;
    }

    public void render(Graphics g) {
        if (booster) {
            Color[] colors = {Color.RED, Color.ORANGE, Color.BLACK, Color.YELLOW};
            int colorIndex = (int) (time / 150) % colors.length;
            g.setColor(colors[colorIndex]);
        } else {
            g.setColor(color);
        }
        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[0].length; col++) {
                if (coords[row][col] != 0) {
                    g.fillRect(col * 30 + x * 30, row * 30 + y * 30, Board.blockSize, Board.blockSize);
                }
            }
        }

    }

    private void checkLine() {
        int size = board.getBoard().length - 1;

        for (int i = board.getBoard().length - 1; i > 0; i--) {
            int count = 0;
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                if (board.getBoard()[i][j] != null) {
                    count++;
                }

                board.getBoard()[size][j] = board.getBoard()[i][j];
            }
            if (count < board.getBoard()[0].length) {
                size--;

            } else {
                board.addScore();
            }
        }
    }

    public void rotateShape() {

        int[][] rotatedShape;

        rotatedShape = transposeMatrix(coords);

        rotatedShape = reverseRows(rotatedShape);

        if ((x + rotatedShape[0].length > 10) || (y + rotatedShape.length > 20)) {
            return;
        }

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int col = 0; col < rotatedShape[row].length; col++) {
                if (rotatedShape[row][col] != 0) {
                    if (board.getBoard()[y + row][x + col] != null) {
                        return;
                    }
                }
            }
        }
        coords = rotatedShape;
    }

    private int[][] transposeMatrix(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][i] = matrix[i][j];
            }
        }
        return temp;
    }

    private int[][] reverseRows(int[][] matrix) {

        int middle = matrix.length / 2;

        for (int i = 0; i < middle; i++) {
            int[] temp = matrix[i];

            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        return matrix;

    }

    public Color getColor() {
        return color;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void speedUp() {
        delay = fast;
    }

    public int[][] getCoords() {
        return coords;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
