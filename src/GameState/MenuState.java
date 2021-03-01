// The main menu GameState.

package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.GameStateManager;

import Manager.Keys;

public class MenuState extends GameState {
	
	private BufferedImage bg;
	private BufferedImage diamond;
	
	private int currentOption = 0;
	private String[] options = {
		"START",
		"HELP",
		"TOP",
		"QUIT"
	};
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		bg = Content.MENUBG[0][0];
		diamond = Content.HPACKAGE[0][0];
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(bg, 0, 0, null);
		
		Content.drawString(g, options[0], 44, 90);
		Content.drawString(g, options[1], 48, 100);
		Content.drawString(g, options[2], 50, 110);
		Content.drawString(g, options[3], 48, 120);

		
		if(currentOption == 0) g.drawImage(diamond, 25, 86, null);
		else if(currentOption == 1) g.drawImage(diamond, 25, 96, null);
		else if(currentOption == 2) g.drawImage(diamond, 25, 106, null);
		else if(currentOption == 3) g.drawImage(diamond, 25, 116, null);

	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
			currentOption++;
		}
		if(Keys.isPressed(Keys.UP) && currentOption > 0) {
			currentOption--;
		}
		if(Keys.isPressed(Keys.SPACE)) {
			selectOption();
		}
	}
	
	private void selectOption() {
		if(currentOption == 0) {
			gsm.setState(GameStateManager.PLAY);
		}
		if(currentOption == 1){
			gsm.setState(GameStateManager.HELP);
		}
		if(currentOption == 2){
			gsm.setState(GameStateManager.TOP);
		}
			
		if(currentOption == 3) {
			System.exit(0);
		}
	}
	
}
