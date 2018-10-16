package service;

import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;

public class OnlineThread implements Runnable {

	Boolean endLoop = false;

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
				//Is message already in database?
				if(DatabaseService.isMessageInDatabase(dm))
					//Message was handled already and no further action is required for this message.
					continue;
				else {
					//The message is new. Get Player State.
					
				}
			}
			
			
			try {
				//set sleep time to at least a minute (60 * 1000)
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

	public void setEndLoop(boolean b) {
		endLoop = b;
	}

	
	
}
