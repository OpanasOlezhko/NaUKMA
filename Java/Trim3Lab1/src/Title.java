import java.awt.*;
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

	Image holologo, tetrislogo, playbutton;

	private int delay = 1000 / FPS;
	private static final long serialVersionUID = 1L;
	private BufferedImage instructions, infobutton;
	private WindowGame window;
	private Rectangle infobounds, playbounds;
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
		holologo = Toolkit.getDefaultToolkit().createImage("holo_logo.gif");
		tetrislogo = Toolkit.getDefaultToolkit().createImage("tetris_logo.gif");
		playbutton = Toolkit.getDefaultToolkit().createImage("play_button.png");
		infobounds = new Rectangle(20, 20, infobutton.getWidth(), infobutton.getHeight());
		playbounds = new Rectangle(155, 450, 150, 50);
		timer = new Timer(1000/60, e -> {
			repaint();
			update();
		});

		timer.start();
		this.window = window;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.setColor(Color.WHITE);

		g.fillRect(0, 0, WindowGame.WIDTH, WindowGame.HEIGHT);

		if (holologo != null) {
			g.drawImage(holologo, 125, -83, this);
		}

		if (tetrislogo != null) {
			g.drawImage(tetrislogo, -4, 150, this);
		}

		if (playbutton != null) {
			g.drawImage(playbutton, 155, 450, this);
		}

		g.setColor(Color.BLACK);
		g.drawString("The", 130, 50);
		g.drawString("team", 290, 120);
		g.drawString("presents", 200, 140);
		g.drawString("Maksym Loshak", 180, 337);
		g.drawString("Oleh Khodko", 187, 357);
		g.drawString("FI, NaUKMA, 2023", 175, 377);

		if(viewingInfo) {
			g.drawImage(instructions, WindowGame.WIDTH / 2 - instructions.getWidth() / 2,
					30 - instructions.getHeight() / 2 + 150, null);
		}

		if (infobounds.contains(mouseX, mouseY)) {
			g.drawImage(infobutton.getScaledInstance(infobutton.getWidth() + 3, infobutton.getHeight() + 3, BufferedImage.SCALE_DEFAULT), infobounds.x + 3, infobounds.y + 3, null);
		} else {
			g.drawImage(infobutton, infobounds.x, infobounds.y, null);
		}

		if (playbounds.contains(mouseX, mouseY)) {
			g.drawImage(playbutton, 158, 453, this);
		} else {
			g.drawImage(playbutton, 155, 450, this);
		}

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
		if (playbounds.contains(mouseX, mouseY) && leftClick && !buttonLapse.isRunning()) {
			window.startTetris();
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
