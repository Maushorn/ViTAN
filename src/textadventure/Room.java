package textadventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Room implements Serializable{

	static final long serialVersionUID = 555184l;
	
	private String name;
	private String description;
	private Boolean empty;
	private HashSet<Item> items;
	private HashSet<InteractiveObject> interactiveObjects;
	private Boolean startRoom;
	
	public Room(String name) {
		super();
		this.name = name;
		this.description = "<leer>";
		this.empty = false;
		this.items = new HashSet<>();
		this.interactiveObjects = new HashSet<>();
		this.startRoom = false;
		//TODO: Do I need these?
//		this.xCoord = xCoord;
//		this.yCoord = yCoord;
	}
	
	public Room(String name, Boolean startRoom) {
		super();
		this.name = name;
		this.description = "<leer>";
		this.empty = false;
		this.items = new HashSet<>();
		this.interactiveObjects = new HashSet<>();
		this.startRoom = startRoom;
	}
	
	public Room() {
		super();
		this.name = "<leer>";
		this.description = "<leer>";
		this.empty = true;
		this.items = new HashSet<>();
		this.interactiveObjects = new HashSet<>();
		this.startRoom = false;
	}
	
	/**Copy-Constructor.
	 * 
	 * Attention: This does not copy the original's Item-Objects!
	 * 
	 * @param original
	 */
	public Room(Room original) {
		super();
		name = original.getName();
		description = original.getDescription();
		empty = original.isEmpty();
		items = new HashSet<>();
		if(original.getItems() != null && !original.getItems().isEmpty())
			for(Item item : original.getItems())
				items.add(item);
		interactiveObjects = new HashSet<>();
		if(original.getInteractiveObjects() != null && !original.getInteractiveObjects().isEmpty())
		for(InteractiveObject iObj : original.getInteractiveObjects())
			interactiveObjects.add(iObj);
		this.startRoom = original.isStartRoom();

	}
	
	
	
	public Boolean isStartRoom() {
		return startRoom;
	}

	public void setStartRoom(Boolean startroom) {
		this.startRoom = startroom;
	}

	public Boolean isEmpty() {
		return empty;
	}
	
	public void setEmpty(Boolean empty) {
		this.empty = empty;
	}

	public HashSet<Item> getItems() {
		return items;
	}

	public void setItems(HashSet<Item> items) {
		this.items = items;
	}
	
	public Item getItemWithName(String name) {
		if(items != null && !items.isEmpty())
			for(Item item : items)
				if(item.getName().equals(name))
					return item;
		return null;
	}
	
	public HashSet<String> getItemNames(){
		HashSet<String> itemNames = new HashSet<>();
		for(Item item : items)
			itemNames.add(item.getName());
		return itemNames;
	}
	
	public HashSet<String> getIObjectNames(){
		HashSet<String> iObjectNames = new HashSet<>();
		for(InteractiveObject iObj : interactiveObjects)
			iObjectNames.add(iObj.getName());
		return iObjectNames;
	}

	public HashSet<InteractiveObject> getInteractiveObjects() {
		return interactiveObjects;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void clear() {
		name = "<leer>";
		description = "<leer>";
		empty = true;
		items = new HashSet<>();
		interactiveObjects = new HashSet<>();
		startRoom = false;
	}
	
	@Override
	public String toString() {
		return "Room [name=" + name + ", description=" + description + ", empty=" + empty + "]";
	}
	
	

}
