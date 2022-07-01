package librarysystem;

import business.Book;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.UI.LibrarySystemCustom;

import java.awt.*;

import javax.swing.*;

public class BookCopyAdd extends JFrame {
	RuleSet ruleSet;
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
		getContentPane().add(mainPanel);

		ruleSet = new BookCopyAddRuleSet();
	}

	public String getIsbnValue() {
		return isbnText.getText();
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


		JButton addBookButton = new JButton("Add");
		addBookButton.setBackground(Color.PINK.darker());
		addBookButton.setForeground(Color.black);
		attachButtonListener(addBookButton);

		JButton addBackToMainBtn = new JButton("<-");
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

	
	private void attachButtonListener(JButton a) {
		a.addActionListener(e -> {
			try {
				ruleSet.applyRules(BookCopyAdd.this);
				Book b = systemController.getBook(getIsbnValue());
				if(b != null) {
					b.addCopy();
					systemController.updateBook(b);
					JOptionPane.showMessageDialog(this, "New Book Copy for: " + b.getTitle() + " successfully added!",
							"Add copy", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					throw new LibrarySystemException("Book doesn't exist");
				}
			} catch (LibrarySystemException exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage(),
						"Update book error", JOptionPane.ERROR_MESSAGE, null);
			}
		});
	}
	
	
	private void backToMainListener(JButton a) {
		a.addActionListener(e -> {	
			mainPanel.removeAll();
			LibrarySystemCustom.INSTANCE.renderMainPanel();
		});
	}

}
