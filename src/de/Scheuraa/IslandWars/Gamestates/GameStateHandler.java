package de.Scheuraa.IslandWars.Gamestates;

import java.util.ArrayList;

public class GameStateHandler {
	
	private static GameState current;
	private static ArrayList<GameState> states = new ArrayList<>();
	
	public GameStateHandler(){
		states.add(new LobbyState());
		states.add(new IngameState());
		states.add(new EndState());
	}
	
	public static void setGameState(int id){
		if(current !=null){
			current.end();
		}
			current = states.get(id);
			current.init();
	}

	public static GameState getCurrentState() {
		
		return current;
	}

}
