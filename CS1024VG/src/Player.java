import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	public int x = 100;
	public int y = 100;
	public int speed = 2;
	public int playerHealth = 100;
	
	private String direction = "down";
	private int spriteCounter = 0;
	private int spriteNum = 1;
	
	private int shootCooldown = 0;
	private final int maxCooldown = 200;
	
	public int hitCooldown = 0;
    public final int maxHitCooldown = 6000;
    
	Projectile p = new Projectile();
	
	public BufferedImage up1, up2, up3, up4, up5, up6;
	public BufferedImage down1, down2, down3, down4, down5, down6;
	public BufferedImage left1, left2, left3, left4, left5, left6;
	public BufferedImage right1, right2, right3, right4, right5, right6;
	
	GamePanel gp;
	KeyHandler kh;
	
	public Player(GamePanel gp, KeyHandler kh) {
		this.gp = gp;
		this.kh = kh;
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/up3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/up4.png"));
			up5 = ImageIO.read(getClass().getResourceAsStream("/player/up5.png"));
			up6 = ImageIO.read(getClass().getResourceAsStream("/player/up6.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/down3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/down4.png"));
			down5 = ImageIO.read(getClass().getResourceAsStream("/player/down5.png"));
			down6 = ImageIO.read(getClass().getResourceAsStream("/player/down6.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/left4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/player/left5.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/player/left6.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/right4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/player/right5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/player/right6.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		// walking
		if (kh.w) {
			direction = "up";
			if (y >= - gp.tileSize / 2) y -= speed;
		} else if (kh.a) {
			direction = "left";
			if (x >= 0) x -= speed;
		} else if (kh.s) {
			direction = "down";
			if (y <= gp.maxScreenRow * gp.tileSize - gp.tileSize * 2) y += speed;
		} else if (kh.d) {
			direction = "right";
			if (x <= gp.maxScreenCol * gp.tileSize - gp.tileSize) x += speed;
		}
		if (kh.w || kh.a || kh.s || kh.d) {
			spriteCounter++;
			if (spriteCounter > 30) {
				if (spriteNum == 6) spriteNum = 1;
				else spriteNum++;
				spriteCounter = 0;
				
			}
		}
		
		// projectile
		if (shootCooldown > 0) shootCooldown--;
		if (shootCooldown == 0) {
		    if (kh.up || kh.down || kh.left || kh.right) {
		         p = null;
		        if (kh.up) p = new Projectile("up", x, y, gp);
		        else if (kh.down) p = new Projectile("down", x, y, gp);
		        else if (kh.left) p = new Projectile("left", x, y, gp);
		        else if (kh.right) p = new Projectile("right", x, y, gp);
		        if (p != null) gp.projectiles.add(p);
		        shootCooldown = maxCooldown;
		    }
		}
		
		
	}
	
	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public Projectile getPlayerProjectile() {
		return p;
	}
	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if (spriteNum == 1) image = up1;
			if (spriteNum == 2) image = up2;
			if (spriteNum == 3) image = up3;
			if (spriteNum == 4) image = up4;
			if (spriteNum == 5) image = up5;
			if (spriteNum == 6) image = up6;
			break;
		case "down":
			if (spriteNum == 1) image = down1;
			if (spriteNum == 2) image = down2;
			if (spriteNum == 3) image = down3;
			if (spriteNum == 4) image = down4;
			if (spriteNum == 5) image = down5;
			if (spriteNum == 6) image = down6;
			break;
		case "left":
			if (spriteNum == 1) image = left1;
			if (spriteNum == 2) image = left2;
			if (spriteNum == 3) image = left3;
			if (spriteNum == 4) image = left4;
			if (spriteNum == 5) image = left5;
			if (spriteNum == 6) image = left6;
			break;
		case "right":
			if (spriteNum == 1) image = right1;
			if (spriteNum == 2) image = right2;
			if (spriteNum == 3) image = right3;
			if (spriteNum == 4) image = right4;
			if (spriteNum == 5) image = right5;
			if (spriteNum == 6) image = right6;
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize * 2, null);
	}
}
