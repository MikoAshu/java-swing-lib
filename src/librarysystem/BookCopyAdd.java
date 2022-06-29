package librarysystem;

import business.Book;
import business.SystemController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BookCopyAdd extends JFrame {
	SystemController systemController;
	private JTextField bookISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookCopyAdd frame = new BookCopyAdd();
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
	public BookCopyAdd() {
		systemController = new SystemController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignOnBaseline(true);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Add Book Copy");
		panel.add(lblNewLabel);
		
		bookISBN = new JTextField();
		getContentPane().add(bookISBN, BorderLayout.CENTER);
		bookISBN.setColumns(10);
		
		JButton addBookCopy = new JButton("Add Copy");
		getContentPane().add(addBookCopy, BorderLayout.EAST);
		
		attachButtonListener(addBookCopy);
		
	}
	
	private void attachButtonListener(JButton a) {
		a.addActionListener(e -> {
			String isbn = bookISBN.getText();
			if (isbn == null || isbn.equals("")) {
				System.out.println("Please enter an ISBN number");
			} else {
				Book b = systemController.getBook(isbn);
				if(b != null) {
					b.addCopy();
					systemController.updateBook(b);
					System.out.println("The book has been updated");
				} else {
					System.out.println("Book doesn't exist");
				}
			}
		});
	}
	
	

}
