import java.awt.image.BufferedImage;

public class Projectile {
	public BufferedImage image;
	public String direction;
	public int x;
	public int y;
	
	
	public Projectile (String direction, int x, int y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
		System.out.println("shot " + direction);
	}
	
	
}
