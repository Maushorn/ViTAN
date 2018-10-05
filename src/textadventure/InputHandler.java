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
		}
		else if(inputArray[0].equals("use")) {
			
			reactionMessage = player.useItem(item, iObject)
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
