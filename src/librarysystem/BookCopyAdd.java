package librarysystem;

import business.Book;
import business.ControllerInterface;
import business.SystemController;

import java.awt.*;

import javax.swing.*;

public class BookCopyAdd extends JFrame {
	private final ControllerInterface systemController;
	private final JPanel mainPanel;
	private JPanel topPanel;
	private JPanel outerMiddle;


	private JTextField isbnText;

	public BookCopyAdd() {
		systemController = new SystemController();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineOuterMiddle();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(outerMiddle, BorderLayout.CENTER);
		getContentPane().add(mainPanel);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void clearData() {
		isbnText.setText("");
	}

	public void defineTopPanel() {
		topPanel = new JPanel();
		JLabel AddBookLabel = new JLabel("Add Book Copy");
		Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(AddBookLabel);
	}

	public void defineOuterMiddle() {
		outerMiddle = new JPanel();
		outerMiddle.setLayout(new BorderLayout());

		//set up left and right panels
		JPanel middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		middlePanel.setLayout(fl);
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		JLabel bookIsbn = new JLabel("Enter ISBN");

		isbnText = new JTextField(10);

		leftPanel.add(bookIsbn);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));


		rightPanel.add(isbnText);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));

		middlePanel.add(leftPanel);
		middlePanel.add(rightPanel);
		outerMiddle.add(middlePanel, BorderLayout.NORTH);

		//add button at bottom
		JButton addBookButton = new JButton("Add Book Copy");
		addBookButton.setBackground(Color.PINK.darker());
		addBookButton.setForeground(Color.black);
		attachButtonListener(addBookButton);
		JPanel addBookButtonPanel = new JPanel();
		addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addBookButtonPanel.add(addBookButton);
		outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookCopyAdd frame = new BookCopyAdd();
					frame.setVisible(true);
					frame.setSize(new Dimension(600, 450));
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
//	public BookCopyAdd() {
//		systemController = new SystemController();
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		getContentPane().setLayout(new BorderLayout(0, 0));
//
//		JPanel panel = new JPanel();
//		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
//		flowLayout.setAlignment(FlowLayout.LEFT);
//		flowLayout.setAlignOnBaseline(true);
//		getContentPane().add(panel, BorderLayout.NORTH);
//
//		JLabel lblNewLabel = new JLabel("Add Book Copy");
//		panel.add(lblNewLabel);
//
//		bookISBN = new JTextField();
//		getContentPane().add(bookISBN, BorderLayout.CENTER);
//		bookISBN.setColumns(10);
//
//		JButton addBookCopy = new JButton("Add Copy");
//		getContentPane().add(addBookCopy, BorderLayout.EAST);
//
//		attachButtonListener(addBookCopy);
//
//	}
	
	private void attachButtonListener(JButton a) {
		a.addActionListener(e -> {
			String isbn = isbnText.getText();
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
