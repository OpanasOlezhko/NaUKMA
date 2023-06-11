import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    @Serial
    private static final long serialVersionUID = 1L;

    private final BufferedImage pause, refresh, menu;

    private final int boardHeight = 20, boardWidth = 10;

    public static final int blockSize = 30;

    private Image background;
    private final Shape booster = new Shape(new int[][]{{1}}, this, Color.GREEN, true);
    private final Color[][] board = new Color[boardHeight][boardWidth];

    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<Shape> lvl3shapes = new ArrayList<>();

    private static Shape currentShape, nextShape;

    private final Timer looper;

    private final int FPS = 60;

    private int delay = 1000 / FPS;

    private int mouseX, mouseY;

    private boolean leftClick = false;

    private final Rectangle stopBounds, refreshBounds, menuBounds;

    private boolean gamePaused = false;

    private boolean gameOver = false;
    
    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"), 
        Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
    private Random random = new Random();
    private Timer buttonLapse = new Timer(300, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonLapse.stop();
        }
    });

    public int level = 1;
    private int score = 0;
    private int scoreCap = 500;
    private WindowGame windowGame;
    public Board(WindowGame windowGame){

        pause = ImageLoader.loadImage("/pause.png");
        refresh = ImageLoader.loadImage("/refresh.png");
        menu = ImageLoader.loadImage("/menu.png");

        mouseX = 0;
        mouseY = 0;

        this.windowGame = windowGame;
        stopBounds = new Rectangle(350, 500, pause.getWidth(), pause.getHeight() + pause.getHeight() / 2);
        refreshBounds = new Rectangle(350, 500 - refresh.getHeight() - 20, refresh.getWidth(),
                refresh.getHeight() + refresh.getHeight() / 2);
        menuBounds = new Rectangle(350, 500 - refresh.getHeight() - pause.getHeight() - 40, menu.getWidth(), menu.getHeight() + menu.getHeight() / 2);

        looper = new Timer(delay, new GameLooper());

        shapes.add(new Shape(new int[][]{
            {1, 1, 1, 1} // I shape;
        }, this, colors[0], false));

        shapes.add(new Shape(new int[][]{
            {1, 1, 1},
            {0, 1, 0}, // T shape;
        }, this, colors[1], false));

        shapes.add(new Shape(new int[][]{
            {1, 1, 1},
            {1, 0, 0}, // L shape;
        }, this, colors[2], false));

        shapes.add(new Shape(new int[][]{
            {1, 1, 1},
            {0, 0, 1}, // J shape;
        }, this, colors[3], false));

        shapes.add(new Shape(new int[][]{
            {0, 1, 1},
            {1, 1, 0}, // S shape;
        }, this, colors[4], false));

        shapes.add(new Shape(new int[][]{
            {1, 1, 0},
            {0, 1, 1}, // Z shape;
        }, this, colors[5], false));

        shapes.add(new Shape(new int[][]{
            {1, 1},
            {1, 1}, // O shape;
        }, this, colors[6], false));

        lvl3shapes.add(new Shape(new int[][]{
                {1, 1, 1},
                {0, 1, 0},
                {0, 1, 0}
        }, this, colors[0], false));

        lvl3shapes.add(new Shape(new int[][]{
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 1}
        }, this, colors[1], false));

        lvl3shapes.add(new Shape(new int[][]{
                {1, 1, 1},
                {1, 1, 1},
        }, this, colors[6], false));
    }

    private void update() {
        if (stopBounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning() && !gameOver) {
            buttonLapse.start();
            gamePaused = !gamePaused;
        }

        if (refreshBounds.contains(mouseX, mouseY) && leftClick) {
            startGame();
        }

        if(menuBounds.contains(mouseX, mouseY) && leftClick) {
            gamePaused = false;
            stopGame();
            windowGame.returnToMenu(this);
        }

        if (gamePaused || gameOver) {
            return;
        }

        if (score >=3000){
            stopGame();
            windowGame.playerWin();
        }

        currentShape.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(background, -7, 0, this);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                }

            }
        }
        g.setColor(Color.BLACK);

        g.setFont(new Font("Georgia", Font.BOLD, 20));
        g.drawString("NEXT:", WindowGame.WIDTH - 125, 30);

        g.setColor(nextShape.getColor());
        for (int row = 0; row < nextShape.getCoords().length; row++) {
            for (int col = 0; col < nextShape.getCoords()[0].length; col++) {
                if (nextShape.getCoords()[row][col] != 0) {
                    g.fillRect(col * 30 + 320, row * 30 + 50, Board.blockSize, Board.blockSize);
                }
            }
        }
        currentShape.render(g);

        if (stopBounds.contains(mouseX, mouseY)) {
            g.drawImage(pause.getScaledInstance(pause.getWidth() + 3, pause.getHeight() + 3, BufferedImage.SCALE_DEFAULT), stopBounds.x + 3, stopBounds.y + 3, null);
        } else {
            g.drawImage(pause, stopBounds.x, stopBounds.y, null);
        }

        if (refreshBounds.contains(mouseX, mouseY)) {
            g.drawImage(refresh.getScaledInstance(refresh.getWidth() + 3, refresh.getHeight() + 3,
                    BufferedImage.SCALE_DEFAULT), refreshBounds.x + 3, refreshBounds.y + 3, null);
        } else {
            g.drawImage(refresh, refreshBounds.x, refreshBounds.y, null);
        }
        if (menuBounds.contains(mouseX, mouseY)) {
            g.drawImage(menu.getScaledInstance(menu.getWidth() + 3, menu.getHeight() + 3, BufferedImage.SCALE_DEFAULT), menuBounds.x + 3, menuBounds.y + 3, null);
        } else {
            g.drawImage(menu, menuBounds.x, menuBounds.y, null);
        }

        if (gamePaused) {
            String gamePausedString = "GAME PAUSED";
            g.setColor(Color.BLACK);
            g.setFont(new Font("Georgia", Font.BOLD, 30));
            g.drawString(gamePausedString, 35, WindowGame.HEIGHT / 2);
        }
        if (gameOver) {
            windowGame.gameOver();
        }
        g.setColor(Color.BLACK);

        g.setFont(new Font("Calibri", Font.BOLD, 25));

        g.drawString("LEVEL: " + level, WindowGame.WIDTH - 125, WindowGame.HEIGHT / 2 - 150);

        g.drawString("SCORE:", WindowGame.WIDTH - 125, WindowGame.HEIGHT / 2-50);
        g.drawString(score + "", WindowGame.WIDTH - 125, WindowGame.HEIGHT / 2 -20);

        g.setColor(Color.BLACK);

        for (int i = 0; i <= boardHeight; i++) {
            g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
        }
        for (int j = 0; j <= boardWidth; j++) {
            g.drawLine(j * blockSize, 0, j * blockSize, boardHeight * 30);
        }
    }

    public void setNextShape() {
        boolean boosterShape = false;
        int index = random.nextInt(shapes.size());
        if (shapes.get(index) == booster)
            boosterShape = true;
        int colorIndex = random.nextInt(colors.length);
        nextShape = new Shape(shapes.get(index).getCoords(), this, colors[colorIndex], boosterShape);
        if(level==3 && !shapes.containsAll(lvl3shapes))
            shapes.addAll(lvl3shapes);
        else if (level<3 && shapes.containsAll(lvl3shapes))
            shapes.removeAll(lvl3shapes);
        if(level>=2 && !shapes.contains(booster))
            shapes.add(booster);
        else if (level==1 && shapes.contains(booster))
            shapes.remove(booster);
    }

    public void setCurrentShape() {
        currentShape = nextShape;
        setNextShape();

        for (int row = 0; row < currentShape.getCoords().length; row++) {
            for (int col = 0; col < currentShape.getCoords()[0].length; col++) {
                if (currentShape.getCoords()[row][col] != 0) {
                    if (board[currentShape.getY() + row][currentShape.getX() + col] != null) {
                        gameOver = true;
                    }
                }
            }
        }

    }

    public Color[][] getBoard() {
        return board;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            currentShape.rotateShape();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentShape.setDeltaX(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            currentShape.setDeltaX(-1);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentShape.speedUp();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            currentShape.speedDown();
//        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void startGame() {
        stopGame();
        setNextShape();
        setCurrentShape();
        gameOver = false;
        looper.start();
        background = Toolkit.getDefaultToolkit().createImage("lvl"+level+"bgedited.png");
    }

    public void stopGame() {
        score = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = null;
            }
        }
        looper.stop();
    }

    class GameLooper implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            update();
            repaint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void addScore() {
        score+=100;

        if (score>=scoreCap && level <3) {
            level++;
            WindowGame.musicPlayer.playSound(WindowGame.musicPlayer.newLevelSound);
            scoreCap=1200;
        }
    }

    public void applyBoost() {
        int boostX = currentShape.getX();
        int boostY = currentShape.getY();
        WindowGame.musicPlayer.playSound(WindowGame.musicPlayer.explosionSound);
        for (int row = boostY - 2; row <= boostY + 2; row++) {
            for (int col = boostX - 2; col <= boostX + 2; col++) {
                if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
                    board[row][col] = null;
                    score+=10;
                }
            }
        }
    }


}
