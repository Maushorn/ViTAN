package textadventure;

import java.util.HashSet;

public class Player {
	
	private Room location;
	private HashSet<String> items;
	
	public Player() {
		items = new HashSet<>();
	}
	
	public HashSet<String> getItems() {
		return items;
	}
	
	public Room getLocation() {
		return location;
	}
	
	public void moveUp() {
		
	}
	
	public void moveRight() {
		
	}
	
	public void moveDown() {
		
	}

	public void moveLeft() {
	
	}
	
}
