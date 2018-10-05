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
		//move
		if(
				inputArray[0].equals("go") ||
				inputArray[0].equals("move")
				) {
			if(
					inputArray[1].equals("up") ||
					inputArray[1].equals("north")
					)
				reactionMessage = player.moveUp();
			else if(
					inputArray[1].equals("right") ||
					inputArray[1].equals("east")
					)
				reactionMessage = player.moveRight();
			else if(
					inputArray[1].equals("down") ||
					inputArray[1].equals("south")
					)
				reactionMessage = player.moveDown();
			else if(
					inputArray[1].equals("left") ||
					inputArray[1].equals("west")
					)
				reactionMessage = player.moveLeft();
		}//use
		else if(inputArray[0].equals("use")) {
			if(
					inputArray[2].equalsIgnoreCase("with") ||
					inputArray[2].equalsIgnoreCase("and")
					)
			reactionMessage = player.useItem(player.getSpecificItem(inputArray[1]), player.getSpecificInteractiveObject(inputArray[3]));
			else reactionMessage = player.useItem(player.getSpecificItem(inputArray[1]), player.getSpecificInteractiveObject(inputArray[3]));
		}//inspect
		else if(inputArray[0].equalsIgnoreCase("inspect")) {
			for(Item i : player.getItems())
				if (i.getName().equalsIgnoreCase(inputArray[1]))
					reactionMessage = i.getDescription();
			for(InteractiveObject iObj : player.getPosition().getInteractiveObjects())
				if(iObj.getName().equalsIgnoreCase(inputArray[1]))
					reactionMessage = iObj.getDescription();
		
		}
		else reactionMessage = "no valid input";
		//TODO Testing
		testOutput(input, inputArray);

		return reactionMessage;
	}

	//TODO Testmethod
	public void testOutput(String input, String[] inputArray) {
		System.out.print(
				"Input: " + input + "\n" +
				"splitted Input: "
				);
		for(String s : inputArray)
			System.out.println(s);
	}
	
}
