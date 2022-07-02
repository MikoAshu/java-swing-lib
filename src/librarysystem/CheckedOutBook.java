package librarysystem;

import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import librarysystem.UI.LibrarySystemCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CheckedOutBook {
    ControllerInterface sc = SystemController.getInstance();
    LibrarySystem librarySystem = LibrarySystem.INSTANCE;

    private JTable table;
    private JPanel topPanel;
    private JPanel lowerPanel;
    private JPanel middlePanel;
    private boolean tableDataSet;
    private final JPanel mainPanel;

    private JTextField isbnField;

    private JButton searchButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public CheckedOutBook() {
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
    private void backButtonListener(JButton backBtn) {
        backBtn.addActionListener(e -> {
            mainPanel.removeAll();
            LibrarySystemCustom.INSTANCE.renderMainPanel();
        });
    }
    private void defineMiddlePanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel isbnLabel = new JLabel("ISBN: ");
        middlePanel.add(isbnLabel, FlowLayout.LEFT);

        isbnField = new JTextField(15);
        middlePanel.add(isbnField, FlowLayout.CENTER);

        searchButton = new JButton("Search");
        searchButton.setBackground(Color.PINK.darker());
        searchButton.setForeground(Color.black);
        searchButtonListener(searchButton);
        middlePanel.add(searchButton, FlowLayout.RIGHT);
    }

    private void defineLowerPanel() {
        lowerPanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        lowerPanel.setLayout(fl);
    }

    private void searchButtonListener(JButton searchBtn) {
        searchBtn.addActionListener(e -> {
            JScrollPane scrollPane;
            String isbn = isbnField.getText().trim();
            if (isbn.length() == 0) {
                librarySystem.displayMessage("Member ID should not be empty", AppMsg.ERROR);
            } else {
                try {
                    List<String[]> records = sc.getCheckedOutBookCopy(isbn);
                    if (records.size() == 0)
                        librarySystem.displayMessage("No Records", AppMsg.INFO);
                    else {
                        if (!tableDataSet) {
                            DefaultTableModel tableModel = new DefaultTableModel();
                            tableModel.addColumn("Member Name");
                            tableModel.addColumn("Book");
                            tableModel.addColumn("ISBN");
                            tableModel.addColumn("Copy Number");
                            tableModel.addColumn("Checkout Date");
                            tableModel.addColumn("Due Date");
                            tableModel.addColumn("Overdue");




                            table = new JTable(tableModel);

                            for (String[] rec : records) {
                                tableModel.addRow(rec);
                                System.out.println(rec[4]);
                            }
//                            tableModel.addRow(new String[]{});
//                            tableModel.addRow(new String[]{});
//                            tableModel.addRow(new String[]{});
//                            tableModel.addRow(new String[]{});
//                            tableModel.addRow(new String[]{});
//                            tableModel.addRow(new String[]{});

                            table.setPreferredScrollableViewportSize(table.getPreferredSize());
                            table.setFillsViewportHeight(true);
                            scrollPane = new JScrollPane(table);

                            lowerPanel.add(scrollPane);
                            tableDataSet = true;
                        } else {
                            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                            tableModel.setRowCount(0);

                            for (String[] rec : records) {
                                tableModel.addRow(rec);
                            }

                            table.setModel(tableModel);
                        }
                        librarySystem.displayMessage("Records fetched successfully", AppMsg.SUCCESS);
                    }
                } catch (LibrarySystemException ex) {
                    librarySystem.displayMessage("Error while executing ", AppMsg.ERROR);
                }
            }
        });
    }
}