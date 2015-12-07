package twitterserver;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Server {
	static Scanner keyboard = new Scanner(System.in);

	
	
	
	
	public static void main(String[] args) {
		//LinkedList<String> publicTweets = new LinkedList<String>();
	try	{
		
		ServerSocket listen = new ServerSocket (2010);
		System.out.println( "Listening on port:  " + listen.getLocalPort() );
	    System.out.println("Listening on address: " + InetAddress.getLocalHost());
	    UserCollection userCollect = new UserCollection();
	    
	    while ( true ) {
			Socket client = listen.accept();
			
			PrintWriter out =new PrintWriter( client.getOutputStream(), true );
			
	        BufferedReader in = new BufferedReader( new InputStreamReader( client.getInputStream() ) );
		
        
		String w;
		String p;
		String j;
		String u;
		String k;
		String line = in.readLine();
		
		//Register Account//
			if(line.equals("REGISTER")) {
				w = in.readLine();
				p = in.readLine();
				if (userCollect.checkValidName(w) == false) {
				boolean avail = userCollect.registerUser(w, p);
					if (avail == true) {
						out.println("Registration Successful");
						out.println("Your new username is: " + w);
					}
					else{
						out.println("Registration Failed");
					}
				
				}
				else {
					out.println("Unavailable Username");
				}
				line = in.readLine();
			}
			
			
		//Log On//	
			else if (line.equals("SIGN IN")){
				j = in.readLine();
				System.out.println(j);
				System.out.println(userCollect.checkValidName(j));
				if (userCollect.checkValidName(j) == true) {
					k = in.readLine();
					boolean correct = userCollect.signIn(j, k);
					if (correct == true) {
						out.println("VALID LOGIN NAME");
						userCollect.onlineUsers.add(j);
						out.println("Log on Successful");
						out.println("Current followers are: ");
						out.println(userCollect.getFollowers(j));
						out.println(userCollect.getMessages(j));
					}
				}
				else {
					out.println("Invalid Username");
				}
			}
				//client.close();
			
		//Log Off//
			else if (line.equals("SIGN OFF")) {
				w = in.readLine();
				if (userCollect.signOff(w) == true) {
					userCollect.onlineUsers.remove(w);
					out.println("Log Off Successful");
					
				}
				else {
					out.println("Log Off Unsuccessful");
				}
				line = in.readLine();
				//client.close();
			}
			
		//Follow User//
			else if (line.equals("FOLLOW")) {
				w = in.readLine();
				if (userCollect.checkValidName(w) == true) {
					out.println("USER TO FOLLOW?");
					u = in.readLine();
					if (userCollect.checkValidName(u) == true) {
						System.out.println("Desired is Valid");
						//userCollect.followUser(w, u);
						if (userCollect.followUser(w, u) == true) {
							out.println("Follow Successful. You are now following" + " " + u);
						}
						else {
							System.out.println("Follow didn't work");
						}
					}
					else {
						out.println("Follow Unsuccessful");
					}
				//client.close();
			}
				else {
					out.println("Username Invalid");
					w = in.readLine();
					if (userCollect.checkValidName(w) == true) {
						out.println("USER TO FOLLOW?");
						u = in.readLine();
						if (userCollect.checkValidName(u)) {
							userCollect.followUser(w, u);
							out.println("Follow Successful. You are now following" + " " + u);
						}
						else {
							out.println("Follow Unsuccessful");
				}
			}
		}
	}
			
			//Tweet//////////////////
			
			else if (line.equals("TWEET")) {
				out.println("Enter what you'd like to tweet");
				w = in.readLine();
				u = in.readLine();
				//publicTweets.add(w);
				if (userCollect.checkValidName(u) == true) {
					userCollect.sendPublicTweet(u, w);
					out.println(userCollect.Tweets);
				}
				
			}
			
			///PRIVATE MESSAGE/////////////////
			
			else if (line.equals("PRIVATE MESSAGE")) {
				w = in.readLine(); //recipient on client side
				p = in.readLine();
				if (userCollect.checkValidName(w) == true) {
					out.println("LEGIT");
					u = in.readLine();
					if (userCollect.checkFollowing(p, w) == true || userCollect.checkFollower(w, p) == true) {
					userCollect.sendPrivateMessage(w, u);
				}
					else {
						out.println("FOLLOW ERROR");
					}
			}
				else {
					out.println("NOT VALID USERNAME");
				}
			}
		//listen.close();
		}
	}
	catch( IOException e) {
	    System.err.println( e.getMessage() );
	}
	/////////////////////////////////////
}
}
