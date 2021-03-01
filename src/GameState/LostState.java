package GameState;

// Congratulations for finishing the game.
//Gives you a rank based on how long it took
//you to beat the game.

//Under two minutes = Speed Demon
//Under three minutes = Adventurer
//Under four minutes = Beginner
//Four minutes or greater = Bumbling Idiot


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class LostState extends GameState {



	private BufferedImage bg;


	public LostState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		
		bg = Content.LOSTBG[0][0];
	}

	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {

		g.drawImage(bg, 0, 0, null);

		Content.drawString(g, "press space", 20, 62);
		Content.drawString(g, "to try again", 16, 74);


	

		Content.drawString(g, "press esc to", 14, 100);
		Content.drawString(g, "return to the", 12, 112);
		Content.drawString(g, "main menu", 28, 124);



	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU);
		}
		if (Keys.isPressed(Keys.SPACE)) {
			gsm.setState(GameStateManager.PLAY);
		}
	}

}