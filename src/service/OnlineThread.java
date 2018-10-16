package service;

import textadventure.AdventureMap;
import textadventure.InputHandler;
import textadventure.Player;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;

public class OnlineThread implements Runnable {

	private Boolean endLoop = false;
	private AdventureMap am;
	
	
	public OnlineThread(AdventureMap am) {
		super();
		this.am = am;
	}

	@Override
	public void run() {

		//TODO get messages
		
		//for each message: validate if message is in database
		
			/*if message is not in database: get player status
			 *  (this is a little complicated, because the AdventureMap Object has to be set according to the player state too)
			*/
		
			//handle message
		
			//send response to player
		
			//put message into database
		
		

		while(!endLoop) {
			DirectMessageList messages = TwitterService.getDirectMessages();
			for(DirectMessage dm : messages) {
				long UserId = dm.getSenderId();
				if(DatabaseService.isMessageInDatabase(dm))
					//Message was handled already and no further action is required for this message.
					continue;
				else {
					//The message is new. Get Player State.
					Player player = new Player(am);
					if(!DatabaseService.isPlayerOnAdventure(UserId, am)) {
						DatabaseService.putPlayerOnAdventure(UserId, am);
					}
					//load Inventory
					player.setItems(DatabaseService.loadInventory(UserId, am));
					//load Room
					
					InputHandler handler = new InputHandler(am, player);
					
					//TODO: Test
					System.out.println(dm.getText());
					System.out.println(handler.processInput(dm.getText()));
				}
				
			}
			try {
				//set sleep time to at least a minute (60 * 1000)
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setEndLoop(boolean b) {
		endLoop = b;
	}

	
	
}
