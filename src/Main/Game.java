// The entry point of the game.
// This class loads up a JFrame window and
// puts a GamePanel into it.

package Main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Manager.Content;

public class Game {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("The Forest");
		BufferedImage b = null;

		window.add(new GamePanel());
		window.setResizable(false);
		
		try {
			b = ImageIO.read(Content.class.getResourceAsStream("/Logo/SmallLogo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		window.setIconImage(b);
		
		
		window.pack();
		window.setLocationRelativeTo(null);
		
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
