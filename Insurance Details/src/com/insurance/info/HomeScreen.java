package com.insurance.info;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.insurance.info.config.DataSourceConfig;
import com.insurance.info.models.CustomerDetails;

public class HomeScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int xMouse;
	private int yMouse;

	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		setUndecorated(true);

		// ***** FRAME *****
		setupFrame();
		makePaneDraggable();
		setupExitAndMinimizeButton();

		// *************** Select Type Label ****************
		JLabel selectLabel = new JLabel("Select Type of Insurance");
		selectLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		selectLabel.setBounds(220, 25, 193, 50);
		contentPane.add(selectLabel);

		// *************** Motor Button ****************
		JLabel motorButton = new JLabel("");
		motorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				CustomerDetails customer = new CustomerDetails();
				customer.setTypeOfInsurance("Motor");
				new InsuranceForm(customer);
				setVisible(false);
			}
		});
		motorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		motorButton.setIcon(new ImageIcon(HomeScreen.class.getResource("/com/insurance/resources/motor_icon.jpg")));
		motorButton.setBounds(38, 103, 157, 148);
		contentPane.add(motorButton);

		JLabel motorLabel = new JLabel("MOTOR");
		motorLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		motorLabel.setBounds(81, 262, 70, 34);
		contentPane.add(motorLabel);

		// *************** Health Button ****************
		JLabel healthButton = new JLabel("");
		healthButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				CustomerDetails customer = new CustomerDetails();
				customer.setTypeOfInsurance("Health");
				new InsuranceForm(customer);
				setVisible(false);

			}
		});
		healthButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		healthButton.setIcon(new ImageIcon(HomeScreen.class.getResource("/com/insurance/resources/health_icon.png")));
		healthButton.setBounds(235, 103, 157, 148);
		contentPane.add(healthButton);

		JLabel healthLabel = new JLabel("HEALTH");
		healthLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		healthLabel.setBounds(278, 262, 70, 34);
		contentPane.add(healthLabel);

		// *************** Non-Motor Button ****************
		JLabel nonMotorButton = new JLabel("");
		nonMotorButton.setBounds(434, 103, 157, 148);
		contentPane.add(nonMotorButton);

		JLabel nonMotorLabel = new JLabel("NON-MOTOR");
		nonMotorLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		nonMotorLabel.setBounds(457, 262, 117, 34);
		contentPane.add(nonMotorLabel);

		Button generateReportButton = new Button("Generate Report");
		generateReportButton.setFont(new Font("Dialog", Font.BOLD, 18));
		generateReportButton.setBounds(208, 399, 205, 75);
		contentPane.add(generateReportButton);

		JLabel orLabel = new JLabel("OR");
		orLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 18));
		orLabel.setBounds(302, 307, 51, 50);
		contentPane.add(orLabel);

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

	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 502);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}
}
