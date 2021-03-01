// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).

package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class HelpState extends GameState {
	
	private BufferedImage bg , diamond;

	
	public HelpState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.HELPBG[0][0];
		diamond = Content.HPACKAGE[0][0];

		
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);

		
		
		Content.drawString(g, "W", 96, 42);
		Content.drawString(g, "A S D", 80, 52);
		
		Content.drawString(g, "movement:", 2, 50);
		
		Content.drawString(g, "space", 74, 66);
		Content.drawString(g, "attack:", 10, 66);
		
		Content.drawString(g, "stats:", 24, 82);
		Content.drawString(g, "e", 80, 82);
		
		Content.drawString(g, "exit:", 22, 98);
		Content.drawString(g, "esc", 70, 98);
		
		g.drawImage(diamond,10 ,120, null);
		Content.drawString(g, ": gives hp", 32, 124);




		
	}
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU);
		}

	}
	
}