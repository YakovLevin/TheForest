// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).

package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entity.Player;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class StatsState extends GameState {

	private BufferedImage bg;


	public StatsState(GameStateManager gsm) {
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

		Content.drawString(g, "stats", 42, 30);

		Content.drawString(g, "HP :", 24, 48);
		Content.drawString(g, Player.getHp() + "", 68, 48);

		Content.drawString(g, "Level :", 22, 62);
		Content.drawString(g, Player.getLevel() + "", 80, 62);

		Content.drawString(g, "EXP :", 24, 76);
		Content.drawString(g, Player.getExp()+"/"+Player.getExpCap(), 68, 76);

		
		g.drawRect(30, 90, 70, 10);
		g.setColor(Color.YELLOW);
		g.fillRect(31, 91, (int)( (70.0 * Player.getExp())/Player.getExpCap()), 9);
		g.setColor(Color.black);

		Content.drawString(g, "power :", 22, 108);
		Content.drawString(g, Player.getPower() + "", 80, 108);

	}

	public void handleInput() {
		if (Keys.isPressed(Keys.E)) {
			gsm.setStats(false);
		}

	}

}