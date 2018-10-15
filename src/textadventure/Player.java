package textadventure;

import java.util.HashSet;

public class Player {
	
	private AdventureMap map;
	private Room position;
	private HashSet<Item> items;
		
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
	
	public HashSet<Item> getItems() {
		return items;
	}
	
	public Item getSpecificItem(String itemName) {
		for(Item i : items)
			if(i.getName().equalsIgnoreCase(itemName))
				return i;
		return null;
	}
	
	public InteractiveObject getSpecificInteractiveObject(String objectName) {
		for(InteractiveObject iObj : position.getInteractiveObjects())
			if(iObj.getName().equalsIgnoreCase(objectName))
				return iObj;
		return null;
	}
	
	public Room getPosition() {
		return position;
	}
	
	//TODO: map.getRoom returns null if the move exceeds the defined map.
	public String moveUp() {
		if((this.getCoord().getY() <= 0))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()-1);
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
		}else return ReactionMessage.INVALID_MOVE;
	}
	
	public String moveRight() {
		if((this.getCoord().getX() >= map.getMap().size()))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX()+1, getCoord().getY());
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
			}else return ReactionMessage.INVALID_MOVE;
	}
	
	public String moveDown() {
		if((this.getCoord().getY() >= map.getMap().get(0).size()))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()+1);
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
			}else return ReactionMessage.INVALID_MOVE;
	}

	public String moveLeft() {
		if(this.getCoord().getX() <= 0)
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX()-1, getCoord().getY());
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return ReactionMessage.VALID_MOVE;
			}else return ReactionMessage.INVALID_MOVE;
	}
	
	public String useItem(Item item, InteractiveObject iObject) {
		if(item == null)
			return ReactionMessage.ITEM_MISSING;
		if(iObject == null)
			return ReactionMessage.INTERACTIVE_OBJECT_MISSING;
		if(iObject.getReward() == null)
			return ReactionMessage.NO_REWARD;
		if(item.getName().equals(iObject.getKeyItem()))
			return ReactionMessage.SUCCESS;
		else return ReactionMessage.ITEM_MISMATCH;
	}
	
	public String getInventoryString() {
		StringBuilder sb = new StringBuilder();
		if(!items.isEmpty()){
			sb.append("Items:");
			for(Item i : items)
				sb.append("\n" + i.getName());
			return sb.toString();
		}else return ReactionMessage.INVENTORY_EMPTY;
	}
	
	public String inspect(Item item) {
		return item.getDescription();
	}
	
	public String takeSpecificItem(String itemName) {
		if(!position.getItems().isEmpty())
		for(Item i : position.getItems())
			if(i.getName().equalsIgnoreCase(itemName)) {
				items.add(i);
				position.getItems().remove(i);
				return ReactionMessage.ITEM_TAKEN + itemName;
			}
		return ReactionMessage.ITEM_MISSING;
	}
	
	public String inspect(InteractiveObject iObject) {
		return iObject.getDescription();
	}
	
	public Coord getCoord() {
		return map.getCoordinates(position);
	}
	
}
