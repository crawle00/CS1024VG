import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	public Thread thread;
	public KeyHandler kh = new KeyHandler();
	public Player player = new Player(this, kh);
	public Boss boss = new Boss(100, 0, 0);
	
	final int tileSize = 16 * 3; // 16x16 and scale of 3
	final int maxScreenCol = 16;
	final int maxScreenRow = 9;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(maxScreenCol*tileSize, maxScreenRow*tileSize));
		this.addKeyListener(kh);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void update() {
		player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
		g2.dispose();
		Toolkit.getDefaultToolkit().sync();
	}
	
	@Override
	public void run() {
		for(;;) {
			try {
				update();
				repaint();
				Thread.sleep(2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}