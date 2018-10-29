package service;

import application.AuthenticationInfo;
import textadventure.AdventureMap;
import textadventure.InputHandler;
import textadventure.Player;
import textadventure.Room;
import twitter4j.DirectMessage;
import twitter4j.DirectMessageList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OnlineThread implements Runnable {

	private Boolean endLoop = false;
	private AdventureMap am;
	private long ownID;
	TwitterService twitter;
	
	public OnlineThread(AdventureMap am) throws AuthenticationException{
		super();
		this.am = am;
		FileInputStream fis;
		ObjectInputStream ois;
		AuthenticationInfo info = null;
		try {
			fis = new FileInputStream(".\\ConfigData.ser");
			ois = new ObjectInputStream(fis);
			info = (AuthenticationInfo)ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new AuthenticationException("Authentication data could not be loaded." +
					"Please make sure the Authentication tokens are set and correct.");
		}
		if(info == null)
			throw new AuthenticationException("Authentication data could not be loaded." +
					"Please make sure the Authentication tokens are set and correct.");
		twitter = new TwitterService(info);
        ownID = twitter.getOwnUserID();

	}

	@Override
	public void run() {
		while(!endLoop) {
			DirectMessageList messages = null;
			try {
				messages = twitter.getDirectMessages();
			}catch(Exception ex){
				System.out.println("Messages konnten nicht abgerufen werden.");
				messages = null;
				ex.printStackTrace();
			}

			if(messages != null)
			for(int i = messages.size()-1; i >= 0; --i) {
				AdventureMap currentMap = new AdventureMap(am);
				DirectMessage dm = messages.get(i);
				long userID = dm.getSenderId();
				if(DatabaseService.isMessageInDatabase(dm))
					//Message was handled already and no further action is required for this message.
					continue;
				if(ownID == dm.getSenderId()) {
					//No need to handle the Server-Messages
					DatabaseService.insertMessage(dm, currentMap);
					continue;
				}
				else {
					DatabaseService.insertMessage(dm, currentMap);
					//The message is new. Get Player State.
					Player player = new Player(currentMap);
					if(!DatabaseService.isPlayerOnAdventure(userID, currentMap)) {
						DatabaseService.putPlayerOnAdventure(userID, currentMap);
					}
					//load Inventory
					player.setItems(DatabaseService.loadInventory(userID, currentMap));
					//load Room
					Room r = DatabaseService.loadRoom(userID, currentMap);
					if(r != null)
						player.setPosition(r);
					
					InputHandler handler = new InputHandler(currentMap, player);
					//send answer to UserID and process Input
					twitter.sendDirectMessage(userID, handler.processInput(dm.getText()));

					//TODO: Test
					System.out.println("Antwort gesendet");
					System.out.println("UserID: " + userID);
					System.out.println(player.getItems());


					DatabaseService.saveGame(userID, player, currentMap);

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
