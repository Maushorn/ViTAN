package textadventure;

import java.util.ArrayList;

public class Room {

	private String name;
	private String description;
	private Boolean empty;
	private ArrayList<String> items;
	private ArrayList<InteractiveObject> interactiveObjects;
	
	//TODO: Necessary?
	private int xCoord;
	private int yCoord;
	
	public Room(String name) {
		super();
		this.name = name;
		this.description = "<leer>";
		this.empty = false;
		this.items = new ArrayList<>();
		this.interactiveObjects = new ArrayList<>();
		//TODO: Do I need these?
//		this.xCoord = xCoord;
//		this.yCoord = yCoord;
	}
	
	public Room() {
		super();
		this.name = "<leer>";
		this.description = "<leer>";
		this.empty = true;
	}
	
	/**Copy-Constructor
	 * 
	 * @param original
	 */
	public Room(Room original) {
		super();
		name = original.getName();
		description = original.getDescription();
		empty = original.isEmpty();
		items = new ArrayList<>();
		if(!original.getItems().isEmpty())
		for(String s : original.getItems())
			items.add(s);
		interactiveObjects = new ArrayList<>();
		for(InteractiveObject iObj : original.getInteractiveObjects())
			interactiveObjects.add(iObj);
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

	public int getXCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getYCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
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

	@Override
	public String toString() {
		return "Room [name=" + name + ", description=" + description + ", empty=" + empty + "]";
	}
	
	

}
