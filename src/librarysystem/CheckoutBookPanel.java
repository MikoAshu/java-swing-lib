package librarysystem;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.UI.LibrarySystemCustom;

import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;

public class CheckoutBookPanel {
    ControllerInterface systemController = SystemController.getInstance();
	LibrarySystem librarySystem = LibrarySystem.INSTANCE;
	
    private final JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;

    private JTextField isbnField;
    private JTextField memberIdField;

    private JPanel innerPanel;

    private JButton checkoutButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckoutBookPanel() {
        mainPanel = new JPanel();
        defineTopPanel();
        defineMiddlePanel();
        defineLowerPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        mainPanel.setLayout(bl);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
    }

    private void defineTopPanel() {
        topPanel = new JPanel();
        JButton backBtn = new JButton("<-");
        backBtn.setBackground(Color.PINK.darker());
        backBtn.setForeground(Color.black);
        backButtonListener(backBtn);
        topPanel.add(backBtn);
    }

    private void defineMiddlePanel() {
        middlePanel = new JPanel();
        BorderLayout bl = new BorderLayout();
        bl.setVgap(30);
        middlePanel.setLayout(bl);

        innerPanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        innerPanel.setLayout(fl);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel memberIdLabel = new JLabel("Member ID");
        JLabel isbnLabel = new JLabel("ISBN");

        memberIdField = new JTextField(10);
        isbnField = new JTextField(10);

        leftPanel.add(memberIdLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,12)));
        leftPanel.add(isbnLabel);

        rightPanel.add(memberIdField);
        rightPanel.add(Box.createRigidArea(new Dimension(0,8)));
        rightPanel.add(isbnField);

        innerPanel.add(leftPanel);
        innerPanel.add(rightPanel);
        middlePanel.add(innerPanel, BorderLayout.NORTH);
    }

    private void defineLowerPanel() {
        lowerPanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
        lowerPanel.setLayout(fl);
        checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(Color.PINK.darker());
        checkoutButton.setForeground(Color.black);
        addCheckoutButtonListener(checkoutButton);
        lowerPanel.add(checkoutButton);
    }
    private void backButtonListener(JButton backBtn) {
        backBtn.addActionListener(e -> {
            mainPanel.removeAll();
            LibrarySystemCustom.INSTANCE.renderMainPanel();
        });
    }
    private void addCheckoutButtonListener(JButton checkoutBtn) {
        checkoutBtn.addActionListener(evt -> {
            String iSBNNo = isbnField.getText().trim();
            String memberId = memberIdField.getText().trim();

            if (iSBNNo.length() == 0) {
            	librarySystem.displayMessage("ISBN is required", AppMsg.ERROR);
            } else if(memberId.length() == 0) {
            	librarySystem.displayMessage("User is required", AppMsg.ERROR);
            } else {
                try {
                	systemController.checkoutBook(memberId, iSBNNo);
                	librarySystem.displayMessage("Checkout Successful", AppMsg.SUCCESS);
                } catch (LibrarySystemException e) {
                	librarySystem.displayMessage(e.getMessage(), AppMsg.ERROR);
                }
            }
        });
    }
}

