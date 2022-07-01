package librarysystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class LibrarySystemCustom extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarySystemCustom frame = new LibrarySystemCustom();
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
	public LibrarySystemCustom() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[] {50, 200};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(SystemColor.textHighlight);
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		contentPane.add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {30};
		gbl_topPanel.rowHeights = new int[] {30, 30, 30};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		topPanel.setLayout(gbl_topPanel);
		
		JLabel lblNewLabel = new JLabel("MIU - LMS");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		topPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0};
		gbl_panel_1.rowHeights = new int[]{0};
		gbl_panel_1.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
	}

}
