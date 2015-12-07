package twitterserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JList;

public class User {
	String userName;
	String userPassword;
	boolean onlineStatus;
	ArrayList<String> followedUsers;
	//JList<String> followers = new JList<String>(followedUsers);
	ArrayList<String> followers;
	LinkedList<String> userTweets;
	ArrayList<String> privateMessages;
	
	public User(String username, String password) {
                userName = username;
                userPassword = password;
                onlineStatus = false;
                followers = new ArrayList<String>();
                privateMessages = new ArrayList<String>();
                followedUsers = new ArrayList<String>();
                userTweets = new LinkedList<String>();
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
	
	/*public ArrayList<String> getFollow() {
		return followers;
	}*/
	
	public boolean logOff() {
			onlineStatus = false;
			return true;
		
}
	public boolean followAUser(String userDesired) {
		if (followedUsers.contains(userDesired)) {
			return false;
		}
		else {
			followedUsers.add(userDesired);
			return true;
		}
	}
	
	public boolean addFollower(String username) {
		if (followers.contains(username)) {
			return false;
		}
		else {
			followers.add(username);
			return true;
		}
	}
	
	public void publicTweet(String tweet) {
		userTweets.add(tweet);
		
	}
	
	public void privateMessage(String message) {
		privateMessages.add(message);
	}
	
	//public void setStatus(boolean status) {
		//onlineStatus = status;
		
	//}
	public boolean getStatus() {
		return onlineStatus;
	}
}
