package twittersemproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.ScrollPaneConstants;

public class TwitterFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	Scanner keyboard = new Scanner(System.in);
	static final int TWITTER_PORT = 2009;
	String onlineUser;
	private final static String newline = "\n";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TwitterFrame frame = new TwitterFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Timestamp////////////
	Date date= new Date();
	
	////////////////////////
	String tweetText;
	String privMessageText;
	String currentOnline;
	
	 // Create the frame.
	//Text Areas and Fields//
	JTextArea tweetTextArea = new JTextArea();
	JScrollPane tweetPane = new JScrollPane( tweetTextArea );
	JTextArea inputTextArea = new JTextArea(10, 10);
	JScrollPane scrollPane = new JScrollPane( inputTextArea );
	JTextArea privateTextArea = new JTextArea(15, 10);
	JScrollPane privatePane = new JScrollPane( privateTextArea );
	JLabel clickTweetLabel = new JLabel("Click Tweet to create a Tweet");
	JTextArea privateMessageTextArea = new JTextArea();
	JLabel lblCurrentUser = new JLabel("Current User:");
	JTextArea followerTextArea = new JTextArea();
	
	//Buttons/////////
	JButton btnSendTweet = new JButton("Tweet");
	JButton viewUserButton = new JButton("View Other User's Tweets");
	JButton sendPrivateMessage = new JButton("Send a Private Message");
	JButton btnFollowUser = new JButton("Follow User");
	
	//Menu items
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	JMenuItem mntmAbout = new JMenuItem("About");
	JMenuItem mntmQuit = new JMenuItem("Quit");
	JMenu mnSignin = new JMenu("Account");
	JMenuItem mntmRegister = new JMenuItem("Register");
	JMenuItem mntmSignin = new JMenuItem("Sign-In");
	JMenuItem mntmSignout = new JMenuItem("Sign-Out");
	JLabel lblUser = new JLabel("User");
	
	
	
	public TwitterFrame() {
		
		//Creates window////
		setTitle("Twitter Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1200,600);
		
		//Button Action Listeners/////////
		btnSendTweet.addActionListener(this);
		viewUserButton.addActionListener(this);
		sendPrivateMessage.addActionListener(this);
		btnFollowUser.addActionListener(this);
		
		/////Menu Bar////////////////
		setJMenuBar(menuBar);
		menuBar.add(mnFile);
		mnFile.add(mntmAbout);
		mntmAbout.addActionListener(this);
		mnFile.add(mntmQuit);
		mntmQuit.addActionListener(this);
		menuBar.add(mnSignin);
		
		
		mnSignin.add(mntmRegister);
		mntmRegister.addActionListener(this);
		mnSignin.add(mntmSignin);
		mntmSignin.addActionListener(this);
		mnSignin.add(mntmSignout);
		mntmSignout.addActionListener(this);
		
		//Button Enablers////
		btnSendTweet.setEnabled(false);
		viewUserButton.setEnabled(false);
		sendPrivateMessage.setEnabled(false);
		btnFollowUser.setEnabled(false);
		
		
		
		//Visibility//////////////
		tweetTextArea.setEditable(false);
		tweetTextArea.setVisible(true);
		tweetPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tweetPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tweetTextArea.setLineWrap(true);
		tweetTextArea.setWrapStyleWord(true);
		tweetPane.setVisible(true);
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		privateTextArea.setLineWrap(true);
		privateTextArea.setWrapStyleWord(true);
		privateMessageTextArea.setEditable(false);
		privateMessageTextArea.setVisible(true);
		privateMessageTextArea.setLineWrap(true);
		privateMessageTextArea.setWrapStyleWord(true);
		privatePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		privatePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		privatePane.setVisible(true);
		followerTextArea.setVisible(true);
		followerTextArea.setEditable(false);
		followerTextArea.setLineWrap(true);
		followerTextArea.setWrapStyleWord(true);
		
		
		//Content Pane///////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnFollowUser, 94, SpringLayout.SOUTH, lblUser);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnFollowUser, 131, SpringLayout.EAST, tweetPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, viewUserButton, -38, SpringLayout.NORTH, followerTextArea);
		sl_contentPane.putConstraint(SpringLayout.NORTH, followerTextArea, 94, SpringLayout.SOUTH, privateMessageTextArea);
		sl_contentPane.putConstraint(SpringLayout.WEST, followerTextArea, 194, SpringLayout.EAST, tweetPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, followerTextArea, -25, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, followerTextArea, -401, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, privateMessageTextArea, 483, SpringLayout.EAST, tweetPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, privateMessageTextArea, 59, SpringLayout.NORTH, tweetTextArea);
		sl_contentPane.putConstraint(SpringLayout.EAST, privateMessageTextArea, -112, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCurrentUser, 40, SpringLayout.EAST, btnSendTweet);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tweetPane, 27, SpringLayout.SOUTH, btnSendTweet);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tweetPane, -7, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, sendPrivateMessage, 0, SpringLayout.SOUTH, lblCurrentUser);
		sl_contentPane.putConstraint(SpringLayout.EAST, sendPrivateMessage, -139, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, viewUserButton, 390, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tweetPane, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, tweetPane, -74, SpringLayout.WEST, viewUserButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, privateMessageTextArea, -309, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUser, -673, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUser, 6, SpringLayout.SOUTH, lblCurrentUser);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCurrentUser, 5, SpringLayout.NORTH, btnSendTweet);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSendTweet, -523, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSendTweet, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, clickTweetLabel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, clickTweetLabel, -6, SpringLayout.NORTH, btnSendTweet);
		
		contentPane.setLayout(sl_contentPane);
		contentPane.add(tweetPane);
		contentPane.add(btnSendTweet);
		contentPane.add(viewUserButton);
		contentPane.add(sendPrivateMessage);
		contentPane.add(btnFollowUser);
		
		//Add Labels and Areas/Fields/////////////////
		contentPane.add(clickTweetLabel);
		contentPane.add(privateMessageTextArea);
		contentPane.add(lblCurrentUser);
		contentPane.add(lblUser);
		contentPane.add(followerTextArea);
		
		

		
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		//Tweeting Actions//////////
		if (e.getSource() == btnSendTweet) { //will also have method to check online status. if offline, will prompt message asking to log in///
			try {
				String host = "127.0.1.1";
				Socket sock = new Socket( host, TWITTER_PORT);
				BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
				PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
				out.println("TWEET");
				String w = in.readLine();
				JOptionPane.showMessageDialog(this, w);
				if (inputTextArea != null) {
					inputTextArea.setText(null);
				int tweetInputData = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this),inputTextArea, "Enter Your Tweet", JOptionPane.OK_CANCEL_OPTION);
				if (tweetInputData == JOptionPane.OK_OPTION) {
					  tweetText = inputTextArea.getText();
					}
				}
				out.println(tweetText);
				out.println(onlineUser);
				String tweet = in.readLine();
				tweetTextArea.append(new Timestamp(date.getTime()) + " " + this.onlineUser + tweet + newline);
				sock.close();
				
	}
			catch ( UnknownHostException x ) {
	            System.err.println( "TwitterClient:  Host doesn't exist" );
	        }
	    catch ( IOException x ) {
	            System.err.println("IOEXCEPTION");
	            System.err.println( x.getMessage() );
	        }
		}
		
		//View User's Tweets////////////
		if(e.getSource() == viewUserButton) {
			try {
				String host = "127.0.1.1";
				Socket sock = new Socket( host, TWITTER_PORT);
				BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
				PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
				String viewedUser = JOptionPane.showInputDialog(this, "Enter the username you would like to view: ");
				out.println("VIEW USER");
				out.println(viewedUser);
				String usersTweets = in.readLine();
				tweetTextArea.append(usersTweets + newline);
				
			
			
			
		}
			catch ( UnknownHostException x ) {
	            System.err.println( "TwitterClient:  Host doesn't exist" );
	        }
	    catch ( IOException x ) {
	            System.err.println("IOEXCEPTION");
	            System.err.println( x.getMessage() );
	        }
		}
		//////////////////////////////////////////////////////////////////////////
		
		//Send Private Message/////////////
		
		if(e.getSource() == sendPrivateMessage) {
			privateMessageTextArea.setVisible(true);
			privateMessageTextArea.setEditable(false);
			String recipient = JOptionPane.showInputDialog(this, "Who would you like to message?");
			try {
				String host = "127.0.1.1";
				Socket sock = new Socket( host, TWITTER_PORT);
				BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
				PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
				out.println("PRIVATE MESSAGE");
				out.println(recipient);
				out.println(onlineUser);
				String w = in.readLine();
				if (w.equals("LEGIT")) {
					int tweetInputData = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this),privateTextArea, "Enter Your Message for" + recipient, JOptionPane.OK_CANCEL_OPTION);
					if (tweetInputData == JOptionPane.OK_OPTION) {
					  privMessageText = privateTextArea.getText();
				}
					out.println(privMessageText);
			}
				else {
					String u = in.readLine();
					JOptionPane.showMessageDialog(this, u);
				}
				
		}
		catch ( UnknownHostException x ) {
	            System.err.println( "TwitterClient:  Host doesn't exist" );
	        }
	    catch ( IOException x ) {
	            System.err.println("IOEXCEPTION");
	            System.err.println( x.getMessage() );
	        }
	}
		///////////////////////////////////////////////////////////////
		
		
		///REGISTER USER//////////
		
		if(e.getSource() == mntmRegister) {
			
			try {
	            String host = "127.0.1.1";
	            String response;
	            String response2;
	            Socket sock = new Socket( host, TWITTER_PORT);
	            BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
	            PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
	            out.println("REGISTER");
	            String username = JOptionPane.showInputDialog(this, "Enter your desired Username");
				String password = JOptionPane.showInputDialog(this, "Enter your desired password");
	            out.println(username);
	            out.println(password);
	            response = in.readLine();
	            //response2 = in.readLine();
	            onlineUser = username;
	            JOptionPane.showMessageDialog(this, response/*response2*/);
	            //in.close();
				sock.close();
		}
			catch ( UnknownHostException x ) {
		            System.err.println( "TwitterClient:  Host doesn't exist" );
		        }
		     catch ( IOException x ) {
		            System.err.println("IOEXCEPTION");
		            System.err.println( x.getMessage() );
		        }
		}
		
		////////////////////////////////////////////////
		
			
		//Sign-In///// GET FOLLOWERS WHEN SIGN IN
		if(e.getSource() == mntmSignin) {
			String username = JOptionPane.showInputDialog(this, "Please Enter Your Username: ");
			String password = JOptionPane.showInputDialog(this,"Please Enter Your Password: ");
			try {
	            String host = "127.0.1.1";
	            String response;
	            Socket sock = new Socket( host, TWITTER_PORT);
	            BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
	            PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
	            out.println("SIGN IN");
	            out.println(username);
	            out.println(password);
	            //String valid = in.readLine();
            	response = in.readLine();
            	//JOptionPane.showMessageDialog(this, response);
            	if (response.equals("ALREADY ON")) {
            		JOptionPane.showMessageDialog(this, "You're already logged in");
            	}
            	if (response.equals("VALID LOGIN NAME")) {
	            	String resp2 = in.readLine();
	            	JOptionPane.showMessageDialog(this, resp2);
	            	onlineUser = username;
	            	lblUser.setText(onlineUser);
	            	String w = in.readLine();
	            	String u = in.readLine();
	            	followerTextArea.setText(w + newline + u);
	            	String x = in.readLine();
	            	privateMessageTextArea.append(x + newline);
	            	btnSendTweet.setEnabled(true);
	            	viewUserButton.setEnabled(true);
	            	sendPrivateMessage.setEnabled(true);
	            	btnFollowUser.setEnabled(true);
	            
	            	//in.close();
	            	//sock.close();
	            	}
	            else if (response.equals("Invalid Username")) {
	            	JOptionPane.showMessageDialog(this, "Invalid Username");
	            }
	            else if (response.equals("FOLLOW ERROR")) {
	            	JOptionPane.showMessageDialog(this, "You aren't following this user/They aren't following you");
	            }
			}
			catch ( UnknownHostException x ) {
	            System.err.println( "TwitterClient:  Host doesn't exist" );
	        }
			catch ( IOException x ) {
	            System.err.println("IOEXCEPTION");
	            System.err.println( x.getMessage() );
	        }
		}
		////////////////////////////////////////////////////////
		
		
		//Sign-Out//////
		if(e.getSource() == mntmSignout) {
			String signConfirm = JOptionPane.showInputDialog(this, "Are you sure you wish to sign out? (Yes/No)");
			try {
	            String host = "127.0.1.1";
	            String response;
	            Socket sock = new Socket( host, TWITTER_PORT);
	            BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
	            PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
	            if (signConfirm.equals("Yes")) {
	            	out.println("SIGN OFF");
	            	out.println(onlineUser);
	            	response = in.readLine();
	            	lblUser.setText("");
	            	JOptionPane.showMessageDialog(this, response);
	            	btnSendTweet.setEnabled(false);
	            	viewUserButton.setEnabled(false);
	            	sendPrivateMessage.setEnabled(false);
	            	btnFollowUser.setEnabled(false);
	            	sock.close();
	            }
	            
	            else {
	            	JOptionPane.showMessageDialog(this, "Sign Out Cancelled");
	            }
			}
			catch ( UnknownHostException x ) {
	            System.err.println( "TwitterClient:  Host doesn't exist" );
	        }
			catch ( IOException x ) {
	            System.err.println("IOEXCEPTION");
	            System.err.println( x.getMessage() );
	        }
		}
		///////////////////////////////////////////////////////
		
		//FOLLOW USER//////////////////////////////////////////
		if(e.getSource() == btnFollowUser) {
			String username = JOptionPane.showInputDialog(this, "Enter your username");
			try {
	            String host = "127.0.1.1";
	            String response;
	            Socket sock = new Socket( host, TWITTER_PORT);
	            BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
	            PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
	            out.println("FOLLOW");
	            out.println(username);
	            response = in.readLine();
	            if (response.equals("USER TO FOLLOW?")) {
	            	String userFollow = JOptionPane.showInputDialog(this, "Enter the username that you would like to follow");
	            	out.println(userFollow);
	            	String h = in.readLine();
	            	followerTextArea.append(newline + h + newline);
	            }
	            else if (response.equals("Username Invalid")) {
	            	username = JOptionPane.showInputDialog(this, "Please enter your username again");
	            	out.println(username);
	            	response = in.readLine();
	            	if (response.equals("USER TO FOLLOW?")) {
	            	String userFollow = JOptionPane.showInputDialog(this, "Enter the username that you would like to follow");
	            	out.println(userFollow);
	            	String h = in.readLine();
	            	followerTextArea.append(h + newline);
	            	
	            	}
	            }
	            
	            sock.close();
			} 
			catch ( UnknownHostException x ) {
	            System.err.println( "TwitterClient:  Host doesn't exist" );
	        }
			catch ( IOException x ) {
	            System.err.println("IOEXCEPTION");
	            System.err.println( x.getMessage() );
	        }
			
		}
		//////////////////////////////////////////////////////////////////////////////
		
		
		//About in menu//////////
		if(e.getSource() == mntmAbout) {
			JOptionPane.showMessageDialog(this, "This program was created by Jacob Miedema. It is a social networking application that allows you to share updates with your followers");
		}
		
		//Exit from Menu//////////
		if (e.getSource() == mntmQuit) {
			System.exit(0);
		}
	}
}
