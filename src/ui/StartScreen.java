package ui;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.EmptyBorder;

import listeners.ButtonListener;
import listeners.InputHintListener;
import listeners.LogInListener;
import listeners.SignUpListener;

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
		logInPanel.setMaximumSize(new Dimension(400,350));
		logInPanel.setOpaque(true);
		logInPanel.setBackground(new Color(104, 0, 0));
		logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.Y_AXIS));
		logInPanel.setBorder(new EmptyBorder(20, 20, 20, 0));
		logInPanel.setAlignmentY(RIGHT_ALIGNMENT);
		
		//Create Sign Up Panel
		JPanel signUpPanel = new JPanel();
		signUpPanel.setMaximumSize(new Dimension(400,350));
		signUpPanel.setOpaque(true);
		signUpPanel.setBackground(new Color(104, 0, 0));
		signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
		signUpPanel.setBorder(new EmptyBorder(20, 20, 20, 0));
		signUpPanel.setAlignmentY(RIGHT_ALIGNMENT);
		
		this.add(Box.createRigidArea(new Dimension(85, 0)));
		this.add(logInPanel);
		this.add(Box.createRigidArea(new Dimension(70, 0)));
		this.add(signUpPanel);
		

		
		//Adding Components to Log In Panel
		ButtonListener buttonListener = new ButtonListener(screens);

		JLabel login = new JLabel("LOG IN");
		login.setForeground(Color.WHITE);
		login.setAlignmentX(Component.LEFT_ALIGNMENT);
		login.setFont(headerFont);

		JLabel username = new JLabel("Username");
		username.setForeground(Color.WHITE);
		username.setFont(sm0l);
		username.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JTextField user_field = new JTextField();
		user_field.setMaximumSize(new Dimension(350, 35));
		user_field.setFont(textFont);
		user_field.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel password = new JLabel("Password");
		password.setForeground(Color.WHITE);
		password.setFont(sm0l);
		password.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPasswordField pw_field = new JPasswordField();
		pw_field.setMaximumSize(new Dimension(350, 35));
		pw_field.setAlignmentX(Component.LEFT_ALIGNMENT);

		JButton login_button = new JButton("Log in");
		login_button.setFont(sm0l);
		login_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		login_button.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		LogInListener loginListener = new LogInListener(screens, textFont, this, user_field, pw_field);
		user_field.addActionListener(loginListener);
		pw_field.addActionListener(loginListener);
		
		login_button.setActionCommand("login");
		login_button.addActionListener(loginListener);
		
		logInPanel.add(login);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 45)));
		logInPanel.add(username);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		logInPanel.add(user_field);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		logInPanel.add(password);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		logInPanel.add(pw_field);
		logInPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		logInPanel.add(login_button);
		
		//Adding Components to Sign Up Panel
		JLabel signup = new JLabel("SIGN UP");
		signup.setFont(headerFont);
		signup.setForeground(Color.WHITE);
		signup.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JTextField prompt = new JTextField("New here? Create an account now!");
		prompt.setMaximumSize(new Dimension(300, 35));
		prompt.setBackground(new Color(104, 0, 0));
		prompt.setFont(textFont);
		prompt.setForeground(Color.WHITE);
		prompt.setEditable(false);
		prompt.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel username2 = new JLabel("Username");
		username2.setForeground(Color.WHITE);
		username2.setFont(sm0l);
		username2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JTextField user_field2 = new JTextField(5);
		user_field2.setMaximumSize(new Dimension(350, 35));
		user_field2.setFont(textFont);
		user_field2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel password2 = new JLabel("Password");
		password2.setForeground(Color.WHITE);
		password2.setFont(sm0l);
		password2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPasswordField pw_field2 = new JPasswordField(5);
		pw_field2.setMaximumSize(new Dimension(350, 35));
		pw_field2.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel email = new JLabel("Email Address");
		email.setForeground(Color.WHITE);
		email.setFont(sm0l);
		email.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JTextField email_field = new JTextField("perrault@example.com");
		email_field.setForeground(Color.GRAY);
		email_field.setMaximumSize(new Dimension(350, 35));
		email_field.setFont(textFont);
		email_field.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		InputHintListener hint = new InputHintListener(email_field);
		email_field.addFocusListener(hint);
		
		
		JButton signup_button = new JButton("Sign up");

		signup_button.setFont(sm0l);
		signup_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signup_button.setAlignmentX(Component.LEFT_ALIGNMENT);

		SignUpListener suListener = new SignUpListener(screens, textFont, this, user_field2, pw_field2, email_field);
		user_field2.addActionListener(suListener);
		pw_field2.addActionListener(suListener);
		email_field.addActionListener(suListener);
		signup_button.setActionCommand("signup");
		signup_button.addActionListener(suListener);
		
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
		signUpPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		signUpPanel.add(signup_button);
		

	}
	

	
	@Override
	  protected void paintComponent(Graphics g) {
		
		try {
		    bg = ImageIO.read(getClass().getResource("home.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    super.paintComponent(g);
	    g.drawImage(bg, 0, 0, null);
	}
	
	 	

}
