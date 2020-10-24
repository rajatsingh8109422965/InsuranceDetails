package com.insurance.info.signup;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.insurance.info.config.DataSourceConfig;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.Button;

public class SignUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int xMouse;
	private int yMouse;

	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField mobileNoTextField;

	public SignUp() {
		setupFrame();

		makePaneDraggable();
		setupExitAndMinimizeButton();

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		firstNameLabel.setBounds(30, 69, 99, 29);
		contentPane.add(firstNameLabel);

		firstNameTextField = new JTextField();
		firstNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		firstNameTextField.setBounds(30, 94, 322, 24);
		contentPane.add(firstNameTextField);
		firstNameTextField.setColumns(10);

		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lastNameLabel.setBounds(30, 127, 99, 29);
		contentPane.add(lastNameLabel);

		lastNameTextField = new JTextField();
		lastNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(30, 152, 322, 24);
		contentPane.add(lastNameTextField);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		emailTextField.setColumns(10);
		emailTextField.setBounds(30, 212, 322, 24);
		contentPane.add(emailTextField);

		JLabel emailLabel = new JLabel("Email Address");
		emailLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		emailLabel.setBounds(30, 187, 99, 29);
		contentPane.add(emailLabel);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		usernameLabel.setBounds(30, 247, 99, 29);
		contentPane.add(usernameLabel);

		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(30, 272, 322, 24);
		contentPane.add(usernameTextField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		passwordLabel.setBounds(30, 305, 99, 29);
		contentPane.add(passwordLabel);

		passwordTextField = new JTextField();
		passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(30, 330, 322, 24);
		contentPane.add(passwordTextField);

		JLabel mobileNoLabel = new JLabel("Mobile No.");
		mobileNoLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		mobileNoLabel.setBounds(30, 365, 99, 29);
		contentPane.add(mobileNoLabel);

		mobileNoTextField = new JTextField();
		mobileNoTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		mobileNoTextField.setColumns(10);
		mobileNoTextField.setBounds(30, 390, 322, 24);
		contentPane.add(mobileNoTextField);

		Button signUpButton = new Button("SIGN UP");
		signUpButton.setFont(new Font("Ebrima", Font.BOLD, 16));
		signUpButton.setForeground(new Color(255, 255, 255));
		signUpButton.setBackground(new Color(0, 0, 0));
		signUpButton.setBounds(108, 430, 146, 41);
		contentPane.add(signUpButton);

	}

	private void setupFrame() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setBounds(100, 100, 651, 504);
		contentPane.setLayout(null);

	}

	private void setupExitAndMinimizeButton() {

		// *************** Exit Button ****************
		JLabel exitButton = new JLabel("X");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (DataSourceConfig.getConnection() != null) {
					try {
						DataSourceConfig.getConnection().close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		exitButton.setBounds(608, 0, 18, 29);
		contentPane.add(exitButton);

		// *************** Minimize Button ****************
		JLabel minimizeButton = new JLabel("_");
		minimizeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		minimizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		minimizeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		minimizeButton.setBounds(577, 0, 18, 21);
		contentPane.add(minimizeButton);

	}

	private void makePaneDraggable() {
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xMouse = e.getX();
				yMouse = e.getY();
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				setLocation(x - xMouse, y - yMouse);
			}
		});

	}

}
