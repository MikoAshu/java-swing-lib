package librarysystem;

import business.Author;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.UI.LibrarySystemCustom;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddNewBook extends JPanel {
    public JPanel getMainPanel() {
        return mainPanel;
    }

    RuleSet ruleSet;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JList<Author> chooseAuthor;
    private JScrollPane scrollPane;
    private DefaultListModel<Author> listModel;
    private JPanel outerMiddle;

    private JTextField bookISBN;
    private JTextField titleField;
    private JTextField maxCheckoutLength;
    private JTextField authLastNameField;
    private JTextField authFirstNameField;
    private JTextField authPhoneNumberField;

    private final ControllerInterface systemController;

    public String getBookISBNValue() {
        return bookISBN.getText();
    }
    public String getTitleValue() {
        return bookISBN.getText();
    }
    public String getMaxCheckoutValue() {
        return maxCheckoutLength.getText();
    }
    public String getauthLastNameValue() {
        return maxCheckoutLength.getText();
    }

    public void clearData() {
        authPhoneNumberField.setText("");
        authFirstNameField.setText("");
        authLastNameField.setText("");
        maxCheckoutLength.setText("");
        titleField.setText("");
        bookISBN.setText("");
    }

    public AddNewBook() {
        ruleSet = new AddNewBookRuleSet();
        systemController = SystemController.getInstance();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineOuterMiddle();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(outerMiddle, BorderLayout.CENTER);
//        getContentPane().add(mainPanel);
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JButton backBtn = new JButton("<-");
        backBtn.setBackground(Color.PINK.darker());
        backBtn.setForeground(Color.black);
        backButtonListener(backBtn);
        topPanel.add(backBtn);
    }

    private void backButtonListener(JButton backBtn) {
        backBtn.addActionListener(e -> {
            mainPanel.removeAll();
            LibrarySystemCustom.INSTANCE.renderMainPanel();
        });
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

        JLabel titleLabel = new JLabel("Book Title");
        JLabel bookISBNLabel = new JLabel("Book ISBN");
        JLabel authFirstNameLabel = new JLabel("Author");
        JLabel maxCheckoutLengthLabel = new JLabel("Max Checkout Length");

        bookISBN = new JTextField(10);
        titleField = new JTextField(10);
        maxCheckoutLength = new JTextField(10);
        authLastNameField = new JTextField(10);
        authFirstNameField = new JTextField(10);
        authPhoneNumberField = new JTextField(10);
        Author[] authors = systemController.getAuthors();
        listModel = new DefaultListModel<>();
        populateModel(authors);
        chooseAuthor = new JList<>(listModel);
        chooseAuthor.setVisibleRowCount(4);
        chooseAuthor.setFixedCellWidth(150);
        scrollPane = new JScrollPane(chooseAuthor);
        scrollPane.setPreferredSize(new Dimension(chooseAuthor.getWidth() + 1, chooseAuthor.getHeight()));

        leftPanel.add(authFirstNameLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(bookISBNLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        leftPanel.add(maxCheckoutLengthLabel);

        rightPanel.add(chooseAuthor);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        rightPanel.add(titleField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        rightPanel.add(bookISBN);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        rightPanel.add(maxCheckoutLength);

        middlePanel.add(leftPanel);
        middlePanel.add(rightPanel);
        outerMiddle.add(middlePanel, BorderLayout.NORTH);

        //add button at bottom
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> {
            try {
                ruleSet.applyRules(AddNewBook.this);
                systemController.addNewBook(getBookISBNValue(), getTitleValue(),
                        Integer.parseInt(getMaxCheckoutValue()), chooseAuthor.getSelectedValuesList());
                JOptionPane.showMessageDialog(this, "New Book: " + getTitleValue() + " successfully added!",
                        "Add New Book", JOptionPane.INFORMATION_MESSAGE, null);
                clearData();
            } catch (LibrarySystemException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Add book error", JOptionPane.ERROR_MESSAGE, null);
            }
        });
        addBookButton.setBackground(Color.PINK.darker());
        addBookButton.setForeground(Color.black);
        JPanel addBookButtonPanel = new JPanel();
        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addBookButtonPanel.add(addBookButton);
        outerMiddle.add(addBookButtonPanel, BorderLayout.CENTER);
    }

    private void populateModel(Author[] authors) {
        for (Author author : authors) {
            listModel.addElement(author);
        }
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    AddNewBook frame = new AddNewBook();
//                    frame.setVisible(true);
//                    frame.setSize(new Dimension(600, 450));
//                    Util.centerFrameOnDesktop(frame);
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
