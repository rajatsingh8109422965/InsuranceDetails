package com.insurance.info;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.insurance.info.config.DataSourceConfig;
import com.insurance.info.dao.InsuranceDao;
import com.insurance.info.models.CustomerDetails;
import com.toedter.calendar.JDateChooser;

public class InsuranceForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int xMouse;
	private int yMouse;

	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField emailTextField;
	private JTextField mobileNoTextField;
	private JTextField netPremiumTextField;
	private JTextField totalPremiumTextField;
	private JTextField agentNameTextField;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;

	public InsuranceForm(CustomerDetails customer) {
		setUndecorated(true);
		setupFrame();

		makePaneDraggable();
		setupExitAndMinimizeButton();

		JLabel firstNameLabel = new JLabel("CUSTOMER NAME");
		firstNameLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		firstNameLabel.setBounds(30, 32, 146, 29);
		contentPane.add(firstNameLabel);

		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextField.setBounds(30, 62, 322, 24);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("ADDRESS");
		addressLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		addressLabel.setBounds(30, 97, 99, 29);
		contentPane.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addressTextField.setColumns(10);
		addressTextField.setBounds(30, 125, 322, 24);
		contentPane.add(addressTextField);

		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailTextField.setColumns(10);
		emailTextField.setBounds(30, 192, 322, 24);
		contentPane.add(emailTextField);

		JLabel emailLabel = new JLabel("EMAIL");
		emailLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		emailLabel.setBounds(30, 160, 99, 29);
		contentPane.add(emailLabel);

		JLabel periodOfInsuranceLabel = new JLabel("PERIOD OF INSURANCE");
		periodOfInsuranceLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		periodOfInsuranceLabel.setBounds(292, 227, 146, 29);
		contentPane.add(periodOfInsuranceLabel);

		JLabel mobileNoLabel = new JLabel("Mobile No.");
		mobileNoLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		mobileNoLabel.setBounds(292, 277, 158, 29);
		contentPane.add(mobileNoLabel);

		mobileNoTextField = new JTextField();
		mobileNoTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mobileNoTextField.setColumns(10);
		mobileNoTextField.setBounds(375, 277, 200, 29);
		contentPane.add(mobileNoTextField);

		String[] strArr = new String[] { "1 year", "3 months", "6 months", "9 months", "3 year", "5 year" };
		comboBox = new JComboBox(strArr);
		comboBox.setBounds(448, 227, 132, 29);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(comboBox);

		JLabel dateOfIssue = new JLabel("DATE OF ISSUE");
		dateOfIssue.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		dateOfIssue.setBounds(30, 227, 99, 29);
		contentPane.add(dateOfIssue);

		JLabel dateOfExpiry = new JLabel("DATE OF EXPIRY");
		dateOfExpiry.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		dateOfExpiry.setBounds(30, 277, 132, 29);
		contentPane.add(dateOfExpiry);

		Button submitButton = new Button("SUBMIT");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDetails(customer);
			}
		});
		submitButton.setFont(new Font("Ebrima", Font.BOLD, 16));
		submitButton.setForeground(new Color(255, 255, 255));
		submitButton.setBackground(new Color(0, 0, 0));
		submitButton.setBounds(107, 406, 146, 41);
		contentPane.add(submitButton);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(163, 227, 119, 29);
		JTextField doiField = new JTextField(15);
		doiField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				JDateChooser chooser = (JDateChooser) e.getSource();
				doiField.setText(sdf.format(chooser.getDate()));
				try {
					customer.setDateofIssue(new Date(sdf.parse(doiField.getText()).getTime()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		contentPane.add(dateChooser);

		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(163, 277, 119, 29);
		JTextField doeField = new JTextField(15);
		doeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				JDateChooser chooser = (JDateChooser) e.getSource();
				doeField.setText(sdf.format(chooser.getDate()));
				try {
					customer.setDateOfExpiryDate(new Date(sdf.parse(doeField.getText()).getTime()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		contentPane.add(dateChooser_1);

		JLabel netpremium = new JLabel("NET PREMIUM");
		netpremium.setBounds(30, 317, 99, 29);
		netpremium.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(netpremium);

		netPremiumTextField = new JTextField();
		netPremiumTextField.setBounds(163, 317, 109, 29);
		netPremiumTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(netPremiumTextField);
		netPremiumTextField.setColumns(10);

		JLabel gst = new JLabel("GST %");
		gst.setBounds(292, 317, 45, 29);
		gst.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(gst);

		comboBox_1 = new JComboBox(new String[] { "12%", "18%", "28%" });
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setBounds(348, 317, 99, 29);
		contentPane.add(comboBox_1);

		JLabel totalAmount = new JLabel("TOTAL AMOUNT");
		totalAmount.setBounds(30, 367, 114, 14);
		totalAmount.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(totalAmount);

		totalPremiumTextField = new JTextField();
		totalPremiumTextField.setBounds(163, 360, 109, 29);
		totalPremiumTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(totalPremiumTextField);
		totalPremiumTextField.setColumns(10);

		JLabel agentName = new JLabel("AGENT NAME");
		agentName.setBounds(291, 364, 107, 21);
		agentName.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(agentName);

		agentNameTextField = new JTextField();
		agentNameTextField.setBounds(389, 360, 186, 29);
		agentNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(agentNameTextField);
		agentNameTextField.setColumns(10);

	}

	private void insertDetails(CustomerDetails customer) {
		customer.setName(nameTextField.getText());
		customer.setAddress(addressTextField.getText());
		customer.setEmail(emailTextField.getText());
		customer.setMobileNo(mobileNoTextField.getText());
		customer.setAgentName(agentNameTextField.getText());
		customer.setGst(comboBox_1.getSelectedItem().toString());
		customer.setPeriodOfInsurance(comboBox.getSelectedItem().toString());

		try {
			customer.setNetPremium(Double.parseDouble(netPremiumTextField.getText()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		try {
			customer.setTotalPremium(Double.parseDouble(totalPremiumTextField.getText()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		System.out.println(InsuranceDao.insertCustomerDetails(customer));

	}

	private void setupFrame() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
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
