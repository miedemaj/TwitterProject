package twitterserver;
import java.util.HashMap;
import java.util.ArrayList;

public class UserCollection implements HashMapInterface {
	
	HashMap<String, User> userMap = new HashMap<String, User>();
	//ArrayList<String> userInformation = new ArrayList<String>();
	ArrayList<String> onlineUsers = new ArrayList<String>();
	
	public UserCollection() {
		
	}
        public boolean registerUser(String username, String password) {
        	System.out.println(username);
            User user = userMap.get(username);
            if (user == null) {
                user = new User(username, password);
                userMap.put(username, user);
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
			return true;
		}
	}	
	public boolean followUser(String currentUser, String desiredUser) {
		if (userMap.get(currentUser) != null) {
			if (userMap.get(desiredUser) != null) {
				return userMap.get(currentUser).followUser(desiredUser);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public String getFollowers(String username) {
		User user = userMap.get(username);
		String foll ="";
		if (userMap.get(user) != null) {
			for (int i = 0; i < user.followers.size(); i++) {
					foll = user.followers.get(i);
			}
		}
		return foll;
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
