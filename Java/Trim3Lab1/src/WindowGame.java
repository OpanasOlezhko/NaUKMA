import javax.swing.JFrame;

public class WindowGame {
    public static final int WIDTH = 450, HEIGHT = 635;

    private Board board;
    private Title title;
    private JFrame window;
    public static MusicPlayer musicPlayer = new MusicPlayer();

    public WindowGame() {

        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        board = new Board();
        title = new Title(this);

        window.addKeyListener(board);
        window.addKeyListener(title);

        window.add(title);

        window.setVisible(true);
        musicPlayer.playMusic("menu-soundtrack.wav");
    }

    public void startTetris() {
        window.remove(title);
        window.addMouseMotionListener(board);
        window.addMouseListener(board);
        window.add(board);
        board.startGame();
        window.revalidate();
        musicPlayer.stopMusic();
        musicPlayer.playMusic("main-soundtrack.wav");
    }

    public static void main(String[] args) {
        new WindowGame();

    }

}
