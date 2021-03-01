// Possibly redundant subclass of Entity.
// There are two types of items: Axe and boat.
// Upon collection, informs the Player
// that the Player does indeed have the item.

package com.neet.DiamondHunter.Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.TileMap.TileMap;

public class Monster extends Entity {

	private BufferedImage[] downSpriteM;
	private BufferedImage[] leftSpriteM;
	private BufferedImage[] rightSpriteM;
	private BufferedImage[] upSpriteM;

	private BufferedImage[] downSpriteMD;
	private BufferedImage[] leftSpriteMD;
	private BufferedImage[] rightSpriteMD;
	private BufferedImage[] upSpriteMD;

	// animation
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;

	private int Hp;

	private static Rectangle monsterArea;


	private boolean decreasing;
	private boolean demeged;

	public Monster(TileMap tm) {
		super(tm);
		width = height = 16;
		cwidth = cheight = 12;

		moveSpeed = 1;

		Hp = 30;// 1 - 5 ; 2 - 3 ; 3 - 1
		monsterArea = new Rectangle();

		upSpriteM = Content.MONSTER[0];
		downSpriteM = Content.MONSTER[1];
		leftSpriteM = Content.MONSTER[2];
		rightSpriteM = Content.MONSTER[3];

		upSpriteMD = Content.MONSTER[4];
		downSpriteMD = Content.MONSTER[5];
		leftSpriteMD = Content.MONSTER[6];
		rightSpriteMD = Content.MONSTER[7];

		animation.setFrames(downSpriteM);
		animation.setDelay(10);

	}

	private void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}

	public void setDirection(boolean b) {
		if (b)
			setAnimation(DOWN, downSpriteM, 10);
		else
			setAnimation(RIGHT, rightSpriteM, 10);

	}

	public void update() {
		if (currentAnimation == RIGHT) {
			if (!demeged) {
				setAnimation(RIGHT, rightSpriteM, 10);
				super.setRight();
			} else {
				setAnimation(RIGHT, rightSpriteMD, 10);
				super.setRight();

			}
			if (!moving) {
				setAnimation(LEFT, leftSpriteM, 10);
			}

		} else if (currentAnimation == LEFT) {
			if (!demeged) {
				setAnimation(LEFT, leftSpriteM, 10);
				super.setLeft();
			} else {
				setAnimation(LEFT, leftSpriteMD, 10);
				super.setLeft();
			}
			if (!moving)
				setAnimation(RIGHT, rightSpriteM, 10);

		}

		if (currentAnimation == DOWN) {
			if (!demeged) {
				setAnimation(DOWN, downSpriteM, 10);
				super.setDown();

			} else {
				setAnimation(DOWN, downSpriteMD, 10);
				super.setDown();

			}
			if (!moving) {
				setAnimation(UP, upSpriteM, 10);
			}
		} else if (currentAnimation == UP) {
			if (!demeged) {
				setAnimation(UP, upSpriteM, 10);
				super.setUp();
			} else {
				setAnimation(UP, upSpriteMD, 10);
				super.setUp();
			}
			if (!moving) {
				setAnimation(DOWN, downSpriteM, 10);
			}

		}

		if (Hp > 0)
			monsterArea.setBounds((x + xmap - width / 2), (y + ymap - height / 2), 16, 16);


		super.update();

	}

	public void draw(Graphics2D g) {
		if (Hp > 0) {
			g.setColor(Color.BLACK);

			g.drawRect((x + xmap - width / 2), (y + ymap - height / 2) - 6, 14, 3);
			g.setColor(Color.WHITE);
			g.fillRect((x + xmap - width / 2) + 1, (y + ymap - height / 2) - 5, (int) (14.0 * Hp / 30) - 1, 2);
			g.setColor(Color.BLACK);
			super.draw(g);
		}
	

	}

	public boolean hasDecreasing() {
		return decreasing;
	}

	public void setDecreasing(boolean b) {
		decreasing = b;
	}

	public int getHp() {
		return Hp;
	}

	public void setHp(int hp) {
		Hp = hp;
	}

	public void setDemeged(boolean b) {
		demeged = b;
	}

	public static Rectangle getMonsterArea() {
		return monsterArea;
	}

}
