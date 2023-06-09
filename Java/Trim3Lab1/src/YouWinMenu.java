import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YouWinMenu extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean leftClick = false;
    private boolean viewingInfo = false;
    private int FPS = 60;

    Image gameOverImg, restartButton, menuButton;
    private int delay = 1000 / FPS;
    private static final long serialVersionUID = 1L;
    private WindowGame window;
    private Rectangle restartbounds, menubounds;
    private Timer timer;
    private Timer buttonLapse = new Timer(300, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonLapse.stop();
        }
    });



    public YouWinMenu(WindowGame window){
        addMouseListener(this);
        addMouseMotionListener(this);
        gameOverImg = Toolkit.getDefaultToolkit().createImage("you_win_img.gif");
        restartButton = Toolkit.getDefaultToolkit().createImage("restart_button.png");
        menuButton = Toolkit.getDefaultToolkit().createImage("menu_button.png");
        restartbounds = new Rectangle(125, 410, 200, 40);
        menubounds = new Rectangle(170, 480, 110, 40);
        timer = new Timer(1000/60, e -> {
            repaint();
        });



        timer.start();
        this.window = window;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        g.fillRect(0, 0, WindowGame.WIDTH, WindowGame.HEIGHT);

        if (gameOverImg != null) {
            g.drawImage(gameOverImg, 75, 30, this);
        }

        if (restartbounds.contains(mouseX, mouseY)) {
            g.drawImage(restartButton, 128, 413, this);
        } else {
            g.drawImage(restartButton, 125, 410, this);
        }

        if (menubounds.contains(mouseX, mouseY)) {
            g.drawImage(menuButton, 173, 483, this);
        } else {
            g.drawImage(menuButton, 170, 480, this);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_SPACE) {
            window.startTetris(this);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

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
        if (restartbounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning()) {
            window.startTetris(this);
        }
        if (menubounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning()) {
            window.returnToMenu(this);
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

}
