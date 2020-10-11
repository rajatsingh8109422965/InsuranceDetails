package com.insurance.info;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.insurance.info.config.Conn;
import com.insurance.info.signup.SignUp;

public class InsuranceApplication extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField unText;
	private JPasswordField paswdText;
	private JButton login, signup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsuranceApplication frame = new InsuranceApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsuranceApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Image image = new ImageIcon(
				"C:\\Users\\Rajat Singh\\eclipse-workspace\\Customer Insurance Details\\Resource\\Login1.png")
						.getImage();
		setBounds(100, 100, 682, 430);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel userName = new JLabel("USERNAME");
		userName.setHorizontalAlignment(SwingConstants.LEFT);
		userName.setFont(new Font("Serif", Font.PLAIN, 14));
		userName.setBounds(318, 51, 115, 37);

		contentPane.add(userName);

		JLabel paswd = new JLabel("PASSWORD");
		paswd.setBounds(318, 119, 115, 37);
		paswd.setFont(new Font("Serif", Font.PLAIN, 14));
		contentPane.add(paswd);

		unText = new JTextField();
		unText.setBounds(443, 53, 179, 37);
		contentPane.add(unText);
		unText.setColumns(10);

		paswdText = new JPasswordField();
		paswdText.setBounds(443, 119, 179, 37);
		contentPane.add(paswdText);

		login = new JButton("LOGIN");
		login.setBounds(332, 197, 101, 23);
		contentPane.add(login);
		login.addActionListener(this);

		signup = new JButton("SIGNUP");
		signup.setBounds(533, 197, 89, 23);
		contentPane.add(signup);
		signup.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Conn c1 = new Conn();
			String a = unText.getText();
			String b = new String(paswdText.getPassword());
			String q = "select * from account where user_name = '" + a + "' and password = '" + b + "'";
			ResultSet rs = c1.s.executeQuery(q);

			if (arg0.getSource() == login) {
				if (rs.next()) {
					setVisible(false);
				}
			}
			if(arg0.getSource()==signup) {
				new SignUp().setVisible(true);
				setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error: " + e);
		}

	}
}
