package librarysystem.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import librarysystem.AppMsg;
import librarysystem.LibrarySystem;
import librarysystem.Util;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindowCustom extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserName;
	private JTextField tfPassword;
	
	LibrarySystem librarySystem = LibrarySystem.INSTANCE;
	private final ControllerInterface systemControler = SystemController.getInstance();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindowCustom frame = new LoginWindowCustom();
					frame.setVisible(true);
					Util.centerFrameOnDesktop(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindowCustom() {
		// -------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		// ------------- First Panel
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);
		panel.setToolTipText("Library Management System");
		
		panel.setForeground(Color.WHITE);
		panel.setBackground(UIManager.getColor("Desktop.background"));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{98};
		gbl_panel.rowHeights = new int[]{150, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblLbSys = new JLabel("Library Management System");
		lblLbSys.setHorizontalAlignment(SwingConstants.CENTER);
		lblLbSys.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLbSys = new GridBagConstraints();
		gbc_lblLbSys.fill = GridBagConstraints.BOTH;
		gbc_lblLbSys.insets = new Insets(0, 0, 5, 0);
		gbc_lblLbSys.gridx = 0;
		gbc_lblLbSys.gridy = 0;
		panel.add(lblLbSys, gbc_lblLbSys);
		
		JLabel lblLibraryLabelDetail = new JLabel("Register, Checkout with ease!");
		lblLibraryLabelDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLibraryLabelDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryLabelDetail.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblLibraryLabelDetail.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblLibraryLabelDetail = new GridBagConstraints();
		gbc_lblLibraryLabelDetail.fill = GridBagConstraints.BOTH;
		gbc_lblLibraryLabelDetail.insets = new Insets(0, 0, 5, 0);
		gbc_lblLibraryLabelDetail.gridx = 0;
		gbc_lblLibraryLabelDetail.gridy = 0;
		gbc_lblLibraryLabelDetail.gridx = 0;
		gbc_lblLibraryLabelDetail.gridy = 1;
		panel.add(lblLibraryLabelDetail, gbc_lblLibraryLabelDetail);
		

		
		// -------- Second panel
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 2};
		gbl_panel_1.rowHeights = new int[] {0, 0, 0, 50};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.gridheight = 1;

		gbc_panel_2.insets = new Insets(10, 10, 25, 10);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setSize(36, 25);
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblLibraryLabelDetail.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblLogin );
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 10, 5, 10);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_1.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{150};
		gbl_panel_3.rowHeights = new int[]{26, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel = new JLabel("Username");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		
		tfUserName = new JTextField(10);
		tfUserName.setBackground(SystemColor.window);
		GridBagConstraints gbc_tfUserName = new GridBagConstraints();
		gbc_tfUserName.insets = new Insets(0, 0, 5, 0);
		gbc_tfUserName.ipadx = 20;
		gbc_tfUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfUserName.gridx = 0;
		gbc_tfUserName.gridy = 1;
		panel_3.add(tfUserName, gbc_tfUserName);
		tfUserName.setColumns(12);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		tfPassword = new JPasswordField(10);
		tfPassword.setBackground(SystemColor.window);
		GridBagConstraints gbc_tfPassword = new GridBagConstraints();
		gbc_tfPassword.insets = new Insets(0, 0, 5, 0);
		gbc_tfPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPassword.gridx = 0;
		gbc_tfPassword.gridy = 3;
		panel_3.add(tfPassword, gbc_tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 4;
		addLoginButtonListener(btnLogin);
		panel_3.add(btnLogin, gbc_btnLogin);
		
		
	}

	private void addLoginButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			
            String userSt = tfUserName.getText().trim();
            String passwordSt = tfPassword.getText().trim();
            
            if (passwordSt.length() == 0) {
            	librarySystem.displayMessage("Username is required!", AppMsg.ERROR);
            }
            else if (userSt.length() == 0) {
            	librarySystem.displayMessage("Password is required!", AppMsg.ERROR);
            } else {
                try {
            		systemControler.login(userSt, passwordSt);
            		
            		LibrarySystemCustom.INSTANCE.setVisible(true);
        			this.setVisible(false);
        			Util.centerFrameOnDesktop(LibrarySystemCustom.INSTANCE);
        			
                    librarySystem.displayMessage("Login Successful", AppMsg.SUCCESS);

                    librarySystem.repaint();
                } catch (LoginException ex) {
                	librarySystem.displayMessage(ex.getMessage(), AppMsg.ERROR);
                }
            }
				
		});
	}
	

}
