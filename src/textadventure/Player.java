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
	
	public Room getPosition() {
		return position;
	}
	
	//TODO: map.getRoom returns null if the move exceeds the defined map.
	public int moveUp() {
		if((this.getCoord().getY() <= 0))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()-1);
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
		}else return ReactionMessage.INVALID_MOVE;
	}
	
	public int moveRight() {
		if((this.getCoord().getX() >= map.getMap().size()))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX()+1, getCoord().getY());
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
			}else return ReactionMessage.INVALID_MOVE;
	}
	
	public int moveDown() {
		if((this.getCoord().getY() >= map.getMap().get(0).size()))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()+1);
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
			}else return ReactionMessage.INVALID_MOVE;
	}

	public int moveLeft() {
		if(this.getCoord().getX() <= 0)
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX()-1, getCoord().getY());
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
			}else return ReactionMessage.INVALID_MOVE;
	}
	
	public Boolean useItem(Item item, InteractiveObject iObject) {
		return item.getName().equals(iObject.getKeyItem());
	}
	
	public Coord getCoord() {
		return map.getCoordinates(position);
	}
	
}
