import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	public Thread thread;
	
	final int tileSize = 16 * 3; // 16x16 and scale of 3
	final int maxScreenCol = 16;
	final int maxScreenRow = 9;
	
	public GamePanel() {
		thread = new Thread();
		this.setPreferredSize(new Dimension(maxScreenCol*tileSize, maxScreenRow*tileSize));
	}
	
	public void start() {
		thread.start();
	}
	
	public void update() {
		
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
				Thread.sleep(2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}