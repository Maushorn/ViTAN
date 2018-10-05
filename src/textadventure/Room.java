package textadventure;

import java.util.ArrayList;

public class Room {

	private String name;
	private String description;
	private Boolean empty;
	private ArrayList<Item> items;
	private ArrayList<InteractiveObject> interactiveObjects;
	private Boolean startRoom;
	
	public Room(String name) {
		super();
		this.name = name;
		this.description = "<leer>";
		this.empty = false;
		this.items = new ArrayList<>();
		this.interactiveObjects = new ArrayList<>();
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
		this.items = new ArrayList<>();
		this.interactiveObjects = new ArrayList<>();
		this.startRoom = startRoom;
	}
	
	public Room() {
		super();
		this.name = "<leer>";
		this.description = "<leer>";
		this.empty = true;
		this.items = new ArrayList<>();
		this.interactiveObjects = new ArrayList<>();
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
		items = new ArrayList<>();
		if(original.getItems() != null && !original.getItems().isEmpty())
		for(String s : original.getItems())
			items.add(s);
		interactiveObjects = new ArrayList<>();
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

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}

	public ArrayList<InteractiveObject> getInteractiveObjects() {
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
		String name = "<leer>";
		String description = "<leer>";
		Boolean empty = true;
		ArrayList<String> items = new ArrayList<>();
		ArrayList<InteractiveObject> interactiveObjects = new ArrayList<>();
//		Boolean startRoom;
	}
	
	@Override
	public String toString() {
		return "Room [name=" + name + ", description=" + description + ", empty=" + empty + "]";
	}
	
	

}
