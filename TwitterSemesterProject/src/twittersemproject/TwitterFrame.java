package twittersemproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
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

public class TwitterFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	Scanner keyboard = new Scanner(System.in);
	static final int TWITTER_PORT = 2010;
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 // Create the frame.
	//Text Areas and Fields//
	JTextArea tweetTextArea = new JTextArea();
	JLabel clickTweetLabel = new JLabel("Click Tweet to create a Tweet");
	JTextArea privateMessageTextArea = new JTextArea();
	JLabel lblCurrentUser = new JLabel("Current User:");
	JTextArea followerTextArea = new JTextArea();
	
	//Buttons/////////
	JButton btnSendTweet = new JButton("Tweet");
	JButton tweetButton = new JButton("Send Tweet");
	JButton viewUserButton = new JButton("View Other User's Tweets");
	JButton sendPrivateMessage = new JButton("Send a Private Message");
	JButton confirmSendButton = new JButton("Confirm Send");
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
		tweetButton.addActionListener(this);
		viewUserButton.addActionListener(this);
		sendPrivateMessage.addActionListener(this);
		confirmSendButton.addActionListener(this);
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
		
		//Text Field and Area Listeners////
		
		
		
		//Visibility//////////////
		tweetTextArea.setEditable(true);
		tweetTextArea.setVisible(true);
		privateMessageTextArea.setVisible(false);
		privateMessageTextArea.setEditable(false);
		confirmSendButton.setVisible(false);
		
		
		//Content Pane///////////////
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUser, 0, SpringLayout.NORTH, btnSendTweet);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUser, -92, SpringLayout.WEST, sendPrivateMessage);
		sl_contentPane.putConstraint(SpringLayout.NORTH, confirmSendButton, 6, SpringLayout.SOUTH, privateMessageTextArea);
		sl_contentPane.putConstraint(SpringLayout.WEST, confirmSendButton, 0, SpringLayout.WEST, sendPrivateMessage);
		sl_contentPane.putConstraint(SpringLayout.NORTH, privateMessageTextArea, 0, SpringLayout.NORTH, tweetTextArea);
		sl_contentPane.putConstraint(SpringLayout.WEST, privateMessageTextArea, 0, SpringLayout.WEST, sendPrivateMessage);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, privateMessageTextArea, 156, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, privateMessageTextArea, -169, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, sendPrivateMessage, 0, SpringLayout.NORTH, btnSendTweet);
		sl_contentPane.putConstraint(SpringLayout.EAST, sendPrivateMessage, -165, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, clickTweetLabel, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, clickTweetLabel, -6, SpringLayout.NORTH, btnSendTweet);
		sl_contentPane.putConstraint(SpringLayout.NORTH, viewUserButton, 51, SpringLayout.SOUTH, tweetButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, viewUserButton, 0, SpringLayout.WEST, tweetButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSendTweet, 0, SpringLayout.WEST, tweetButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSendTweet, -6, SpringLayout.NORTH, tweetTextArea);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tweetButton, 172, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tweetButton, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, tweetTextArea, 52, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, tweetTextArea, 0, SpringLayout.WEST, tweetButton);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tweetTextArea, -6, SpringLayout.NORTH, tweetButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, tweetTextArea, 169, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCurrentUser, 0, SpringLayout.NORTH, clickTweetLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblCurrentUser, 43, SpringLayout.EAST, clickTweetLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnFollowUser, 0, SpringLayout.NORTH, viewUserButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnFollowUser, 0, SpringLayout.WEST, sendPrivateMessage);
		
		contentPane.setLayout(sl_contentPane);
		
			
		//Adds Buttons///////////
		contentPane.add(tweetButton);
		contentPane.add(tweetTextArea);
		contentPane.add(btnSendTweet);
		contentPane.add(viewUserButton);
		contentPane.add(sendPrivateMessage);
		contentPane.add(confirmSendButton);
		
		//Add Labels and Areas/Fields/////////////////
		contentPane.add(clickTweetLabel);
		contentPane.add(privateMessageTextArea);
		contentPane.add(lblCurrentUser);
		contentPane.add(lblUser);
		contentPane.add(btnFollowUser);
		contentPane.add(followerTextArea);
		
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, followerTextArea, 6, SpringLayout.SOUTH, btnFollowUser);
		sl_contentPane.putConstraint(SpringLayout.WEST, followerTextArea, -11, SpringLayout.WEST, lblUser);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, followerTextArea, 226, SpringLayout.SOUTH, viewUserButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, followerTextArea, -107, SpringLayout.EAST, contentPane);
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		//Tweeting Actions//////////
		if (e.getSource() == btnSendTweet) { //will also have method to check online status. if offline, will prompt message asking to log in///
			JOptionPane.showMessageDialog(this, "Please press Ok, then type your tweet in the space below");
			tweetTextArea.setVisible(true);
			tweetTextArea.setEditable(true);
			
		}
		if(e.getSource() == tweetButton) {
			JOptionPane.showMessageDialog(this, "Hitting the send tweet button will add the tweet to the tweet ArrayList/hashmap");
		}
		
		//View User's Tweets////////////
		if(e.getSource() == viewUserButton) {
			JOptionPane.showInputDialog(this, "Enter the username you would like to view: ");
			
		}
		
		//Send Private Message/////////////
		if(e.getSource() == sendPrivateMessage) {
			String messageToUser = JOptionPane.showInputDialog(this, "Enter the username that you would like to message: ");
			JOptionPane.showMessageDialog(this, "Press Ok then Enter your message in the text area shown: ");
			privateMessageTextArea.setVisible(true);
			privateMessageTextArea.setEditable(true);
			confirmSendButton.setVisible(true);
		}
		if(e.getSource() == confirmSendButton) {
			JOptionPane.showMessageDialog(this, "Will send the messageToUser string to the entered username");
		}
		
		if(e.getSource() == mntmRegister) {
			String username = JOptionPane.showInputDialog(this, "Enter your desired Username");
			String password = JOptionPane.showInputDialog(this, "Enter your desired password");
			try {
	            String host = "127.0.1.1";
	            String response;
	            String response2;
	            Socket sock = new Socket( host, TWITTER_PORT);
	            BufferedReader in = new BufferedReader( new InputStreamReader( sock.getInputStream() ) );
	            PrintWriter out = new PrintWriter( sock.getOutputStream(), true );
	            out.println("REGISTER");
	            out.println(username);
	            out.println(password);
	            response = in.readLine();
	            response2 = in.readLine();
	            onlineUser = username;
	            JOptionPane.showMessageDialog(this, response + " " + response2);
	            
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
	            response = in.readLine();
	            JOptionPane.showMessageDialog(this, response);
	            onlineUser = username;
	            lblUser.setText(onlineUser);
	            String w = in.readLine();
	            String u = in.readLine();
	            followerTextArea.setText(w + newline + u);
	            
	            //in.close();
	            //sock.close();
			}
			catch ( UnknownHostException x ) {
	            System.err.println( "TwitterClient:  Host doesn't exist" );
	        }
			catch ( IOException x ) {
	            System.err.println("IOEXCEPTION");
	            System.err.println( x.getMessage() );
	        }
		}
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
	            
	            	in.close();
	            	sock.close();
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
	            	followerTextArea.append(h + newline);
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
	            
	            in.close();
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
