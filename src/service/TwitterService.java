package service;

import application.AuthenticationInfo;
import twitter4j.DirectMessageList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterService {

	private final String CONSUMER_KEY;
	private final String CONSUMER_SECRET;
	private final String ACCESS_TOKEN;
	private final String ACCESS_TOKEN_SECRET;
	
	//TODO: These keys shall be set through GUI
	TwitterService(AuthenticationInfo info) {
		CONSUMER_KEY = info.getCONSUMER_KEY();
		CONSUMER_SECRET = info.getCONSUMER_SECRET();
		ACCESS_TOKEN = info.getACCESS_TOKEN();
		ACCESS_TOKEN_SECRET = info.getACCESS_TOKEN_SECRET();
	}

	
	/**Returns a single User object with information from the specified Twitterhandle.
	 * (preceded @ has to be omitted)
	 * 
	 * @param twitterHandle
	 * @return
	 */
	public User getTwitterUser(String twitterHandle) {
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		User user = null;
		try {
			user = twitter.showUser(twitterHandle);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**Returns a single User object with information from specified ID.
	 * 
	 * @param userId
	 * @return
	 */
	public User getTwitterUser(long userId) {
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		User user = null;
		try {
			user = twitter.showUser(userId);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**Returns a List of the last 30 day's messages.
	 * Caution: Messages are ordered from newest to oldest.
	 * So for handling messages chronologically, you have to begin at the end!
	 * 
	 * @return
	 */
	public DirectMessageList getDirectMessages(){
		//TODO: Test-Output
		System.out.println("DirectMessages werden abgerufen");

		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		DirectMessageList messageList = null;
		try {
			messageList = twitter.getDirectMessages(50);
			//TODO: For the moment I have to comment this out because of the rate limit.
			//Only 50 messages can be read at one request.
//			String cursor = messageList.getNextCursor();
//			while(cursor!=null) {
//				messageList.addAll(twitter.getDirectMessages(50, cursor));
//				cursor = twitter.getDirectMessages(50, cursor).getNextCursor();
//			}
		} catch (TwitterException e) {
			e.printStackTrace();
		} 
		return messageList;
	}
	
	/**Tweet something in case you want every follower to see it.
	 * 
	 * @param message
	 */
	public void tweet(String message) {
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		
		try {
			twitter.updateStatus(message);
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
	}
	
	/**Send DirectMessage to User via TwitterHandle.
	 * 
	 * @param twitterhandle
	 * @param message
	 */
	public void sendDirectMessage(String twitterhandle, String message) {
		
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		try {
			twitter.sendDirectMessage(twitterhandle, message);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	/**Send DirectMessage to User via UserID.
	 * 
	 * @param userID
	 * @param message
	 */
	public void sendDirectMessage(long userID, String message) {
		
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		try {
			twitter.sendDirectMessage(userID, message);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public long getOwnUserID() {
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		try {
			return twitter.getId();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}
