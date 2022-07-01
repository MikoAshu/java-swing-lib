package panels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class CheckoutRecord {

	private JTable table;
	private JFrame frame;
	private JTextField textField;
	private boolean tableDataSet;
	
	ControllerInterface sc = SystemController.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutRecord window = new CheckoutRecord();
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
	public CheckoutRecord() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 678, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter member ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(32, 10, 138, 29);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(197, 12, 244, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				        String memberID = textField.getText().trim();
			            if (memberID.length() == 0) {
			                System.out.println("Member ID should not be empty");
			            } else {
			                try {
			                    List<String[]> records = sc.getMemberCheckoutEntries(memberID);
			                    if (records.size() == 0)
			                    	System.out.println("no books");
			                    else {
			                        if (!tableDataSet) {
			                            DefaultTableModel tableModel = new DefaultTableModel();
			                            tableModel.addColumn("Member Id");
			                            tableModel.addColumn("Book");
			                            tableModel.addColumn("Copy Number");
			                            tableModel.addColumn("Checkout Date");
			                            tableModel.addColumn("Due Date");



			                            table = new JTable(tableModel);

			                            for (String[] rec : records) {
			                                tableModel.addRow(rec);
			                            }
			                            tableModel.addRow(new String[]{});
			                            tableModel.addRow(new String[]{});
			                            tableModel.addRow(new String[]{});
			                            tableModel.addRow(new String[]{});
			                            tableModel.addRow(new String[]{});
			                            tableModel.addRow(new String[]{});

			                            table.setPreferredScrollableViewportSize(table.getPreferredSize());
			                            table.setFillsViewportHeight(true);
			                           

			                           
			                            tableDataSet = true;
			                        } else {
			                            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			                            tableModel.setRowCount(0);

			                            for (String[] rec : records) {
			                                tableModel.addRow(rec);
			                            }

			                            table.setModel(tableModel);
			                        }
			                        System.out.println("Retrieved successfully");
			                    }
			                } catch (LibrarySystemException ex) {
			                	System.out.println("Error" + ex.getMessage());
			                }
			            }
			}
		});
		search.setBackground(new Color(135, 206, 235));
		search.setBounds(482, 12, 112, 29);
		frame.getContentPane().add(search);
	}
}
