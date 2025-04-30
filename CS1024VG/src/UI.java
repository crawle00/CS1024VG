import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class UI {
	BufferedImage bossHPOutline, playerHPOutline, bossHPBar,playerHPBar,shootCD;
	public int bossHPBarWidth = 300;
	public static int playerHPBarWidth = 100;
	public UI(){
		try {
			bossHPOutline = ImageIO.read(getClass().getResourceAsStream("/UI/healthbar.png"));
			playerHPOutline = ImageIO.read(getClass().getResourceAsStream("/UI/playerhealthbar.png"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
	}
	
	public void drawUI(Graphics2D g2) {
		BufferedImage bossHPOutlineImage = null;
		BufferedImage playerHPOutlineImage = null;
		
		bossHPOutlineImage = bossHPOutline;
		playerHPOutlineImage = playerHPOutline;
		
		
		g2.setColor(Color.RED);
	    g2.fillRect(249, 35, (int) (bossHPBarWidth*(126.0/150)), (int) (50*(45.0/75))); //bossHPBar
	    g2.fillRect(21, 371, (int) (playerHPBarWidth*(88.0/100)), (int) (50*(39.0/50)));
		//draws outline for hpbar
		g2.drawImage(bossHPOutlineImage, 225, 25, 300, 50, null);
		g2.drawImage(playerHPOutlineImage, 15, 365, 100, 50,null);
	}
}
