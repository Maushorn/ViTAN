package textadventure;

import java.io.Serializable;

public class InteractiveObject implements Serializable{

	static final long serialVersionUID = 555186l;
	
	private String name;
	private String description;
	private String keyItem;
	private Item reward;
	
	public InteractiveObject(String name) {
		super();
		this.name = name;
		this.reward = null;
	}
	
	/**Copy-Contructor
	 * 
	 * @param original
	 */
	public InteractiveObject(InteractiveObject original) {
		super();
		name = original.getName();
		description = original.getDescription();
		keyItem = original.getKeyItem();
		reward = new Item(original.getReward());
	}
	
	public void removeReward() {
		reward = null;
	}
	
	public Item getReward() {
		return reward;
	}

	public void setReward(Item reward) {
		this.reward = reward;
	}
	
	public String getKeyItem() {
		return keyItem;
	}

	public void setKeyItem(String keyItem) {
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
