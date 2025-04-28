import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boss {
	public int bossHealth = 100;
	
	public int bossx = 0;
	public int bossy = 0;
	
	public Boss(){
	}
	public Boss(int bossHealth, int bossx, int bossy) {
		this.bossHealth = bossHealth;
		this.bossx = bossx;
		this.bossy = bossy;
		try {
		BufferedImage phase1 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase1.png"));
		BufferedImage phase2 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase2.png"));
		BufferedImage phase3 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase3.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawBoss(Graphics2D g2) {
		BufferedImage image = null;
		
		
		
		g2.drawImage(image, bossx, bossy, 100, 100, null);
}
	
	
	public void update() {
	}
}
