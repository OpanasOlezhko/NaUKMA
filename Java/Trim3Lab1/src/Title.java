import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Title extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

	private int mouseX, mouseY;
	private boolean leftClick = false;
	private boolean viewingInfo = false;
	private int FPS = 60;

	private int delay = 1000 / FPS;
	private static final long serialVersionUID = 1L;
	private BufferedImage instructions, infobutton;
	private WindowGame window;
	private Rectangle infobounds;
	private BufferedImage[] playButton = new BufferedImage[2];
	private Timer timer;
	private Timer buttonLapse = new Timer(300, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			buttonLapse.stop();
		}
	});

	

	public Title(WindowGame window){
		addMouseListener(this);
		addMouseMotionListener(this);
		instructions = ImageLoader.loadImage("/arrow.png");
		infobutton = ImageLoader.loadImage("/info.png");
		infobounds = new Rectangle(20, 20, infobutton.getWidth(), infobutton.getHeight());
		timer = new Timer(1000/60, e -> {
			repaint();
			update();
		});

		timer.start();
		this.window = window;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.GRAY);
		
		g.fillRect(0, 0, WindowGame.WIDTH, WindowGame.HEIGHT);

		if(viewingInfo) {
			g.drawImage(instructions, WindowGame.WIDTH / 2 - instructions.getWidth() / 2,
					30 - instructions.getHeight() / 2 + 150, null);
		}

		if (infobounds.contains(mouseX, mouseY)) {
			g.drawImage(infobutton.getScaledInstance(infobutton.getWidth() + 3, infobutton.getHeight() + 3, BufferedImage.SCALE_DEFAULT), infobounds.x + 3, infobounds.y + 3, null);
		} else {
			g.drawImage(infobutton, infobounds.x, infobounds.y, null);
		}

		//g.drawImage(infobutton, 20, 20, null);
		
                g.setColor(Color.WHITE);
		g.drawString("Press space to play!", 150, WindowGame.HEIGHT / 2 + 100);

	}

	private void update() {
		if (infobounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning()) {
			buttonLapse.start();
			viewingInfo = !viewingInfo;
		}

	}

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_SPACE) {
            window.startTetris();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

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

}
