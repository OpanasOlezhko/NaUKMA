import javax.swing.JFrame;

public class WindowGame {
    public static final int WIDTH = 450, HEIGHT = 635;

    private Board board;
    private Title title;
    private LevelsMenu levelsMenu;
    private JFrame window;
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

        window.addKeyListener(board);
        window.addKeyListener(title);

        window.add(title);

        window.setVisible(true);
        musicPlayer.playMusic("menu-soundtrack.wav");
    }

    public void startTetris() {
        window.remove(levelsMenu);
        window.addMouseMotionListener(board);
        window.addMouseListener(board);
        window.add(board);
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

    public void returnToMenu(){
//        window.addKeyListener(title);
//        window.removeMouseMotionListener(board);
//        window.removeMouseListener(board);
        window.remove(board);
        window.add(title);
        musicPlayer.stopMusic();
        musicPlayer.playMusic("menu-soundtrack.wav");

    }

    public static void main(String[] args) {
        new WindowGame();

    }

}
