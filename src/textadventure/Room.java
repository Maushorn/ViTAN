package textadventure;

import java.util.ArrayList;

public class Room {

	private String name;
	private String description;
	private Boolean empty;
	private ArrayList<Item> items;
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
	
	/**Copy-Constructor
	 * 
	 * @param original
	 */
	public Room(Room original) {
		super();
		this.name = original.getName();
		this.description = original.getDescription();
		this.empty = original.isEmpty();
	}
	
	public Boolean isEmpty() {
		return empty;
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

	public Room() {
		super();
		this.name = "<leer>";
		this.description = "<leer>";
		this.empty = true;
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
