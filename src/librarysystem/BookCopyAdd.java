package librarysystem;

import business.Book;
import business.ControllerInterface;
import business.SystemController;
import librarysystem.UI.LibrarySystemCustom;

import java.awt.*;

import javax.swing.*;

public class BookCopyAdd {
	private final ControllerInterface systemController;
    LibrarySystem librarySystem = LibrarySystem.INSTANCE;
    LibrarySystemCustom librarySystemCustom = LibrarySystemCustom.INSTANCE;

    private final JPanel mainPanel;
	private JPanel topPanel;
	private JPanel outerMiddle;


	private JTextField isbnText;

	public BookCopyAdd() {
		systemController =  SystemController.getInstance();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineOuterMiddle();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(outerMiddle, BorderLayout.CENTER);
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void clearData() {
		isbnText.setText("");
	}

	public void defineTopPanel() {
		topPanel = new JPanel();
//		JLabel AddBookLabel = new JLabel("Add Book Copy");
//		Util.adjustLabelFont(AddBookLabel, Util.DARK_BLUE, true);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		topPanel.add(AddBookLabel);
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
		JButton addBookButton = new JButton("Add");
		addBookButton.setBackground(Color.PINK.darker());
		addBookButton.setForeground(Color.black);
		attachButtonListener(addBookButton);
		
		JButton addBackToMainBtn = new JButton("Back to Main");
		addBackToMainBtn.setBackground(Color.PINK.darker());
		addBackToMainBtn.setForeground(Color.black);
		backToMainListener(addBackToMainBtn);
		
		JPanel addBookButtonPanel = new JPanel();
		addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addBookButtonPanel.add(addBookButton);
		
		JPanel addBackButtonPanel = new JPanel();
		addBackButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addBackButtonPanel.add(addBackToMainBtn);
		
		outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);
		outerMiddle.add(addBackButtonPanel, BorderLayout.PAGE_END);


	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BookCopyAdd frame = new BookCopyAdd();
//					frame.setVisible(true);
//					frame.setSize(new Dimension(600, 450));
//					Util.centerFrameOnDesktop(frame);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
				librarySystem.displayMessage("Please enter an ISBN number", AppMsg.ERROR);
			} else {
				Book b = systemController.getBook(isbn);
				if(b != null) {
					b.addCopy();
					systemController.updateBook(b);
					librarySystem.displayMessage("The book has been updated", AppMsg.SUCCESS);
				} else {
					librarySystem.displayMessage("Book doesn't exist", AppMsg.INFO);
				}
			}
		});
	}
	
	
	private void backToMainListener(JButton a) {
		a.addActionListener(e -> {			
			LibrarySystemCustom.INSTANCE.renderMainPanel();

		});
	}

}
