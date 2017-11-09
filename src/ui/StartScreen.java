package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import listeners.ButtonListener;
import listeners.InputHintListener;

public class StartScreen extends JPanel {
	
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private BufferedImage bg = null;
	
	public StartScreen(JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}
	
	public void create() {
		
		//Change Font size as required
		Font sm0l = headerFont.deriveFont((float) 14);
		
		//Set BoxLayout
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		//Create Log in Panel
		JPanel logInPanel = new JPanel();
		logInPanel.setMaximumSize(new Dimension(300,300));
		logInPanel.setOpaque(true);
		logInPanel.setBackground(new Color(104, 0, 0));
		logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.Y_AXIS));
		logInPanel.setBorder(new EmptyBorder(20, 20, 20, 10));
		logInPanel.setAlignmentY(RIGHT_ALIGNMENT);
		
		//Create Sign Up Panel
		JPanel signUpPanel = new JPanel();
		signUpPanel.setMaximumSize(new Dimension(300,300));
		signUpPanel.setOpaque(true);
		signUpPanel.setBackground(new Color(104, 0, 0));
		signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
		signUpPanel.setBorder(new EmptyBorder(20, 20, 20, 0));
		signUpPanel.setAlignmentY(RIGHT_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(50, 0)));
		this.add(logInPanel);
		this.add(Box.createRigidArea(new Dimension(70, 0)));
		this.add(signUpPanel);
		

		
		//Adding Components to Log In Panel
		ButtonListener buttonListener = new ButtonListener(screens);
		
		JLabel login = new JLabel("LOG IN");
		login.setForeground(Color.WHITE);
		login.setAlignmentX(LEFT_ALIGNMENT);
		login.setFont(headerFont);

		JLabel username = new JLabel("Username");
		username.setForeground(Color.WHITE);
		username.setFont(sm0l);
		
		JTextField user_field = new JTextField(5);
		user_field.setMaximumSize(new Dimension(500, 30));
		user_field.setFont(textFont);

		JLabel password = new JLabel("Password");
		password.setForeground(Color.WHITE);
		password.setFont(sm0l);
		
		JPasswordField pw_field = new JPasswordField(5);
		pw_field.setMaximumSize(new Dimension(500, 30));
		
		JButton login_button = new JButton("Log in");
		login_button.setActionCommand("login");
		login_button.addActionListener(buttonListener);
		login_button.setFont(sm0l);
		login_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		logInPanel.add(login);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		logInPanel.add(username);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		logInPanel.add(user_field);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		logInPanel.add(password);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		logInPanel.add(pw_field);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		logInPanel.add(login_button);
		
		//Adding Components to Sign Up Panel
		JLabel signup = new JLabel("SIGN UP");
		JLabel prompt = new JLabel("Create an account now!");
		signup.setFont(headerFont);
		prompt.setFont(textFont);
		signup.setForeground(Color.WHITE);
		prompt.setForeground(Color.WHITE);

		
		JLabel username2 = new JLabel("Username");
		username2.setForeground(Color.WHITE);
		username2.setFont(sm0l);
		
		JTextField user_field2 = new JTextField(5);
		user_field2.setMaximumSize(new Dimension(500, 30));
		user_field2.setFont(textFont);
		
		JLabel password2 = new JLabel("Password");
		password2.setForeground(Color.WHITE);
		password2.setFont(sm0l);
		
		JPasswordField pw_field2 = new JPasswordField(5);
		pw_field2.setMaximumSize(new Dimension(500, 30));
		
		JLabel email = new JLabel("Email Address");
		email.setForeground(Color.WHITE);
		email.setFont(sm0l);
		
		JTextField email_field = new JTextField("perrault@example.com");
		email_field.setForeground(Color.GRAY);
		email_field.setMaximumSize(new Dimension(500, 30));
		email_field.setFont(textFont);
		
		InputHintListener hint = new InputHintListener(email_field);
		email_field.addFocusListener(hint);
		
		JButton signup_button = new JButton("Sign up");
		signup_button.setActionCommand("signup");
		signup_button.addActionListener(buttonListener);
		signup_button.setFont(sm0l);
		signup_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		signUpPanel.add(signup);
		signUpPanel.add(prompt);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		signUpPanel.add(username2);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		signUpPanel.add(user_field2);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(password2);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		signUpPanel.add(pw_field2);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		signUpPanel.add(email);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		signUpPanel.add(email_field);
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		signUpPanel.add(signup_button);
		

	}
	

	
	@Override
	  protected void paintComponent(Graphics g) {
		
		try {
		    bg = ImageIO.read(getClass().getResource("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    super.paintComponent(g);
	    g.drawImage(bg, 0, 0, null);
	}
	
	 	

}
