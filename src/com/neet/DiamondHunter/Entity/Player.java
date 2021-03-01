// The only subclass the fully utilizes the
// Entity superclass (no other class requires
// movement in a tile based map).
// Contains all the gameplay associated with
// the Player.

package com.neet.DiamondHunter.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;



import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;

public class Player extends Entity {

	// sprites
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	private BufferedImage[] downBoatSprites;
	private BufferedImage[] leftBoatSprites;
	private BufferedImage[] rightBoatSprites;
	private BufferedImage[] upBoatSprites;

	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	private final int DOWNBOAT = 4;
	private final int LEFTBOAT = 5;
	private final int RIGHTBOAT = 6;
	private final int UPBOAT = 7;

	private Rectangle attackBox;
	private boolean attack;
	private boolean damaged;

	// gameplay
	private static int Hp;
	private static int Exp;
	private static int ExpCap;
	private static int Level;
	private static int power;

	private int axeNum;

	private boolean hasBoat;
	private boolean hasAxe;
	private boolean died;

	private boolean onWater;
	private long ticks;

	public Player(TileMap tm) {

		super(tm);

		width = 16;
		height = 16;
		cwidth = 12;
		cheight = 12;

		moveSpeed = 5;
		Hp = 100;
		Level = 1;
		Exp = 0;
		ExpCap = 4;
		power = 1;
		axeNum = 0;

		attack = false;
		died = false;

		attackBox = new Rectangle();
		attackBox.setBounds(0, 0, 16, 16);

		tileMap.setFinished(false);

		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];
		downBoatSprites = Content.PLAYER[4];
		leftBoatSprites = Content.PLAYER[5];
		rightBoatSprites = Content.PLAYER[6];
		upBoatSprites = Content.PLAYER[7];

		animation.setFrames(downSprites);
		animation.setDelay(10);

	}

	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}

	public void collectedHP() {
		if (Hp <= 80)
			Hp += 20;
		else if (Hp > 80 && Hp <= 100)
			Hp += 100 - Hp;

	}

	public void gotBoat() {
		hasBoat = true;
		tileMap.replace(22, 4);
	}

	public void gotAxe() {
		hasAxe = true;
		if (Item.getType() == 0) {
			axeNum = 3;
			power += 12;
			Exp += 6;
			updateExp();
		}
		if (Item.getType() == 2) {
			axeNum = 2;
			power += 7;
			Exp += 4;
			updateExp();

		}
		if (Item.getType() == 3) {
			axeNum = 1;
			power += 5;
			Exp += 2;
			updateExp();

		}
	}

	public boolean hasBoat() {
		return hasBoat;
	}

	public boolean hasAxe() {
		return hasAxe;
	}

	// Used to update time.
	public long getTicks() {
		return ticks;
	}

	// Keyboard input. Moves the player.
	public void setDown() {
		super.setDown();
	}

	public void setLeft() {
		super.setLeft();
	}

	public void setRight() {
		super.setRight();
	}

	public void setUp() {
		super.setUp();
	}

	// Keyboard input.
	// If Player has axe, dead trees in front
	// of the Player will be chopped down.
	public void setAction() {
		if (hasAxe) {
			if (currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
			}
			if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
			}
			if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
			}
			if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
			}

		}
	}

	public void update() {

		ticks++;

		updateExp();

		// check if on water
		boolean current = onWater;
		if (tileMap.getIndex(ydest / tileSize, xdest / tileSize) == 4) {
			onWater = true;
		} else {
			onWater = false;
		}

		// set animation
		if (down) {
			attackBox.setBounds((x + xmap - width / 2), (y + ymap - height / 2) + 17, 16, 16);

			if (onWater && currentAnimation != DOWNBOAT) {
				setAnimation(DOWNBOAT, downBoatSprites, 10);

			} else if (!onWater && currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if (left) {
			attackBox.setBounds((x + xmap - width / 2) - 17, (y + ymap - height / 2), 16, 16);

			if (onWater && currentAnimation != LEFTBOAT) {
				setAnimation(LEFTBOAT, leftBoatSprites, 10);
			} else if (!onWater && currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if (right) {
			attackBox.setBounds((x + xmap - width / 2) + 17, (y + ymap - height / 2), 16, 16);

			if (onWater && currentAnimation != RIGHTBOAT) {
				setAnimation(RIGHTBOAT, rightBoatSprites, 10);
			} else if (!onWater && currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if (up) {
			attackBox.setBounds((x + xmap - width / 2), (y + ymap - height / 2) - 17, 16, 16);

			if (onWater && currentAnimation != UPBOAT) {
				setAnimation(UPBOAT, upBoatSprites, 10);
			} else if (!onWater && currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}
		// update position
		super.update();

	}

	private void updateExp() {
		if (Exp >= ExpCap) {
			Exp = Exp - ExpCap;
			Level++;
			ExpCap += 2;
			power += 2;
		}
	}

	// Draw Player.
	public void draw(Graphics2D g) {
		if (damaged) {
			g.setColor(Color.red);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);
		}
		super.draw(g);
	}

	public static int getHp() {
		return Hp;
	}

	public void setHp(int hp) {
		Hp = hp;
	}

	public static int getLevel() {
		return Level;
	}

	public static int getExp() {
		return Exp;
	}

	public int getNumAxe() {
		return axeNum;
	}

	public Rectangle getAttackBox() {
		return attackBox;
	}

	public boolean isAttack() {
		return attack;
	}

	public static void setExp(int exp) {
		Exp = exp;
	}

	public static void setLevel(int lvl) {
		Level = lvl;
	}

	public void setDied(boolean d) {
		died = d;
	}

	public boolean hasDied() {
		return died;
	}

	public static int getExpCap() {
		return ExpCap;
	}

	public static int getPower() {
		return power;
	}



	public void setAttack(boolean b) {
		attack = b;
	}

	public void setDamaged(boolean d) {
		damaged = d;
	}
}