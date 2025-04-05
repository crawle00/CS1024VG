import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	public Thread thread;
	
	public GamePanel() {
		thread = new Thread();
	}
	
	public void start() {
		thread.start();
	}
	
	@Override
	public void run() {
		for(;;) {
			try {
				Thread.sleep(2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}