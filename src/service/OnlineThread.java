package service;

import textadventure.AdventureMap;
import textadventure.InputHandler;
import textadventure.Player;
import textadventure.Room;
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
		
		long ownID = TwitterService.getOwnUserID(); 

		while(!endLoop) {
			DirectMessageList messages = TwitterService.getDirectMessages();
			//remove old messages
//			for(int i = 0; i <= messages.size(); ++i) {
//				messages.get(i).getCreatedAt()
//				if(messages.get(i).getCreatedAt().before(new Date().setDate(new Date().getDate()-1))) {
//					
//				}
//			}
			
			for(int i = messages.size()-1; i >= 0; --i) {
				DirectMessage dm = messages.get(i);
				long userID = dm.getSenderId();
				if(ownID == dm.getSenderId() || DatabaseService.isMessageInDatabase(dm))
					//Message was handled already and no further action is required for this message.
					continue;
				else {
					DatabaseService.insertMessage(dm, am);
					//The message is new. Get Player State.
					Player player = new Player(am);
					if(!DatabaseService.isPlayerOnAdventure(userID, am)) {
						DatabaseService.putPlayerOnAdventure(userID, am);
					}
					//load Inventory
					player.setItems(DatabaseService.loadInventory(userID, am));
					//load Room
					Room r = DatabaseService.loadRoom(userID, am);
					if(r != null)
						player.setPosition(DatabaseService.loadRoom(userID, am));
					
					InputHandler handler = new InputHandler(am, player);
					//send answer to UserID and process Input
					TwitterService.sendDirectMessage(userID, handler.processInput(dm.getText()));
					
					DatabaseService.saveGame(userID, player, am);

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
