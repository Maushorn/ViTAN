package textadventure;

public class Room {

	private String name;
	private String description;
	
	
	public Room(String name) {
		super();
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Room [name=" + name + ", description=" + description + "]";
	}

}
