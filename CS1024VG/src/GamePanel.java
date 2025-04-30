import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;

import static GamePanel.GameState.LOSS;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
	public Thread thread;
	public KeyHandler kh = new KeyHandler();
	public Player player = new Player(this, kh);
	public Boss boss = new Boss(this, 100, 650, 150);
	public UI UI = new UI();//THIEN CHANGED THIS???????
	public ArrayList<Projectile> projectiles = new ArrayList<>();
	public ArrayList<BossProjectile> bossProjectiles = new ArrayList<>();
	
	
	final int tileSize = 16 * 3; // 16x16 and scale of 3
	final int maxScreenCol = 16;
	final int maxScreenRow = 9;
	//THIEN CHANGE HERE
	enum GameState {
		PLAYING, WIN, LOSS;
	}
	GameState gameState = GameState.PLAYING;
	//END OF THIEN CHANGE
	
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
		if(gameState == GameState.PLAYING) { //CHANGE THIS AND PUT ALL THE UPDATE STUFF INSIDE OF IT
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
		    //idk if this works
		    Iterator<BossProjectile> biter = bossProjectiles.iterator();
		    while (biter.hasNext()) {
		        Projectile b = biter.next();
		        b.update();
		        if (b.isOffScreen()) {
		            biter.remove();
		        }
		    }
		    //collision for boss tweak later
		    if((player.getPlayerProjectile().getX() >= 600 && player.getPlayerProjectile().getX() <= 700) && (player.getPlayerProjectile().getY() >= 110 && player.getPlayerProjectile().getY() <= 260)) {
				boss.setBossHealth(boss.getBossHealth()-3);
				UI.bossHPBarWidth = boss.bossHealth*3;//THIEN CHANGED THIS?????????
				player.getPlayerProjectile();
				System.out.println(boss.getBossHealth());
				player.getPlayerProjectile().setVisibility(false);
			}
		    //reset boss health
		    if(kh.r) {
		    	boss.setBossHealth(100);
		    }
		    //THIEN CHANGE HERERERER
		    if (boss.bossHealth <= 0) {
	            gameState = GameState.WIN;
	        }
		    //EN OF CAHGENNGENNENE
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(gameState == GameState.PLAYING) {//CHANGE HERE DO THE SAME THING AS YOU DID IN UPDATE
			player.draw(g2);
			boss.drawBoss(g2);
			UI.drawUI(g2);//THIEN CHANGE HERE/
			for (Projectile p : projectiles) {
		        p.draw(g2);
		    }
			g2.dispose();
			Toolkit.getDefaultToolkit().sync();
		}else if(gameState == GameState.WIN) {
			g2.drawString("You WIN!!!", (maxScreenCol*tileSize)/2 ,(maxScreenRow*tileSize)/2);
		}else if(gameState == GameState.LOSS) {
			g2.drawString("you lose :((((", (maxScreenCol*tileSize)/2 ,(maxScreenRow*tileSize)/2);
		}
	
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