package twitterserver;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

public class UserCollection implements HashMapInterface {
	
	HashMap<String, User> userMap = new HashMap<String, User>();
	//ArrayList<String> userInformation = new ArrayList<String>();
	ArrayList<String> onlineUsers = new ArrayList<String>();
	LinkedList<String> Tweets = new LinkedList<String>();
	String foll;
	
	public UserCollection() {
		
	}
        public boolean registerUser(String username, String password) {
            User user = userMap.get(username);
            if (user == null) {
                user = new User(username, password);
                userMap.put(username, user);
                System.out.println(user);
                return true;
            }
            
            else {
                return false;
            }
        }
        
        public boolean signIn(String username, String password) {
        	User user = userMap.get(username);
        	boolean corr = user.logOn(password);
        	if (corr == true) {
        		onlineUsers.add(username);
        		return true;
        	}
        	else {
        		return false;
        		
        	}
        	//user.userPassword.compareTo(password);
        	/*if (password.equals(user.userPassword)) {
        		return true;
        	}
        		else{
        			return false;
        		}*/
        	}
        public boolean signOff(String username) {
        	User user = userMap.get(username);
        	if (user != null) {
        		user.logOff();
        		return true;
        	}
        	else {
        		return false;
        	}
        		
        	}
	
	
	public boolean checkValidName(String user) {
		User username = userMap.get(user);
		System.out.println(username);
		if (username == null){
			return false;
		}
		else {
			System.out.println("Valid Name");
			return true;
		}
	}
	
	public boolean checkSameName(String registered, String desiredUser) {
		User username = userMap.get(registered);
		if (desiredUser.equals(registered)) {
			return true;
		}
		else {
			return false;
		}
			
	}
		
		
	public boolean followUser(String currentUser, String desiredUser) {
		if (userMap.get(currentUser) != null) {
			if (userMap.get(desiredUser) != null) {
				//if (userMap.get(currentUser).followAUser(desiredUser) == true) {
					System.out.println("made it to last if statement");
					return userMap.get(currentUser).followAUser(desiredUser);
				//}
				//else {
					//return false;
				//}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
		
		/*User current = userMap.get(currentUser);
		User desired = userMap.get(desiredUser);
		if (current != null) {
			if (desired != null) {
				//desired.addFollower(currentUser);
				current.followAUser(desiredUser);
				return true;
			}
			else {
				System.out.println("Desired is null");
				return false;
			}
		}
		else {
			System.out.println("Current is null");
			return false;
		}*/
		
		/*if (userMap.get(currentUser) != null) {
			User current = userMap.get(currentUser);
			if (userMap.get(desiredUser) != null) {
				User desired = userMap.get(desiredUser);
				desired.addFollower(currentUser);
				return current.followAUser(desiredUser);
				}
				else {
					return false;	
				}
			}
				else {
					return false;
				}
		}*/
	
	public String getFollowers(String username) {
		User user = userMap.get(username);
		if (userMap.get(username) != null) {
			for (int i = 0; i < user.followers.size(); i++) {
					foll = user.followers.get(i);
			}
			//String ers = foll;
			return foll;
		}
		else {
			return "No Followers";
		}
		
	}
	
	public void sendPublicTweet(String user, String tweet) {
		User us = userMap.get(user);
		String addedTweet = tweet;
		us.userTweets.add(tweet);
		Tweets.add(addedTweet);
		
	}
	
	public void sendPrivateMessage(String desiredUser, String message) {
		User desired = userMap.get(desiredUser);
		String privateMessage = message;
		desired.privateMessage(privateMessage);
	}
	public ArrayList<String> getMessages(String user) {
		User current = userMap.get(user);
		return current.privateMessages;
	}
	
	public void /*String*/ returnAllTweets() {
		System.out.println(Tweets);

	}
	
	
	
	/*public boolean checkValidPassword(String user, String password) {
		User username = userMap.get(user);
		if (password.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}*/
}
