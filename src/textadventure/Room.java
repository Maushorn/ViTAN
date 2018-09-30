package textadventure;

public class Room {

	private String name;
	private String description;
	private Boolean empty;
	private int xCoord;
	private int yCoord;
	
	public Room(String name) {
		super();
		this.name = name;
		this.description = "<leer>";
		this.empty = false;
//		this.xCoord = xCoord;
//		this.yCoord = yCoord;
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
		return name;
	}

}
