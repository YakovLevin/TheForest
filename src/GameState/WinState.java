// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.

// Under two minutes = Speed Demon
// Under three minutes = Adventurer
// Under four minutes = Beginner
// Four minutes or greater = Bumbling Idiot

package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import Manager.Content;
import Manager.Data;
import Manager.GameStateManager;
import Manager.Keys;

public class WinState extends GameState {


	private long ticks;
	private int seconds;
	private int minutes;
	private BufferedImage bg;
	
	private String path = System.getProperty("user.dir");

	private String secondsPreS;
	private String minutesPreS;

	private int secondsP;
	private int minutesP;

	public WinState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
				
		ticks = Data.getTime();
		minutes = (int) (ticks / 1800);
		seconds = (int) ((ticks / 30) % 60);
		

		try {

			FileReader fr = new FileReader(path + "\\src\\SavedTop\\top.txt");
				
			BufferedReader br = new BufferedReader(fr);

			String currline = br.readLine();
			if (currline != null) {
				minutesPreS = currline;
				currline = br.readLine();
			}
			if (currline != null)
				secondsPreS = currline;

		} catch (IOException e) {
			e.printStackTrace();
		}
		if ((!secondsPreS.equals("non"))) {
			minutesP = Integer.parseInt(minutesPreS);
			secondsP = Integer.parseInt(secondsPreS);
		} else {
			minutesP = 99;
			secondsP = 99;
		}

		if (minutes <= minutesP) {
			if (minutes == minutesP) {
				if (seconds <= secondsP) {
					PrintWriter pw;
					try {
						pw = new PrintWriter(
								path + "\\src\\SavedTop\\top.txt");
						pw.println("" + minutes);
						pw.println("" + seconds);
						pw.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			} else {
				PrintWriter pw;
				try {
					pw = new PrintWriter(path + "\\src\\SavedTop\\top.txt");
					pw.println("" + minutes);
					pw.println("" + seconds);
					pw.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		bg = Content.WONBG[0][0];
	}

	public void update() {
		handleInput();

	}

	public void draw(Graphics2D g) {

		g.drawImage(bg, 0, 0, null);

		Content.drawString(g, "finish time", 20, 62);

		if (minutes < 10) {
			if (seconds < 10)
				Content.drawString(g, "0" + minutes + ":0" + seconds, 44, 74);
			else
				Content.drawString(g, "0" + minutes + ":" + seconds, 44, 74);
		} else {
			if (seconds < 10)
				Content.drawString(g, minutes + ":0" + seconds, 44, 74);
			else
				Content.drawString(g, minutes + ":" + seconds, 44, 74);
		}

		Content.drawString(g, "press esc to", 14, 100);
		Content.drawString(g, "return to the", 12, 112);
		Content.drawString(g, "main menu", 28, 124);

	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU);
		}
	}



}