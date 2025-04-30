import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UI {
	BufferedImage bossHPOutline, playerHPOutline, bossHPBar,playerHPBar,shootCD;
	public UI(){
		try {
			bossHPOutline = ImageIO.read(getClass().getResourceAsStream("/UI/healthbar.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
	}
	
	public void drawBoss(Graphics2D g2) {
		BufferedImage bossHPOutlineImage = null;
		
		bossHPOutlineImage = bossHPOutline;
		
		g2.drawImage(bossHPOutlineImage, 384, 100, 150, 75, null);
}
