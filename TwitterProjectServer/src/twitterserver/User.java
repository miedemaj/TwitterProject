package twitterserver;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JList;

public class User {
	String userName;
	String userPassword;
	boolean onlineStatus;
	ArrayList<String> followedUsers = new ArrayList<String>();
	//JList<String> followers = new JList<String>(followedUsers);
	ArrayList<String> followers;
	
	public User(String username, String password) {
                userName = username;
                userPassword = password;
                onlineStatus = false;
                ArrayList<String> followers = new ArrayList<String>();
                ArrayList<String> privateMessages = new ArrayList<String>();
                ArrayList<String> userTweets = new ArrayList<String>();
		//userMap.put(userName, userName);
		//ArrayList<String> userMessages = new ArrayList<String>();
		
	}
	/*public void createMessage() {
		System.out.println("What would you like to tweet");
		String message = keyboard.nextLine();
		User.this.userMessages.add(message);
		//userMessages.add(message);

	}
	*/
	
	
	
	public boolean logOn(String password) {
		if (password.equals(userPassword)) {
			onlineStatus = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean logOff() {
			onlineStatus = false;
			return true;
		
}
	public boolean followUser(String userDesired) {
		if (followedUsers.contains(userDesired)) {
			return false;
		}
		else {
			followedUsers.add(userDesired);
			return true;
		}
	}
	
	public void publicTweet() {
		
		
	}
	
	public void privateMessage() {
		
	}
	
	//public void setStatus(boolean status) {
		//onlineStatus = status;
		
	//}
	public boolean getStatus() {
		return onlineStatus;
	}
}
