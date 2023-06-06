import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    private int mouseX, mouseY;
    private boolean leftClick = false;
    Image gameOver, restartButton;
    private Rectangle restartBounds;
    private Timer timer;
    private Timer buttonLapse = new Timer(300, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonLapse.stop();
        }
    });

    public GameOver(){
        addMouseListener(this);
        addMouseMotionListener(this);
        restartButton = ImageLoader.loadImage("/restart.png");
        gameOver = Toolkit.getDefaultToolkit().createImage("game-over.gif");
        restartBounds = new Rectangle(155, 450, 150, 50);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        g.fillRect(WindowGame.WIDTH/4, WindowGame.HEIGHT/4, WindowGame.WIDTH/2, WindowGame.HEIGHT/2);

        g.setColor(Color.BLACK);

        g.drawRect(WindowGame.WIDTH/4, WindowGame.HEIGHT/4, WindowGame.WIDTH/2, WindowGame.HEIGHT/2);


        if (gameOver != null) {
            g.drawImage(gameOver, -4, 150, this);
        }

        if (restartButton != null) {
            g.drawImage(restartButton, 155, 450, this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
