package textadventure;

public class Item {
	
	private String name;
	private String description;
	private InteractiveObject interactiveObject;
	
	public Item(String name) {
		super();
		this.name = name;
		description = "";
		interactiveObject = null;
	}

	/**Copy-Constructor
	 * 
	 * @param original
	 */
	public Item(Item original) {
		
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

	public InteractiveObject getInteractiveObject() {
		return interactiveObject;
	}

	public void setInteractiveObject(InteractiveObject interactiveObject) {
		this.interactiveObject = interactiveObject;
	}

	
	
}
