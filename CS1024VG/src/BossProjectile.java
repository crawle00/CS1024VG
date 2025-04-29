import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BossProjectile extends Projectile {

	private BufferedImage bpimage;
	private String bpdirection;
	private int bpx;
	private int bpy;
	private int bpspeed = 2;
	private GamePanel bpgp;
	
	
	
	
	public BossProjectile(String direction, int x, int y, GamePanel gp) {
		super(direction, x, y, gp);
		try {
			bpimage = ImageIO.read(getClass().getResourceAsStream("BossProjectile.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.bpdirection = direction;
		this.bpx = x + 16;
		this.bpy = y + 32 + 16;
		this.bpgp = gp;
	}
	
	@Override
	public boolean isOffScreen() {
	    return bpx < 0 || bpx > bpgp.maxScreenCol * bpgp.tileSize ||
	           bpy < 0 || bpy > bpgp.maxScreenRow * bpgp.tileSize;
	}
	
	@Override
	public void update() {
		if (bpdirection == "up") bpy -= bpspeed;
		else if (bpdirection.equals("down")) bpy += bpspeed;
		else if (bpdirection.equals("left")) bpx -= bpspeed;
		else if (bpdirection.equals("right")) bpx += bpspeed;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(bpimage, bpx, bpy, bpgp.tileSize / 2, bpgp.tileSize / 2, null);
	}

}
