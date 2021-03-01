// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).

package com.neet.DiamondHunter.GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.Keys;

public class PauseState extends GameState {
	
	private BufferedImage bg ;

	
	public PauseState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.STOPBG[0][0];

	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 16, 16, null);

		
		Content.drawString(g, "paused", 40, 30);
		
		Content.drawString(g, "press space ", 20, 48);
		Content.drawString(g, " to exit", 30, 60);
		
		Content.drawString(g, "press esc ", 26, 94);
		Content.drawString(g, " to return", 18, 106);


		
	}
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(false);
		}
		if(Keys.isPressed(Keys.SPACE)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENU);
		}
	}
	
}
