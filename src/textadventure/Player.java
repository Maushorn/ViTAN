package textadventure;

import java.util.HashSet;

public class Player {
	
	private AdventureMap map;
	private Room position;
	private HashSet<Item> items;


	public Player(AdventureMap map) {
		this.map = map;
		items = new HashSet<>();
		position = map.getStart();
	}
	
	/**Should only be used for setting the starting position of the player at the beginning of a adventure.
	 * 
	 * @param room
	 */
	public void setPosition(Room room) {
		position = room;
	}
	
	public HashSet<Item> getItems() {
		return items;
	}
	
	public void setItems(HashSet<Item> items) {
		this.items = items;
	}

	/**Returns an item identified by it's name.
	 *
	 * @param itemName
	 * @return
	 */
	public Item getSpecificItem(String itemName) {
		for(Item i : items)
			if(i.getName().equalsIgnoreCase(itemName))
				return i;
		return null;
	}

	/**Retreves a interactive object from the current location of the player.
	 *
	 * @param objectName
	 * @return
	 */
	public InteractiveObject getSpecificInteractiveObject(String objectName) {
		for(InteractiveObject iObj : position.getInteractiveObjects())
			if(iObj.getName().equalsIgnoreCase(objectName))
				return iObj;
		return null;
	}
	
	public Room getPosition() {
		return position;
	}

	/**Moves the player on the grid of an AdventureMap.
	 *
	 * @return
	 */
	public String moveUp() {
		if((this.getCoord().getY() <= 0))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()-1);
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return "Du betrittst: " + destination.getName();
		}else return ReactionMessage.INVALID_MOVE;
	}

	/**Moves the player on the grid of an AdventureMap.
	 *
	 * @return
	 */
	public String moveRight() {
		if((this.getCoord().getX() >= map.getMap().size()))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX()+1, getCoord().getY());
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return "Du betrittst: " + destination.getName();
			}else return ReactionMessage.INVALID_MOVE;
	}

	/**Moves the player on the grid of an AdventureMap.
	 *
	 * @return
	 */
	public String moveDown() {
		if((this.getCoord().getY() >= map.getMap().get(0).size()))
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX(), getCoord().getY()+1);
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return "Du betrittst: " + destination.getName();
			}else return ReactionMessage.INVALID_MOVE;
	}

	/**Moves the player on the grid of an AdventureMap.
	 *
	 * @return
	 */
	public String moveLeft() {
		if(this.getCoord().getX() <= 0)
			return ReactionMessage.INVALID_MOVE;
		Room destination = map.getRoomAt(getCoord().getX()-1, getCoord().getY());
		if(destination!=null && !destination.getName().equals("<leer>")) {
			position = destination;
			return "Du betrittst: " + destination.getName();
			}else return ReactionMessage.INVALID_MOVE;
	}

	/**If a player combines a InteractiveObject with it's correct key item,
	 * this method adds a reward item to the players inventory.
	 *
	 * @param item
	 * @param iObject
	 * @return
	 */
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

	/**Lists all Items in the players inventory in a String representation.
	 *
	 * @return
	 */
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

	/**Adds a item to the players inventory.
	 *
	 * @param itemName
	 * @return
	 */
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
