import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {
	private BufferedImage image;
	private String direction;
	private int x;
	private int y;
	private int speed = 3;
	private GamePanel gp;
	private boolean visibility = true;
	
	public Projectile() {}
	
	public Projectile (String direction, int x, int y, GamePanel gp) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/player-bullet.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.direction = direction;
		this.x = x + 16;
		this.y = y + 32 + 16;
		this.gp = gp;
		
	}
	
	public boolean isOffScreen() {
	    return x < 0 || x > gp.maxScreenCol * gp.tileSize ||
	           y < 0 || y > gp.maxScreenRow * gp.tileSize;
	}
	
	public void update() {
		if (direction == "up") y -= speed;
		else if (direction.equals("down")) y += speed;
		else if (direction.equals("left")) x -= speed;
		else if (direction.equals("right")) x += speed;
	}
	
	
	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void draw(Graphics2D g2) {
		if(this.visibility) {
			g2.drawImage(image, x, y, gp.tileSize / 2, gp.tileSize / 2, null);
		}
		else {
			this.x = 0;
			this.y = 0;
		}

	}
}
