// Possibly redundant subclass of Entity.
// There are two types of items: Axe and boat.
// Upon collection, informs the Player
// that the Player does indeed have the item.

package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Manager.Content;
import TileMap.TileMap;

public class Item extends Entity {

	private BufferedImage sprite;
	private static int type;
	public static final int BOAT = 1;
	public static final int AXE_1 = 3;
	public static final int AXE_2 = 2;
	public static final int AXE_3 = 0;
	private ArrayList<int[]> tileChanges;


	
	public Item(TileMap tm) {
		super(tm);
		type = -1;
		width = height = 16;
		cwidth = cheight = 12;
		//contain all changes to the map
		tileChanges = new ArrayList<int[]>();

		
	}

	public void setType(int i) {
		type = i;
		if (type == BOAT) {
			sprite = Content.ITEMS[1][0];
		} else if (type == AXE_1) {
			sprite = Content.ITEMS[1][1];
		} else if (type == AXE_2) {
			sprite = Content.ITEMS[1][2];
		} else if (type == AXE_3) {
			sprite = Content.ITEMS[1][3];
		}
	}



	public void collected(Player p) {
		if (type == BOAT) {
			p.gotBoat();
		}
		if (type == AXE_1 || type == AXE_2 || type == AXE_3) {
			p.gotAxe();
		}

	}
	public void addChange(int[] i) {
		tileChanges.add(i);
	}
	public ArrayList<int[]> getChanges() {
		return tileChanges;
	}
	

	public void draw(Graphics2D g) {
		setMapPosition();
		g.drawImage(sprite, x + xmap - width / 2, y + ymap - height / 2, null);
	}

	public static int getType() {
		return type;
	}

}
