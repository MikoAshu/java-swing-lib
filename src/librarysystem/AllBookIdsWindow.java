package librarysystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.ControllerInterface;
import business.SystemController;
import librarysystem.UI.LibrarySystemCustom;


public class AllBookIdsWindow {
//	private static final long serialVersionUID = 1L;
//	public static final AllBookIdsWindow INSTANCE = new AllBookIdsWindow();
    ControllerInterface ci = SystemController.getInstance();
    private boolean isInitialized = false;
	public JPanel getMainPanel() {
		return mainPanel;
	}
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private TextArea textArea;

	public AllBookIdsWindow() {
		init();
	}
	
	public void init() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		mainPanel.add(lowerPanel, BorderLayout.SOUTH);
//		getContentPane().add(mainPanel);
//		isInitialized = true;
	}
	
	public void defineTopPanel() {
		topPanel = new JPanel();
		JButton backBtn = new JButton("<-");
		backBtn.setBackground(Color.PINK.darker());
		backBtn.setForeground(Color.black);
		backButtonListener(backBtn);
		topPanel.add(backBtn);
	}
	
	public void defineMiddlePanel() {
		middlePanel = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
		middlePanel.setLayout(fl);
		textArea = new TextArea(8, 20);
		//populateTextArea();
		middlePanel.add(textArea);
		
	}
	
	public void defineLowerPanel() {
		
		JButton backToMainButn = new JButton("<= Back to Main");
		backToMainButn.addActionListener(new BackToMainListener());
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));;
		lowerPanel.add(backToMainButn);
	}

	private void backButtonListener(JButton backBtn) {
		backBtn.addActionListener(e -> {
			mainPanel.removeAll();
			LibrarySystemCustom.INSTANCE.renderMainPanel();
		});
	}

	class BackToMainListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
    		
		}
	}
	
	public void setData(String data) {
		textArea.setText(data);
	}
}
