package service;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterService {

	private static final String CONSUMER_KEY;
	private static final String CONSUMER_SECRET;
	private static final String ACCESS_TOKEN;
	private static final String ACCESS_TOKEN_SECRET;
	
	static {
		CONSUMER_KEY = "eHTZPuGf3c4Cz29mkCLGeP8VJ";
		CONSUMER_SECRET = "BwGyCqmpw2afiTXlQ7AqdJsWq9QhTka4JqhSAidnD8YmnXOU5r";
		ACCESS_TOKEN = "1015692032541646849-qiuENhpnzFNG4IKO4l1fbTLHB8xnn6";
		ACCESS_TOKEN_SECRET = "bk6W4SHLhW6vdwrCQbw9EaCPFt2hnL0dCLhufafMe1MGB";
	}

	public static ResponseList<DirectMessage> getDirectMessages(){
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey(CONSUMER_KEY);
		configBuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
		configBuilder.setOAuthAccessToken(ACCESS_TOKEN);
		configBuilder.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Configuration config = configBuilder.build();
		TwitterFactory twitterFactory = new TwitterFactory(config);
		Twitter twitter = twitterFactory.getInstance();
		ResponseList<DirectMessage> messageList = null;
		try {
			messageList = twitter.getDirectMessages();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return messageList;
	}
	
	public static void tweet(String message) {
	 
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
	
	public static void sendDirectMessage(String twitterhandle, String message) {
		
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
	
}