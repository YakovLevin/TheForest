// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.

package com.neet.DiamondHunter.HUD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.neet.DiamondHunter.Entity.HPackage;
import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Content;

public class Hud {

	private int yoffset;

	private BufferedImage bar;
	private BufferedImage boat;
	private BufferedImage axe_1;
	private BufferedImage axe_2;
	private BufferedImage axe_3;

	private Player player;

	// private int numDiamonds;
	private Font font;
	private Color textColor;

	public Hud(Player p, ArrayList<HPackage> h) {

		player = p;
		// numDiamonds = d.size();
		yoffset = GamePanel.HEIGHT;

		bar = Content.BAR[0][0];
		boat = Content.ITEMS[0][0];
		axe_1 = Content.ITEMS[0][1];
		axe_2 = Content.ITEMS[0][2];
		axe_3 = Content.ITEMS[0][3];

		font = new Font("Arial", Font.PLAIN, 10);

		textColor = new Color(210, 0, 30);

	}

	public void draw(Graphics2D g) {

		// draw hud
		g.drawImage(bar, 0, yoffset, null);

		// draw diamond bar
		g.setColor(textColor);
		g.fillRect(36, yoffset + 4, (int) (38.0 * player.getHp() / 100), 8);// *
																			// player.numDiamonds()
																			// /
																			// numDiamonds

		// draw diamond amount
		g.setFont(font);
		String s = "HP:";
		Content.drawString(g, s, 4, yoffset + 3);

		/*
		 * String s = player.numDiamonds() + "/" + numDiamonds;
		 * 
		 * if(player.numDiamonds() >= 10) g.drawImage(diamond, 80, yoffset,
		 * null); else g.drawImage(diamond, 72, yoffset, null);
		 */

		// draw items
		if (player.hasBoat())
			g.drawImage(boat, 100, yoffset, null);

		if (player.hasAxe()) {
			if (player.getNumAxe() == 1)
				g.drawImage(axe_1, 112, yoffset, null);
			if (player.getNumAxe() == 2)
				g.drawImage(axe_2, 112, yoffset, null);
			if (player.getNumAxe() == 3)
				g.drawImage(axe_3, 112, yoffset, null);

		}

		// draw time
		int minutes = (int) (player.getTicks() / 1800);
		int seconds = (int) ((player.getTicks() / 30) % 60);
		if (minutes < 10) {
			if (seconds < 10)
				Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
			else
				Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
		} else {
			if (seconds < 10)
				Content.drawString(g, minutes + ":0" + seconds, 85, 3);
			else
				Content.drawString(g, minutes + ":" + seconds, 85, 3);
		}

	}

}
