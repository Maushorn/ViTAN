package textadventure;

import java.util.HashSet;

public class Player {
	
	private AdventureMap map;
	private Room position;
	private HashSet<String> items;
		
	public Player(AdventureMap map) {
		this.map = map;
		items = new HashSet<>();
	}
	
	/**Should only be used for setting the start.
	 * 
	 * @param start
	 */
	public void setPosition(Room start) {
		position = start;
	}
	
	public HashSet<String> getItems() {
		return items;
	}
	
	public Room getPsoition() {
		return position;
	}
	
	//TODO: map.getRoom returns null if the move exceeds the map.
	public Boolean moveUp() {
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()-1);
		if(destination!=null) {
			position = destination;
			return true;
		}else return false;
	}
	
	public Boolean moveRight() {
		Room destination = map.getRoomAt(getCoord().getX()+1, getCoord().getY());
		if(destination!=null) {
			position = destination;
			return true;
		}else return false;
	}
	
	public Boolean moveDown() {
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()+1);
		if(destination!=null) {
			position = destination;
			return true;
		}else return false;
	}

	public Boolean moveLeft() {
		Room destination = map.getRoomAt(getCoord().getX()-1, getCoord().getY());
		if(destination!=null) {
			position = destination;
			return true;
		}else return false;
	}
	
	public Boolean useItem(Item item, InteractiveObject iObject) {
		return item.getName().equals(iObject.getKeyItem());
	}
	
	public Coord getCoord() {
		return map.getCoordinates(position);
	}
	
}
