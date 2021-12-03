package app.includes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.fonts.*;

public class Login extends JFrame implements ActionListener {
	Style style = new Style();
	Fonts f = new Fonts();
	JButton submit ;
	Conn cn = new Conn();
	JTextField username;
	JPasswordField password;
	JLabel aboutUs;
	public Login() {
		/*--
		 * Welcome Back Label !
		 *-- */
		JLabel welcomeBack = new JLabel("Welcome Back!");
		welcomeBack.setForeground(Color.decode("#2E2A64"));
		welcomeBack.setFont(f.poppinsMedium(20));
		
		/*--
		 * Email Field !
		 *-- */
		 username = style.textField();
		username.setPreferredSize(new Dimension(320, 50));
		JLabel  usernameLabel = style.inputLabel("Username :");
		usernameLabel.setFont(f.poppinsMedium(12));
		JPanel usernameInput = new JPanel(new BorderLayout());
		usernameInput.setBackground(null);
		usernameInput.add(usernameLabel, BorderLayout.NORTH);
		usernameInput.add(username, BorderLayout.CENTER);
		
		/*--
		 * Password Field !
		 *-- */
		 password = new JPasswordField();
		password.setPreferredSize(new Dimension(320, 50));
		password.setBorder(BorderFactory.createLineBorder(Color.decode("#D0DDE5"), 1));
		// let the UI set a border, then squeeze in another border of your own
		password.setBorder(BorderFactory.createCompoundBorder(
				password.getBorder(),
				BorderFactory.createEmptyBorder(0, 15, 0, 0)));
		password.setForeground(new Color(0x24245C));
		password.setBackground(new Color(0xFFFFFF));
		password.setFont(f.poppins(12));
		JLabel  passwordLabel = style.inputLabel("Password : ");
		passwordLabel.setFont(f.poppinsMedium(12));
		JPanel passwordInput = new JPanel(new BorderLayout());
		passwordInput.setBackground(null);
		passwordInput.add(passwordLabel, BorderLayout.NORTH);
		passwordInput.add(password, BorderLayout.CENTER);
		
		/*--
		 * Submit Button !
		 *-- */
		 submit = style.darkBlueButton("Sign In");
		submit.setPreferredSize(new Dimension(320, 50));
		// Action Listener 
		submit.addActionListener(this);
		
		/*--
		 * About Us Label !
		 *-- */
		 aboutUs = new JLabel("Username or Password incorrect");
		aboutUs.setForeground(Color.RED);
		aboutUs.setVisible(false);
		aboutUs.setFont(f.poppinsMedium(12));
		aboutUs.setPreferredSize(new Dimension(320, 100));
		
		
		/*--
		 * Components Container !
		 *-- */
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel container = new JPanel(new GridBagLayout());
		container.setBackground(Color.decode("#F3F3FB"));
		gbc.insets = new Insets(25,0,0,0);
		gbc.gridx = gbc.gridy = 0;
		container.add(welcomeBack, gbc);
		gbc.gridy = 1;
		container.add(usernameInput, gbc);
		gbc.gridy = 2;
		container.add(passwordInput, gbc);
		gbc.gridy = 3;
		container.add(submit, gbc);
		gbc.gridy = 4;
		container.add(aboutUs, gbc);
		/*--
		 * Window & Panels Position !
		 *-- */
		this.getContentPane().add(container);
		this.setTitle("Login");
		style.loadFrameIcon(this);
		this.setMinimumSize(new Dimension(1000, 600));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Home();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == submit) {
		
		
				
			if(username.getText().equals("") ||  String.valueOf(password.getPassword()).equals("")) {JOptionPane.showMessageDialog(null, "fill in all the information "); }
			else {
				if(cn.getUserInfos(username.getText(), String.valueOf(password.getPassword()))) {
					
								username.setText(null);password.setText(null);
								new Home();
							}else  {aboutUs.setVisible(true);}
								
							}
							
						
						
		
		
			
			
		}  
	}
}
