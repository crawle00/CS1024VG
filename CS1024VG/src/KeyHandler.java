import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean w, a, s, d, up, down, left, right, r;
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) w = true;
		if (e.getKeyCode() == KeyEvent.VK_A) a = true;
		if (e.getKeyCode() == KeyEvent.VK_S) s = true;
		if (e.getKeyCode() == KeyEvent.VK_D) d = true;
		if (e.getKeyCode() == KeyEvent.VK_UP) up = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
		if (e.getKeyCode() == KeyEvent.VK_R) r = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) w = false;
		if (e.getKeyCode() == KeyEvent.VK_A) a = false;
		if (e.getKeyCode() == KeyEvent.VK_S) s = false;
		if (e.getKeyCode() == KeyEvent.VK_D) d = false;
		if (e.getKeyCode() == KeyEvent.VK_UP) up = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
		if (e.getKeyCode() == KeyEvent.VK_R) r = false;
	}

}
