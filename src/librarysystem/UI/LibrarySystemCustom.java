package librarysystem.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;
import librarysystem.*;
import panels.AddNewMember;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.UIManager;

public class LibrarySystemCustom extends JFrame {
	ControllerInterface ci = SystemController.getInstance();
	public static LibrarySystemCustom INSTANCE = new LibrarySystemCustom();
	
	private JPanel contentPane;
	JPanel menuPanel;
	JPanel topPanel;
	JPanel containerPanel;
	
	GridBagLayout gbl_menuPanel;
	GridBagConstraints gbc_menuPanel;
	GridBagLayout gbl_topPanel;
	GridBagConstraints gbc_topPanel;
	JLabel lblMainLabel;
	
    Auth auth;
    
	private static LibrarySystemCustom frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    frame = INSTANCE;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public LibrarySystemCustom() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[] {50, 310, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		topPanel = new JPanel();
		containerPanel = new JPanel();
		
		topPanel.setBackground(UIManager.getColor("Desktop.background"));
		gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		
		
		gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] {30};
		gbl_topPanel.rowHeights = new int[] {40, 40, 40};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		topPanel.setLayout(gbl_topPanel);
		
		lblMainLabel = new JLabel("MIU - LMS");
		lblMainLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblMainLabel.setForeground(SystemColor.activeCaption);
		lblMainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		topPanel.add(lblMainLabel, gbc_lblNewLabel);
		
		menuPanel = new JPanel();
		gbc_menuPanel = new GridBagConstraints();
		gbc_menuPanel.insets = new Insets(0, 0, 5, 0);
		gbc_menuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuPanel.gridx = 0;
		gbc_menuPanel.gridy = 1;
		
		
		gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[] {30, 30, 30, 30, 30, 40, 30};
		gbl_menuPanel.rowHeights = new int[] {40, 40, 40, 30, 30, 0};
		gbl_menuPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_menuPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		menuPanel.setLayout(gbl_menuPanel);
		
		JButton btnAddBookCopy = new JButton("Add Book Copy");
		btnAddBookCopy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        BookCopyAdd abcp = new BookCopyAdd();
		        JPanel addBookCopyPanel = abcp.getMainPanel();
		        contentPane.remove(menuPanel);
		        addBookCopyPanel.setLayout(gbl_menuPanel);
				containerPanel.removeAll();
		        containerPanel.add(addBookCopyPanel);
		        contentPane.add(containerPanel, gbc_menuPanel);
		        lblMainLabel.setText("Add Book Copy");
		        frame.invalidate();
		        frame.validate();
		        frame.repaint();
			}
		});
		
		GridBagConstraints gbc_btnAddBook = new GridBagConstraints();
		gbc_btnAddBook.gridheight = 2;
		gbc_btnAddBook.fill = GridBagConstraints.BOTH;
		gbc_btnAddBook.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddBook.gridx = 2;
		gbc_btnAddBook.gridy = 0;
		menuPanel.add(btnAddBookCopy, gbc_btnAddBook);
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddNewMember();
			}
		});
		GridBagConstraints gbc_btnAddMember = new GridBagConstraints();
		gbc_btnAddMember.gridheight = 2;
		gbc_btnAddMember.fill = GridBagConstraints.BOTH;
		gbc_btnAddMember.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddMember.gridx = 3;
		gbc_btnAddMember.gridy = 0;
		menuPanel.add(btnAddMember, gbc_btnAddMember);
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddNewBook pan = new AddNewBook();
		        JPanel jpanel = pan.getMainPanel();
		        contentPane.remove(menuPanel);
		        jpanel.setLayout(gbl_menuPanel);
				containerPanel.removeAll();
		        containerPanel.add(jpanel);
		        
		        contentPane.add(containerPanel, gbc_menuPanel);
		        lblMainLabel.setText("Add Book");
		        frame.invalidate();
		        frame.validate();
		        frame.repaint();
			}
		});
		GridBagConstraints gbc_btnAllBooks = new GridBagConstraints();
		gbc_btnAllBooks.gridheight = 2;
		gbc_btnAllBooks.fill = GridBagConstraints.BOTH;
		gbc_btnAllBooks.insets = new Insets(0, 0, 5, 5);
		gbc_btnAllBooks.gridx = 4;
		gbc_btnAllBooks.gridy = 0;
		menuPanel.add(btnAddBook, gbc_btnAllBooks);
		
		JButton btnCkoutBook = new JButton("Checkout Book");
		btnCkoutBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckoutBookPanel pan = new CheckoutBookPanel();
		        JPanel jpanel = pan.getMainPanel();
		        contentPane.remove(menuPanel);
		        jpanel.setLayout(gbl_menuPanel);
				containerPanel.removeAll();
				containerPanel.add(jpanel);
				contentPane.add(containerPanel, gbc_menuPanel);
		        lblMainLabel.setText("Checkout Book");
		        frame.invalidate();
		        frame.validate();
		        frame.repaint();
			}
		});
		
		GridBagConstraints gbc_btnCkoutBook = new GridBagConstraints();
		gbc_btnCkoutBook.gridheight = 2;
		gbc_btnCkoutBook.fill = GridBagConstraints.BOTH;
		gbc_btnCkoutBook.insets = new Insets(0, 0, 0, 5);
		gbc_btnCkoutBook.gridx = 2;
		gbc_btnCkoutBook.gridy = 2;
		menuPanel.add(btnCkoutBook, gbc_btnCkoutBook);
		
		JButton btnVwCheckoutRecord = new JButton("View Checkout Record");
		btnVwCheckoutRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckoutRecordPanel pan = new CheckoutRecordPanel();
		        JPanel jpanel = pan.getMainPanel();
		        contentPane.remove(menuPanel);
		        jpanel.setLayout(gbl_menuPanel);
				containerPanel.removeAll();
		        containerPanel.add(jpanel);
		        contentPane.add(containerPanel, gbc_menuPanel);
		        
		        lblMainLabel.setText("View Checkout Record");
		        frame.invalidate();
		        frame.validate();
		        frame.repaint();
			}
		});
		GridBagConstraints gbc_btnVwCheckoutRecord = new GridBagConstraints();
		gbc_btnVwCheckoutRecord.gridheight = 2;
		gbc_btnVwCheckoutRecord.fill = GridBagConstraints.BOTH;
		gbc_btnVwCheckoutRecord.insets = new Insets(0, 0, 0, 5);
		gbc_btnVwCheckoutRecord.gridx = 3;
		gbc_btnVwCheckoutRecord.gridy = 2;
		menuPanel.add(btnVwCheckoutRecord, gbc_btnVwCheckoutRecord);
		
		
		JButton btnAllBooks = new JButton("All Books");
		btnAllBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AllBookIdsWindow pan = new AllBookIdsWindow();
		        JPanel jpanel = pan.getMainPanel();
		        contentPane.remove(menuPanel);
		        jpanel.setLayout(gbl_menuPanel);
				containerPanel.removeAll();
		        containerPanel.add(jpanel);
				List<String> ids = ci.allBookIds();
				Collections.sort(ids);
				StringBuilder sb = new StringBuilder();
				for(String s: ids) {
					sb.append(s).append("\n");
				}
				pan.setData(sb.toString());

		        contentPane.add(containerPanel, gbc_menuPanel);
		        lblMainLabel.setText("All Books");
		        frame.invalidate();
		        frame.validate();
		        frame.repaint();
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.gridheight = 2;
		gbc_btnExit.fill = GridBagConstraints.BOTH;
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 4;
		gbc_btnExit.gridy = 2;
		menuPanel.add(btnAllBooks, gbc_btnExit);
//		---------------------------------------------
		JButton overdue_books = new JButton("Overdue Books");
		overdue_books.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CheckedOutBook pan = new CheckedOutBook();
				JPanel jpanel = pan.getMainPanel();
				contentPane.remove(menuPanel);
				jpanel.setLayout(gbl_menuPanel);
				containerPanel.removeAll();
				containerPanel.add(jpanel);

				contentPane.add(containerPanel, gbc_menuPanel);
				lblMainLabel.setText("Overdue Books");
				frame.invalidate();
				frame.validate();
				frame.repaint();
			}
		});
		GridBagConstraints gbc_btnOverdue = new GridBagConstraints();
		gbc_btnOverdue.gridheight = 2;
		gbc_btnOverdue.fill = GridBagConstraints.BOTH;
		gbc_btnOverdue.insets = new Insets(0, 0, 0, 5);
		gbc_btnOverdue.gridx = 2;
		gbc_btnOverdue.gridy = 4;
		menuPanel.add(overdue_books, gbc_btnOverdue);

//		---------------------------------------------
		JButton btnLogout = new JButton("Exit");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					System.exit(EXIT_ON_CLOSE);
			}
		});
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.gridheight = 2;
		gbc_btnLogout.fill = GridBagConstraints.BOTH;
		gbc_btnLogout.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogout.gridx = 3;
		gbc_btnLogout.gridy = 4;
		menuPanel.add(btnLogout, gbc_btnLogout);
		
		var role = SystemController.getCurrentAuth();
		
		if (role == Auth.LIBRARIAN) {
			btnAddBookCopy.setEnabled(false);
			btnAddMember.setEnabled(false);
			btnAddBook.setEnabled(false);
			overdue_books.setEnabled(false);
		} else if (role == Auth.ADMIN) {
			btnCkoutBook.setEnabled(false);
			btnVwCheckoutRecord.setEnabled(false);
		} 
		
		renderMainPanel();
	}

	public void renderMainPanel() {
		if (frame != null) {
			contentPane.removeAll();
		}
			contentPane.add(menuPanel, gbc_menuPanel);
			contentPane.add(topPanel, gbc_topPanel);
		
		if (frame != null) {
			lblMainLabel.setText("MIU - LMS");
	        frame.revalidate();
	        frame.repaint();
		}
	}

}
