// The GameStateManager does exactly what its
// name says. It contains a list of GameStates.
// It decides which GameState to update() and
// draw() and handles switching between different
// GameStates.

package com.neet.DiamondHunter.Manager;

import java.awt.Graphics2D;


import com.neet.DiamondHunter.GameState.GameState;
import com.neet.DiamondHunter.GameState.HelpState;
import com.neet.DiamondHunter.GameState.IntroState;
import com.neet.DiamondHunter.GameState.LostState;
import com.neet.DiamondHunter.GameState.MenuState;
import com.neet.DiamondHunter.GameState.PauseState;
import com.neet.DiamondHunter.GameState.PlayState;
import com.neet.DiamondHunter.GameState.StatsState;
import com.neet.DiamondHunter.GameState.TopState;
import com.neet.DiamondHunter.GameState.WinState;


public class GameStateManager {
	
	private boolean paused;
	private PauseState pauseState;
	
	private boolean stats;
	private StatsState statsState;

	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 7;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int HELP = 3;
	public static final int TOP = 4;
	public static final int WIN = 5;
	public static final int LOST = 6;

	
	
	
	public GameStateManager() {
		

		
		paused = false;
		pauseState = new PauseState(this);
		
		stats = false;
		statsState = new StatsState(this);
		
		gameStates = new GameState[NUM_STATES];
		setState(INTRO);
		
	}
	
	public void setState(int i) {
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		if(i == INTRO) {
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		else if(i == MENU) {
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		else if(i == PLAY) {
			gameStates[i] = new PlayState(this);
			gameStates[i].init();
		}
		else if(i == HELP) {
			gameStates[i] = new HelpState(this);
			gameStates[i].init();
		}
		else if(i == TOP) {
			gameStates[i] = new TopState(this);
			gameStates[i].init();
		}
		else if(i == WIN) {
			gameStates[i] = new WinState(this);
			gameStates[i].init();
		}
		else if(i == LOST) {
			gameStates[i] = new LostState(this);
			gameStates[i].init();
		}
	}
	
	public void unloadState(int i) {
		gameStates[i] = null;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	public void setStats(boolean b) {
		stats = b;
	}
	
	public void update() {
		if(paused) {
			pauseState.init();
			pauseState.update();
		}
		else if(stats) {
			statsState.init();
			statsState.update();
			
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
	}
	
	public void draw(Graphics2D g) {
		if(paused) {
			pauseState.draw(g);
		}
		else if(stats) {
			statsState.draw(g);
		}
		else if(gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		}
	}
	
	
}
