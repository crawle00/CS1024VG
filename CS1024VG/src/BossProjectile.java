import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BossProjectile extends Projectile {
    private static final BufferedImage bpimage;
    static {
        try {
        	bpimage = ImageIO.read(BossProjectile.class.getResourceAsStream("/Boss/BossProjectile.png"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load boss projectile image", e);
        }
    }
    private String bpdirection;
    private int bpx, bpy;
    private int bpspeed = 1;
    private GamePanel bpgp;
    private Player player;
    private boolean alive = true;
    public int getBpspeed() {
		return bpspeed;
	}

	public void setBpspeed(int bpspeed) {
		this.bpspeed = bpspeed;
	}

	public BossProjectile() {}	

    public BossProjectile(String direction, int x, int y, GamePanel gp, Player player) {
        super(direction, x, y, gp);
        this.bpdirection = direction;
        this.bpx = x + 50; // Adjust projectile spawn point relative to boss
        this.bpy = y + 50;
        this.bpgp = gp;
        this.player = player;
    }

    @Override
    public boolean isOffScreen() {
        return bpx < 0 || bpx > bpgp.maxScreenCol * bpgp.tileSize ||
               bpy < 0 || bpy > bpgp.maxScreenRow * bpgp.tileSize;
    }

    @Override
    public void update() {
        switch (bpdirection) {
            case "up" -> bpy -= bpspeed;
            case "down" -> bpy += bpspeed;
            case "left" -> bpx -= bpspeed;
            case "right" -> bpx += bpspeed;
            case "upleft" -> { bpx -= bpspeed; bpy -= bpspeed; }
            case "upright" -> { bpx += bpspeed; bpy -= bpspeed; }
            case "downleft" -> { bpx -= bpspeed; bpy += bpspeed; }
            case "downright" -> { bpx += bpspeed; bpy += bpspeed; }
        }
        if (player.hitCooldown > 0) player.hitCooldown--;
        if (player.hitCooldown == 0 && bpx > player.x -20 && bpx < player.x + 20 && bpy > player.y && bpy < player.y+100) {
        	player.playerHealth -= 25;
        	player.hitCooldown = player.maxHitCooldown;
        	UI.playerHPBarWidth = player.playerHealth;
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(bpimage, bpx, bpy, bpgp.tileSize / 2, bpgp.tileSize / 2, null);
    }
}