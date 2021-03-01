// Simple class that plays animation
// once and is removed.

package Entity;

import java.awt.Graphics2D;

import Manager.Content;
import TileMap.TileMap;

public class Sparkle extends Entity {
	
	private boolean removed;
	
	public Sparkle(TileMap tm) {
		super(tm);
		animation.setFrames(Content.SPARKLE[0]);
		animation.setDelay(5);
		width = height = 16;
	}
	
	public boolean shouldRemove() {
		return removed;
	}
	
	public void update() {
		animation.update();
		if(animation.hasPlayedOnce()) removed = true;
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
