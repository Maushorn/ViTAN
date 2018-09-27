package textadventure;

public class Room {

	private String name;
	private String description;
	private Boolean empty;
	
	public Room(String name) {
		super();
		this.name = name;
		this.empty = false;
	}
	
	public Room() {
		super();
		this.name = "<leer>";
		this.empty = true;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name;
	}

}
