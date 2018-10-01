package textadventure;

public class InteractiveObject {

	private String name;
	private String description;
	private String keyItem;
	private String reward;
	
	public InteractiveObject(String name) {
		super();
		this.name = name;
	}
	
	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}
	
	public String getUnlockItem() {
		return keyItem;
	}

	public void setUnlockItem(String keyItem) {
		this.keyItem = keyItem;
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
