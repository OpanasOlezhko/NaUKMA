import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelsMenu extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean leftClick = false;
    private Board board;
    private boolean viewingInfo = false;
    private int FPS = 60;

    Image level1Button, level2Button, level3Button, backgroundDown, backgroundUp;
    private int delay = 1000 / FPS;
    private static final long serialVersionUID = 1L;
    private WindowGame window;
    private Rectangle level1bounds, level2bounds, level3bounds;
    private Timer timer;
    private Timer buttonLapse = new Timer(300, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonLapse.stop();
        }
    });



    public LevelsMenu(WindowGame window){
        addMouseListener(this);
        addMouseMotionListener(this);
        level1Button = Toolkit.getDefaultToolkit().createImage("level1.png");
        level2Button = Toolkit.getDefaultToolkit().createImage("level2.png");
        level3Button = Toolkit.getDefaultToolkit().createImage("level3.png");
        backgroundDown = Toolkit.getDefaultToolkit().createImage("background1.png");
        backgroundUp = Toolkit.getDefaultToolkit().createImage("background.png");

        level1bounds = new Rectangle(125, 110, 200, 40);
        level2bounds = new Rectangle(125, 260, 200, 40);
        level3bounds = new Rectangle(125, 410, 200, 40);
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

        g.drawImage(backgroundUp, 0, 0, this);
        if (level1bounds.contains(mouseX, mouseY)) {
            g.drawImage(level1Button, 128, 103, this);
        } else {
            g.drawImage(level1Button, 125, 100, this);
        }

        if (level2bounds.contains(mouseX, mouseY)) {
            g.drawImage(level2Button, 128, 253, this);
        } else {
            g.drawImage(level2Button, 125, 250, this);
        }

        g.drawImage(backgroundDown, 0, getHeight()-235, this);
        if (level3bounds.contains(mouseX, mouseY)) {
            g.drawImage(level3Button, 128, 403, this);
        } else {
            g.drawImage(level3Button, 125, 400, this);
        }


//        g.setColor(Color.WHITE);
//        g.drawString("Press space to play!", 150, WindowGame.HEIGHT / 2 + 100);

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
        if (level1bounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning()) {
            window.startLevel = 1;
            window.startTetris(this);
        }
        if (level2bounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning()) {
            window.startLevel = 2;
            window.startTetris(this);
        }
        if (level3bounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning()) {
            window.startLevel  =3;
            window.startTetris(this);
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
