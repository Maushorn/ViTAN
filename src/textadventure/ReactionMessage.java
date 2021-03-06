package textadventure;

public class ReactionMessage {
	public static final String INVALID_MOVE;
	public static final String VALID_MOVE;
	public static final String SUCCESS;
	public static final String ITEM_MISMATCH;
	public static final String ITEM_MISSING;
	public static final String INVENTORY_EMPTY;
	public static final String INVALID_INPUT;
	public static final String INTERACTIVE_OBJECT_MISSING;
	public static final String EXPLAIN_TAKE;
	public static final String ITEM_TAKEN;
	public static final String NO_REWARD;

	
	static {
//		VALID_MOVE = "OK";
//		INVALID_MOVE = "This move is not possible.";
//		INVALID_INPUT = "Wrong syntax.";
//		SUCCESS = "Success!";
//		ITEM_MISMATCH = "This is the wrong item.";
//		ITEM_MISSING = "No such item available.";
//		INTERACTIVE_OBJECT_MISSING = "No such object available.";
//		INVENTORY_EMPTY = "Your inventory is empty.";
//		EXPLAIN_TAKE = "Type \"take [item]\" to try to pick up a item.";
//		ITEM_TAKEN = "You picked up a ";
//		NO_REWARD = "Nothing happens...";

		VALID_MOVE = "OK";
		INVALID_MOVE = "Dieser Zug ist nicht m�glich.";
		INVALID_INPUT = "Falsche Syntax.";
		SUCCESS = "Erfolg!";
		ITEM_MISMATCH = "Das ist der falsche Gegenstand.";
		ITEM_MISSING = "Eine solcher Gegenstand ist nciht verf�gbar.";
		INTERACTIVE_OBJECT_MISSING = "Ein solches Objekt ist nicht verf�gbar.";
		INVENTORY_EMPTY = "Dein Inventar ist leer.";
		EXPLAIN_TAKE = "Schreibe \"take [Gegenstand]\" um den Gegenstand aufzuheben.";
		ITEM_TAKEN = "Du erh�ltst: ";
		NO_REWARD = "Nichts passiert...";
	}
}
