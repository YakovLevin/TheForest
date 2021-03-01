// The main playing GameState.
// Contains everything you need for gameplay:
// Player, TileMap, Diamonds, etc.
// Updates and draws all game objects.

package com.neet.DiamondHunter.GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.neet.DiamondHunter.Entity.HPackage;
import com.neet.DiamondHunter.Entity.Item;
import com.neet.DiamondHunter.Entity.Monster;
import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Entity.Sparkle;
import com.neet.DiamondHunter.HUD.Hud;
import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.Manager.Data;
import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.Keys;
import com.neet.DiamondHunter.TileMap.TileMap;

public class PlayState extends GameState {

	// player
	private Player player;

	// tilemap
	private TileMap tileMap;

	// diamonds
	private ArrayList<HPackage> hps;

	// items
	private ArrayList<Item> items;

	private ArrayList<Monster> monsters;

	// sparkles
	private ArrayList<Sparkle> sparkles;

	// camera position
	private int xsector;
	private int ysector;
	private int sectorSize;

	// hud
	private Hud hud;

	// events
	private boolean eventStart;
	private boolean eventFinish = false;
	private boolean eventDied = false;
	private boolean Mdead;

	private int eventTick;

	// transition box
	private ArrayList<Rectangle> boxes;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

		// create lists
		hps = new ArrayList<HPackage>();
		sparkles = new ArrayList<Sparkle>();
		items = new ArrayList<Item>();
		monsters = new ArrayList<Monster>();

		// load map
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/testtileset.gif");
		tileMap.loadMap("/Maps/e3.map");

		// create player
		player = new Player(tileMap);



		// initialize player
		player.setTilePosition(4, 4);

		// set up camera position
		sectorSize = GamePanel.WIDTH;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);

		// load hud
		hud = new Hud(player, hps);

		// fill lists
		populateHPackages();
		populateItems();
		populateMonsters();
		
		// start event
		boxes = new ArrayList<Rectangle>();
		eventStart = true;
		

	}

	private void populateHPackages() {

		HPackage h;

		h = new HPackage(tileMap);
		h.setTilePosition(7, 3);

		h = new HPackage(tileMap);
		h.setTilePosition(34, 7);
		hps.add(h);

		h = new HPackage(tileMap);
		h.setTilePosition(22, 29);
		hps.add(h);

		h = new HPackage(tileMap);
		h.setTilePosition(2, 14);
		hps.add(h);
		h = new HPackage(tileMap);
		h.setTilePosition(11, 21);
		hps.add(h);
		h = new HPackage(tileMap);
		h.setTilePosition(31, 20);
		hps.add(h);

		h = new HPackage(tileMap);
		h.setTilePosition(1, 30);
		hps.add(h);
		h = new HPackage(tileMap);
		h.setTilePosition(22, 9);
		hps.add(h);
		h = new HPackage(tileMap);
		h.setTilePosition(2, 35);
		hps.add(h);

	}

	private void populateItems() {
		Item item;

		item = new Item(tileMap);
		item.setType(Item.AXE_3);
		item.setTilePosition(18, 15);// 18 15
		item.addChange(new int[] { 15, 10, 1 });
		item.addChange(new int[] { 16, 10, 1 });
		item.addChange(new int[] { 15, 11, 1 });
		item.addChange(new int[] { 16, 11, 1 });
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.BOAT);
		item.setTilePosition(27, 18);// 27 18
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.AXE_2);
		item.setTilePosition(37, 30);// 37 30
		item.addChange(new int[] { 32, 34, 1 });
		item.addChange(new int[] { 32, 33, 1 });
		items.add(item);

		item = new Item(tileMap);
		item.setType(Item.AXE_1);
		item.setTilePosition(12, 4);// 12 4
		items.add(item);

	}

	private void populateMonsters() {

		Monster m;

		m = new Monster(tileMap);
		m.setDirection(true);
		m.setTilePosition(20, 6);
		monsters.add(m);

		m = new Monster(tileMap);
		m.setDirection(false);
		m.setTilePosition(26, 4);
		monsters.add(m);

		m = new Monster(tileMap);
		m.setDirection(false);
		m.setTilePosition(32, 17);// 32 17
		monsters.add(m);

		m = new Monster(tileMap);
		m.setDirection(false);
		m.setTilePosition(29, 34);
		monsters.add(m);
		m = new Monster(tileMap);
		m.setDirection(false);
		m.setTilePosition(10, 14);
		monsters.add(m);
		m = new Monster(tileMap);
		m.setDirection(true);
		m.setTilePosition(2, 22);
		monsters.add(m);
		m = new Monster(tileMap);
		m.setDirection(false);
		m.setTilePosition(18, 25);
		monsters.add(m);
		m = new Monster(tileMap);
		m.setDirection(false);
		m.setTilePosition(19, 12);
		monsters.add(m);
		m = new Monster(tileMap);
		m.setDirection(false);
		m.setTilePosition(20, 9);
		monsters.add(m);
		m = new Monster(tileMap);
		m.setDirection(true);
		m.setTilePosition(20, 28);
		monsters.add(m);
		m = new Monster(tileMap);
		m.setDirection(true);
		m.setTilePosition(19, 30);
		monsters.add(m);

	}

	public void update() {

		// check keys
		handleInput();

		if (player.getHp() == 0)
			eventDied = true;

		// check events
		if (eventStart)
			eventStart();
		
		if (eventFinish)
			eventFinish();
		if (eventDied)
			eventDied();



		// update camera
		int oldxs = xsector;
		int oldys = ysector;
		xsector = player.getx() / sectorSize;
		ysector = player.gety() / sectorSize;
		tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
		tileMap.update();

		if (tileMap.isMoving())
			return;

		// update player
		player.update();

		// update diamonds
		for (int i = 0; i < hps.size(); i++) {

			HPackage h = hps.get(i);
			h.update();

			// player collects diamond
			if (player.intersects(h)) {

				// remove from list
				hps.remove(i);
				i--;

				player.collectedHP();
				
				// add new sparkle
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(h.getx(), h.gety());
				sparkles.add(s);

			}
		}

		// update sparkles
		for (int i = 0; i < sparkles.size(); i++) {
			Sparkle s = sparkles.get(i);
			s.update();
			if (s.shouldRemove()) {
				sparkles.remove(i);
				i--;
			}
		}

		// update items
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (player.intersects(item)) {
				item.setType(i);
				item.collected(player);

				items.remove(i);

				Sparkle s = new Sparkle(tileMap);
				s.setPosition(item.getx(), item.gety());
				sparkles.add(s);

				ArrayList<int[]> alitem = item.getChanges();
				for (int[] j : alitem) {
					tileMap.setTile(j[0], j[1], j[2]);
				}

			}
		}
		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			monster.update();
			Mdead = false;
			// monster attack player
			if (player.intersects(monster) && player.getHp() > 0 && monster.getHp() > 0) {
				if (!monster.hasDecreasing()) {
					player.setHp(player.getHp() - 5);
					monster.setDecreasing(true);
					player.setDamaged(true);
					break;
				}
			} else {
				monster.setDecreasing(false);
				player.setDamaged(false);

			}
			// player attacks monster
			if (player.getAttackBox().intersects(monster.getMonsterArea()) && (player.isAttack()) && player.hasAxe()
					&& monster.getHp() > 0) {
				monster.setHp(monster.getHp() - player.getPower());
				if (monster.getHp() <= 0) {
					Mdead = true;
					player.setExp(player.getExp() + 2);
				}
				monster.setDemeged(true);
				break;
			} else
				monster.setDemeged(false);
			if (Mdead) {
				monsters.remove(i);
				monster = null;
			}
		}

	}

	public void draw(Graphics2D g) {

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);

		// draw diamonds
		for (HPackage h : hps) {
			h.draw(g);
		}

		// draw sparkles
		for (Sparkle s : sparkles) {
			s.draw(g);
		}

		// draw items
		for (Item i : items) {
			i.draw(g);
		}
		for (Monster m : monsters) {
			m.draw(g);
		}
		// draw hud
		hud.draw(g);

		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for (int i = 0; i < boxes.size(); i++) {
			g.fill(boxes.get(i));
		}

	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setPaused(true);
		}
		if (Keys.isPressed(Keys.E)) {
			gsm.setStats(true);
		}

		if (Keys.isDown(Keys.LEFT))
			player.setLeft();
		if (Keys.isDown(Keys.RIGHT)) {
			if (tileMap.isFinished()) {
				eventFinish = true;
				eventFinish();
			} else
				player.setRight();
		}
		if (Keys.isDown(Keys.UP))
			player.setUp();
		if (Keys.isDown(Keys.DOWN))
			player.setDown();
		if (Keys.isPressed(Keys.SPACE)) {
			player.setAction();
			player.setAttack(true);
		} else
			player.setAttack(false);

	}

	// ===============================================

	private void eventStart() {
		eventTick++;
		if (eventTick == 1) {
			boxes.clear();
			for (int i = 0; i < 9; i++) {
				boxes.add(new Rectangle(0, i * 16, GamePanel.WIDTH, 16));
			}
		}
		if (eventTick > 1 && eventTick < 32) {
			for (int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if (i % 2 == 0) {
					r.x -= 4;
				} else {
					r.x += 4;
				}
			}
		}
		if (eventTick == 33) {
			boxes.clear();
			eventStart = false;
			eventTick = 0;
		}
	}

	private void eventFinish() {
		eventTick++;
		if (eventTick == 1) {
			boxes.clear();
			for (int i = 0; i < 9; i++) {
				if (i % 2 == 0)
					boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
				else
					boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
			}

		}
		if (eventTick > 1) {
			for (int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if (i % 2 == 0) {
					if (r.x < 0)
						r.x += 4;
				} else {
					if (r.x > 0)
						r.x -= 4;
				}
			}
		}

		if (eventTick > 33) {
			if (tileMap.isFinished()) {
				Data.setTime(player.getTicks());
				gsm.setState(GameStateManager.WIN);
			}
		}
	}

	private void eventDied() {
		eventTick++;
		if (eventTick == 1) {
			boxes.clear();
			for (int i = 0; i < 9; i++) {
				if (i % 2 == 0)
					boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
				else
					boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
			}
			player.setDied(true);
		}
		if (eventTick > 1) {
			for (int i = 0; i < boxes.size(); i++) {
				Rectangle r = boxes.get(i);
				if (i % 2 == 0) {
					if (r.x < 0)
						r.x += 4;
				} else {
					if (r.x > 0)
						r.x -= 4;
				}
			}
		}

		if (eventTick > 33) {
			if (player.hasDied()) {
				Data.setTime(player.getTicks());
				gsm.setState(GameStateManager.LOST);
			}
		}
	}

}
