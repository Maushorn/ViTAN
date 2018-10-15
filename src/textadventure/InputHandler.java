package textadventure;

public class InputHandler {

	AdventureMap adventure;
	Player player;

	public InputHandler(AdventureMap adventure, Player player) {
		super();
		this.adventure = adventure;
		this.player = player;
	}

	public String processInput(String input) {
		String[] inputArray;
		inputArray = input.split(" ");
		String reactionMessage = "";
		
		// move
		if (inputArray[0].equals("go") || inputArray[0].equals("move")) {
			if (inputArray[1].equals("up") || inputArray[1].equals("north"))
				reactionMessage = player.moveUp();
			else if (inputArray[1].equals("right") || inputArray[1].equals("east"))
				reactionMessage = player.moveRight();
			else if (inputArray[1].equals("down") || inputArray[1].equals("south"))
				reactionMessage = player.moveDown();
			else if (inputArray[1].equals("left") || inputArray[1].equals("west"))
				reactionMessage = player.moveLeft();
		} // use
		else if (inputArray[0].equals("use") && inputArray.length >= 3) {
			if (inputArray[2].equalsIgnoreCase("with") || inputArray[2].equalsIgnoreCase("and")) {
				if (inputArray.length > 3) {
					reactionMessage = player.useItem(player.getSpecificItem(inputArray[1]),
							player.getSpecificInteractiveObject(inputArray[3]));
					if(reactionMessage.equals(ReactionMessage.SUCCESS)) {
						InteractiveObject iObj = player.getSpecificInteractiveObject(inputArray[3]);
						Item reward = iObj.getReward();
						player.getItems().add(reward);
					}
				}
				else {
					reactionMessage = ReactionMessage.INVALID_INPUT;
				}
			} else
				reactionMessage = player.useItem(player.getSpecificItem(inputArray[1]),
						player.getSpecificInteractiveObject(inputArray[2]));
		} // inspect
		else if (inputArray[0].equalsIgnoreCase("inspect")) {
			if (inputArray.length == 1) {
				reactionMessage = player.getPosition().getDescription();
			} else {
				reactionMessage = ReactionMessage.ITEM_MISSING;
				if (!player.getItems().isEmpty()) {
					for (Item i : player.getItems())
						if (i.getName().equalsIgnoreCase(inputArray[1]))
							reactionMessage = i.getDescription();
				}
				if (!player.getPosition().getInteractiveObjects().isEmpty()) {
					for (InteractiveObject iObj : player.getPosition().getInteractiveObjects())
						if (iObj.getName().equalsIgnoreCase(inputArray[1]))
							reactionMessage = iObj.getDescription();
				} 
					

			}
		} // take
		else if (inputArray[0].equalsIgnoreCase("take")) {
			if (inputArray.length == 1)
				reactionMessage = ReactionMessage.EXPLAIN_TAKE;
			else
				reactionMessage = player.takeSpecificItem(inputArray[1]);
		} else
			reactionMessage = "no valid input";
		// TODO Testing
		testOutput(input, inputArray);

		return reactionMessage;
	}

	// TODO Testmethod
	public void testOutput(String input, String[] inputArray) {
		System.out.print("Input: " + input + "\n" + "splitted Input: ");
		for (String s : inputArray)
			System.out.println(s);
	}

}
