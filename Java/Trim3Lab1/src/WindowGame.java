import javax.swing.*;
import java.lang.reflect.Type;

public class WindowGame {
    public static final int WIDTH = 450, HEIGHT = 635;

    private Board board;
    private Title title;
    private LevelsMenu levelsMenu;
    private JFrame window;
    private GameOverMenu gameOver;
    private YouWinMenu youWin;
    public int startLevel = 1;
    public static MusicPlayer musicPlayer = new MusicPlayer();

    public WindowGame() {

        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        board = new Board(this);
        title = new Title(this);
        levelsMenu = new LevelsMenu(this);
        gameOver = new GameOverMenu(this);
        youWin = new YouWinMenu(this);

        window.addKeyListener(board);
        window.addKeyListener(title);

        window.add(title);

        window.setVisible(true);
        musicPlayer.playMusic("menu-soundtrack.wav");
    }

    public void startTetris(JPanel panel) {
        window.remove(panel);
        window.addMouseMotionListener(board);
        window.addMouseListener(board);
        window.add(board);
        board.level = startLevel;
        board.startGame();
        window.revalidate();
        musicPlayer.stopMusic();
        musicPlayer.playMusic("main-soundtrack.wav");
    }


    public void openLevelsMenu(){
        window.remove(title);
        window.addMouseMotionListener(levelsMenu);
        window.addMouseListener(levelsMenu);
        window.add(levelsMenu);
        window.revalidate();
    }

    public void returnToMenu(JPanel panel){
        window.remove(panel);
        window.add(title);
        musicPlayer.stopMusic();
        musicPlayer.playMusic("menu-soundtrack.wav");
        window.revalidate();
    }

    public void gameOver(){
        window.remove(board);
        window.add(gameOver);
        musicPlayer.stopMusic();
        musicPlayer.playSound(musicPlayer.gameOverSound);
        musicPlayer.playMusic("menu-soundtrack.wav");
        window.revalidate();
    }

    public void playerWin(){
        window.remove(board);
        window.add(youWin);
        musicPlayer.stopMusic();
        musicPlayer.playSound(musicPlayer.victorySound);
        musicPlayer.playMusic("menu-soundtrack.wav");
        window.revalidate();
    }


    public static void main(String[] args) {
        new WindowGame();

    }

}
