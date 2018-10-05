package textadventure;

public class ReactionMessage {
	public static final String INVALID_MOVE;
	public static final String VALID_MOVE;
	public static final String SUCCESS;
	public static final String ITEM_MISMATCH;
	public static final String ITEM_MISSING;
	public static final String INVENTORY_EMPTY;
	
	
	static {
		VALID_MOVE = "OK";
		INVALID_MOVE = "This move is not possible.";
		SUCCESS = "Success!";
		ITEM_MISMATCH = "This is the wrong item.";
		ITEM_MISSING = "No such item available.";
		INVENTORY_EMPTY = "Your inventory is empty.";
	}
}
