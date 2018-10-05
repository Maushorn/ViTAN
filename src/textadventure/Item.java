package textadventure;

public class Item {
	
	private String name;
	private String description;
	
	public Item(String name) {
		super();
		this.name = name;
		description = "";
	}

	public Item(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	/**Copy-Constructor
	 * 
	 * @param original
	 */
	public Item(Item original) {
		this.name = original.getName();
		this.description = original.getDescription();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
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
		return "Item [name=" + name + ", description=" + description + "]";
	}
	
	
	
}
