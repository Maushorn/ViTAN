package textadventure;

public class InteractiveObject {

	private String name;
	private String description;
	private String unlockItem;
	
	public InteractiveObject(String name) {
		super();
		this.name = name;
	}
	
	public String getUnlockItem() {
		return unlockItem;
	}

	public void setUnlockItem(String unlockItem) {
		this.unlockItem = unlockItem;
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
	
}
