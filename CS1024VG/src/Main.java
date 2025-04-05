import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("CS1024VG");
		GamePanel game = new GamePanel();
		
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(960,540));
		frame.setResizable(false);
		frame.setVisible(true);
		
		game.start();
	}
}