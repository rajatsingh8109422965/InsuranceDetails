package com.insurance.info;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.insurance.info.config.DataSourceConfig;
import com.insurance.info.models.CustomerDetails;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class InsuranceForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int xMouse;
	private int yMouse;

	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField emailTextField;
	private JTextField mobileNoTextField;

	public InsuranceForm(CustomerDetails customer) {
		setupFrame();

		makePaneDraggable();
		setupExitAndMinimizeButton();

		JLabel firstNameLabel = new JLabel("NAME");
		firstNameLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		firstNameLabel.setBounds(30, 32, 99, 29);
		contentPane.add(firstNameLabel);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		nameTextField.setBounds(30, 62, 322, 24);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		customer.setName(nameTextField.getText());

		JLabel addressLabel = new JLabel("ADDRESS");
		addressLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		addressLabel.setBounds(30, 97, 99, 29);
		contentPane.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		addressTextField.setColumns(10);
		addressTextField.setBounds(30, 125, 322, 24);
		contentPane.add(addressTextField);
		customer.setAddress(addressTextField.getText());

		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		emailTextField.setColumns(10);
		emailTextField.setBounds(30, 192, 322, 24);
		contentPane.add(emailTextField);
		customer.setEmail(emailTextField.getText());

		JLabel emailLabel = new JLabel("EMAIL");
		emailLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		emailLabel.setBounds(30, 160, 99, 29);
		contentPane.add(emailLabel);

		JLabel periodOfInsuranceLabel = new JLabel("PERIOD OF INSURANCE");
		periodOfInsuranceLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		periodOfInsuranceLabel.setBounds(222, 227, 146, 29);
		contentPane.add(periodOfInsuranceLabel);

		JLabel mobileNoLabel = new JLabel("Mobile No.");
		mobileNoLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		mobileNoLabel.setBounds(222, 277, 99, 29);
		contentPane.add(mobileNoLabel);

		mobileNoTextField = new JTextField();
		mobileNoTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		mobileNoTextField.setColumns(10);
		mobileNoTextField.setBounds(308, 272, 195, 24);
		contentPane.add(mobileNoTextField);
		customer.setMobileNo(mobileNoTextField.getText());
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(376, 233, 132, 21);
		contentPane.add(comboBox);
		
		JLabel dateOfIssue = new JLabel("DATE OF ISSUE");
		dateOfIssue.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		dateOfIssue.setBounds(29, 227, 99, 29);
		contentPane.add(dateOfIssue);
		
		JLabel dateOfExpiry = new JLabel("DATE OF EXPIRY");
		dateOfExpiry.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		dateOfExpiry.setBounds(30, 277, 132, 29);
		contentPane.add(dateOfExpiry);
		
		Button submitButton = new Button("SUBMIT");
		submitButton.setFont(new Font("Ebrima", Font.BOLD, 16));
		submitButton.setForeground(new Color(255, 255, 255));
		submitButton.setBackground(new Color(0, 0, 0));
		submitButton.setBounds(107, 345, 146, 41);
		contentPane.add(submitButton);
		
		
		
		UtilDateModel model = new UtilDateModel();
		model.setDate(1990, 8, 24);
		JDatePanelImpl datePanel = new JDatePanelImpl(model,new Properties());
		
		insertDetails(customer);

	}

	private void insertDetails(CustomerDetails customer) {
		Connection connection = DataSourceConfig.getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement("insert into customer_details (name,mobileno,address,email,date_of_issue,period_of_insurance,date_of_expiry,type_of_insurance) values (?,?,?,?,?,?,?,?)");
		ps.setString(1, customer.getName());
		ps.setString(2, customer.getMobileNo());
		ps.setString(3, customer.getAddress());
		ps.setString(4, customer.getEmail());
		ps.setString(5, customer.getDateofIssue());
		ps.setString(6, customer.getPeriodOfInsurance());
		ps.setString(7, customer.getDateOfExpiryDate());
		ps.setString(8, customer.getTypeOfInsurance());
		
		System.out.println(ps.execute());
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void setupFrame() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setBounds(100, 100, 651, 504);
		contentPane.setLayout(null);
		setVisible(true);
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
