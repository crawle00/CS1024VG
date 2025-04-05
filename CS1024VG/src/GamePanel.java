import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	public Thread thread;
	public KeyHandler kh = new KeyHandler();
	
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
		if (kh.w) System.out.println("w");
		else if (kh.a) System.out.println("a");
		else if (kh.s) System.out.println("s");
		else if (kh.d) System.out.println("d");
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
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