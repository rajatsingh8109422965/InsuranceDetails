package com.insurance.info;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.insurance.info.config.Conn;
import com.insurance.info.signup.SignUp;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import java.awt.Panel;

public class InsuranceApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static InsuranceApplication frame;
	private JPanel contentPane;
	private JTextField unText;
	private JPasswordField paswdText;
	private JLabel signUpButton; 
	private int xMouse;
	private int yMouse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InsuranceApplication();
					frame.setUndecorated(true);
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
		setBounds(100, 100, 682, 418);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
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
				
				frame.setLocation(x - xMouse, y-yMouse);
			}
		});

		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel userName = new JLabel("USERNAME");
		userName.setHorizontalAlignment(SwingConstants.LEFT);
		userName.setFont(new Font("Serif", Font.PLAIN, 14));
		userName.setBounds(337, 171, 87, 21);

		contentPane.add(userName);

		JLabel paswd = new JLabel("PASSWORD");
		paswd.setBounds(337, 217, 87, 29);
		paswd.setFont(new Font("Serif", Font.PLAIN, 14));
		contentPane.add(paswd);

		unText = new JTextField();
		unText.setBounds(434, 173, 197, 21);
		contentPane.add(unText);
		unText.setColumns(10);

		paswdText = new JPasswordField();
		paswdText.setBounds(434, 223, 197, 21);
		contentPane.add(paswdText);

		Button login = new Button("LOGIN");
		login.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		login.setForeground(Color.WHITE);
		login.setBackground(new Color(0, 0, 0));
		login.addActionListener(new ActionListener() {
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
					if (arg0.getSource() == signUpButton) {
						new SignUp().setVisible(true);
						setVisible(false);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("error: " + e);
				}

			}
		});
		login.setBounds(447, 275, 132, 37);
		contentPane.add(login);

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(39, 39, 132));
		leftPanel.setBounds(0, -10, 327, 440);
		contentPane.add(leftPanel);
		
		JLabel leftImage = new JLabel("");
		leftImage.setIcon(new ImageIcon(InsuranceApplication.class.getResource("/com/insurance/resources/Login_LeftImage.jpg")));
		leftPanel.add(leftImage);

		JLabel exitButton = new JLabel("X");
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		exitButton.setBounds(644, 0, 18, 29);
		contentPane.add(exitButton);

		JLabel minimizeButton = new JLabel("_");
		minimizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
				
			}
		});
		minimizeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		minimizeButton.setBounds(613, 0, 18, 21);
		contentPane.add(minimizeButton);
		
		JLabel loginIcon = new JLabel("");
		loginIcon.setIcon(new ImageIcon(InsuranceApplication.class.getResource("/com/insurance/resources/Login_Icon.png")));
		loginIcon.setBounds(458, 46, 106, 104);
		contentPane.add(loginIcon);
		
		signUpButton = new JLabel("New User? Click Here");
		signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		signUpButton.setBounds(447, 334, 117, 14);
		contentPane.add(signUpButton);
	}
}
