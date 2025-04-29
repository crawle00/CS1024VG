import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boss {
	public int bossHealth = 100;
	
	public int bossx = 0;
	public int bossy = 0;
	BufferedImage phase1, phase2, phase3;
	
	public Boss(){
	}
	public Boss(int bossHealth, int bossx, int bossy) {
		this.bossHealth = bossHealth;
		this.bossx = bossx;
		this.bossy = bossy;
		try {
		phase1 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase1.png"));
		phase2 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase2.png"));
		phase3 = ImageIO.read(getClass().getResourceAsStream("/Boss/bossPhase3.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawBoss(Graphics2D g2) {
		BufferedImage image = null;
		
		if(this.bossHealth >= 70) {
			image = phase1;
		}
		else if(this.bossHealth >= 30) {
			image = phase2;
		}
		else if(this.bossHealth < 30 && this.bossHealth > 0) {
			image = phase3;
		}
		else {
			//Thien make a you win page
			System.out.println("You Win!");
		}
		
		
		
		g2.drawImage(image, bossx, bossy, 100, 100, null);
}
	
	
	public void update() {
		
	}
	public int getBossHealth() {
		return bossHealth;
	}
	public void setBossHealth(int bossHealth) {
		this.bossHealth = bossHealth;
	}
	public int getBossx() {
		return bossx;
	}
	public void setBossx(int bossx) {
		this.bossx = bossx;
	}
	public int getBossy() {
		return bossy;
	}
	public void setBossy(int bossy) {
		this.bossy = bossy;
	}
}
