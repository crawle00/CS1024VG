import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	public Thread thread;
	public KeyHandler kh = new KeyHandler();
	public Player player = new Player(this, kh);
	public Boss boss = new Boss(100, 350, 150);
	public ArrayList<Projectile> projectiles = new ArrayList<>();
	
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
		boss.update();
		
		Iterator<Projectile> iter = projectiles.iterator();
	    while (iter.hasNext()) {
	        Projectile p = iter.next();
	        p.update();
	        if (p.isOffScreen()) {
	            iter.remove();
	        }
	    }
	    //collision for boss tweak later
	    //not working yet
	    if(player.getPlayerProjectile().getX() >= 50 && player.getPlayerProjectile().getX() <= 150 && player.getPlayerProjectile().getY() >= 300 && player.getPlayerProjectile().getY() <= 400) {
			boss.setBossHealth(boss.getBossHealth()-10);
			System.out.println(boss.getBossHealth());
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
		boss.drawBoss(g2);
		for (Projectile p : projectiles) {
	        p.draw(g2);
	    }
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