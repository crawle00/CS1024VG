import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean w, a, s, d;
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) w = true;
		if (e.getKeyCode() == KeyEvent.VK_A) a = true;
		if (e.getKeyCode() == KeyEvent.VK_S) s = true;
		if (e.getKeyCode() == KeyEvent.VK_D) d = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) w = false;
		if (e.getKeyCode() == KeyEvent.VK_A) a = false;
		if (e.getKeyCode() == KeyEvent.VK_S) s = false;
		if (e.getKeyCode() == KeyEvent.VK_D) d = false;
	}

}
