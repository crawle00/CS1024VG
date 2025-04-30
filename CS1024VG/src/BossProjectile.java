import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BossProjectile extends Projectile {
    private BufferedImage bpimage;
    private String bpdirection;
    private int bpx, bpy;
    private int bpspeed = 1;
    private GamePanel bpgp;

    public BossProjectile(String direction, int x, int y, GamePanel gp) {
        super(direction, x, y, gp);
        this.bpdirection = direction;
        this.bpx = x + 50; // Adjust projectile spawn point relative to boss
        this.bpy = y + 50;
        this.bpgp = gp;

        try {
            bpimage = ImageIO.read(getClass().getResourceAsStream("/Boss/BossProjectile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(bpimage, bpx, bpy, bpgp.tileSize / 2, bpgp.tileSize / 2, null);
    }
}