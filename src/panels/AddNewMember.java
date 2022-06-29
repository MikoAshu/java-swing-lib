package panels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AddNewMember {
	
	ControllerInterface controllerInterface = SystemController.INSTANCE;

	private JFrame frame;
	private JTextField id;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTextField cell;
	JButton status = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewMember window = new AddNewMember();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddNewMember() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 255));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Add new member");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId = id.getText();
	            String userFirstName = firstName.getText();
	            String userLastName = lastName.getText();
	            String userStreet = street.getText();
	            String userCity = city.getText();
	            String userState = state.getText();
	            String userZip = zip.getText();
	            String userCell = cell.getText();
	            
	            try {
					controllerInterface.addMember(userId, userFirstName, userLastName, userCell, userStreet, userCity, userState, userZip);
					status.setForeground(Color.GREEN);
					status.setText("Successfully member created!");
					clearTextFields();
				} catch (LibrarySystemException e1) {
					status.setForeground(Color.RED);
					status.setText(e1.getMessage());
					System.out.println(e1.getMessage());
				} 
	            
			}

			private void clearTextFields() {
				id.setText("");
				firstName.setText("");
				lastName.setText("");
				street.setText("");
				city.setText("");
				state.setText("");
				zip.setText("");
				cell.setText("");
				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(248, 248, 255));
		btnNewButton.setBounds(648, 483, 294, 43);
		frame.getContentPane().add(btnNewButton);
		
		id = new JTextField();
		id.setBounds(648, 22, 294, 35);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(648, 10, 45, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(648, 67, 96, 13);
		frame.getContentPane().add(lblFirstname);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		firstName.setBounds(648, 79, 294, 35);
		frame.getContentPane().add(firstName);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(648, 124, 96, 13);
		frame.getContentPane().add(lblLastname);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(648, 136, 294, 35);
		frame.getContentPane().add(lastName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Street");
		lblNewLabel_1_1.setBounds(648, 172, 45, 13);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		street = new JTextField();
		street.setColumns(10);
		street.setBounds(648, 184, 294, 35);
		frame.getContentPane().add(street);
		
		JLabel lblNewLabel_2_1 = new JLabel("City");
		lblNewLabel_2_1.setBounds(648, 221, 45, 13);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(648, 233, 294, 35);
		frame.getContentPane().add(city);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("State");
		lblNewLabel_1_1_1.setBounds(648, 278, 45, 13);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		state = new JTextField();
		state.setColumns(10);
		state.setBounds(648, 290, 294, 35);
		frame.getContentPane().add(state);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(648, 293, 294, 2);
		frame.getContentPane().add(separator_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Zip");
		lblNewLabel_2_1_1.setBounds(648, 326, 45, 13);
		frame.getContentPane().add(lblNewLabel_2_1_1);
		
		zip = new JTextField();
		zip.setColumns(10);
		zip.setBounds(648, 338, 294, 35);
		frame.getContentPane().add(zip);
		
		JSeparator separator_2_1_1 = new JSeparator();
		separator_2_1_1.setBounds(648, 339, 294, 2);
		frame.getContentPane().add(separator_2_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Cell");
		lblNewLabel_1_1_1_1.setBounds(648, 383, 45, 13);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		cell = new JTextField();
		cell.setColumns(10);
		cell.setBounds(648, 395, 294, 35);
		frame.getContentPane().add(cell);
		
		status.setBounds(557, 536, 491, 21);
		status.setOpaque(false);
		status.setContentAreaFilled(false);
		status.setBorderPainted(false);
		frame.getContentPane().add(status);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, -27, 482, 678);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AddNewMember.class.getResource("/images/member.png")));
		lblNewLabel_1.setBounds(95, 174, 300, 300);
		panel.add(lblNewLabel_1);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1094, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
}
