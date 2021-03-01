// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).

package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class TopState extends GameState {

	private BufferedImage bg;
	private String secondsS, minutesS;
	private int minutesI, secondsI;
	private boolean reset ;
	private String path = System.getProperty("user.dir");

	public TopState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		bg = Content.TOPBG[0][0];
		reset = false;
		try { // Get the initial times

			FileReader fr = new FileReader(path + "\\src\\SavedTop\\top.txt");
			BufferedReader br = new BufferedReader(fr);

			String currline = br.readLine();
			minutesS = currline;
			currline = br.readLine();
			secondsS = currline;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		handleInput();
		if (reset)
			secondsS = "non";
	}

	public void draw(Graphics2D g) {
		g.drawImage(bg, 0, 0, null);
		Content.drawString(g, "time :", 40, 60);

		if (!secondsS.equals("non")) {
			minutesI = Integer.parseInt(minutesS);
			secondsI = Integer.parseInt(secondsS);

			if (minutesI >= 10 && secondsI >= 10)
				Content.drawString(g, minutesS + " : " + secondsS, 36, 86);
			else if (minutesI >= 10)
				Content.drawString(g, minutesS + " : 0" + secondsS, 36, 86);
			else if (secondsI >= 10)
				Content.drawString(g, "0" + minutesS + " : " + secondsS, 36, 86);
			else
				Content.drawString(g, "0" + minutesS + " : 0" + secondsS, 36, 86);

		} else
			Content.drawString(g, "non : non", 28, 86);

		g.setColor(Color.green);
		g.drawRect(23, 79, 82, 22);
		g.setColor(Color.black);
		g.drawRect(24, 80, 80, 20);
		g.setColor(Color.green);
		g.drawRect(25, 81, 78, 18);

		Content.drawString(g, "press space", 20, 114);
		Content.drawString(g, "to reset", 34, 122);

	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU);
		}
		if (Keys.isPressed(Keys.SPACE)) {
			reset = true;
			PrintWriter pw;
			try {
				pw = new PrintWriter(path + "\\src\\SavedTop\\top.txt");
				pw.println("non");
				pw.println("non");
				pw.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

}