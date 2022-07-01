package librarysystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import business.ControllerInterface;
import business.SystemController;
import dataaccess.Auth;


public class LibrarySystem extends JFrame implements LibWindow {
	ControllerInterface ci = SystemController.getInstance();
	public final static LibrarySystem INSTANCE =new LibrarySystem();
	
	JPanel mainPanel;
	JMenuBar menuBar;
    JMenu options;
    JMenuItem login, allBookIds, allMemberIds; 
    String pathToImage;
    Auth auth;
    private boolean isInitialized = false;
    
    private static LibWindow[] allWindows = { 
    	LibrarySystem.INSTANCE,
		LoginWindow.INSTANCE,
		AllMemberIdsWindow.INSTANCE,	
//		AllBookIdsWindow.INSTANCE
	};
    private LibrarySystem() {}
    	    
	public static void hideAllWindows() {
		
		for(LibWindow frame: allWindows) {
			frame.setVisible(false);
			
		}
	}
    
    public void showWindows(Auth auth) {
    	this.auth = auth;
        if (auth == Auth.BOTH) {
        	showAllBookIdsPage();
        	showAllMembersPage();
        }
        else if (auth == Auth.ADMIN) {
        	showAllMembersPage();
        }
        else if (auth == Auth.LIBRARIAN) {
        	showAllBookIdsPage();
        }
        else {
        	
        };
        setMenuItems();
    }
   
    

    
    public void init() {
    	formatContentPane();
    	setPathToImage();
    	insertSplashImage();
		
		createMenus();
		//pack();
		setSize(660,500);
		isInitialized = true;
    }
    
	public void displayMessage(String msg, AppMsg appMsg) {
		if(appMsg == AppMsg.ERROR) {
			  JOptionPane.showMessageDialog(INSTANCE, msg);   
		} else if (appMsg == AppMsg.INFO) {
			JOptionPane.showMessageDialog(INSTANCE, msg);   
		} else if (appMsg == AppMsg.WARNING) {
			JOptionPane.showMessageDialog(INSTANCE, msg);   
		} else if (appMsg == AppMsg.SUCCESS) {
			JOptionPane.showMessageDialog(INSTANCE, msg);   
		}
		

	}
	
    private void formatContentPane() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,1));
		getContentPane().add(mainPanel);	
	}
    
    private void setPathToImage() {
    	String currDirectory = System.getProperty("user.dir");
    	pathToImage = currDirectory + "\\src\\librarysystem\\library.jpg";
    }
    
    private void insertSplashImage() {
        ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));	
    }
    
    private void createMenus() {
    	menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
		addMenuItems();
		setJMenuBar(menuBar);		
    }
    
    private void addMenuItems() {
       options = new JMenu("Options");  
 	   menuBar.add(options);
 	   login = new JMenuItem("Login");
 	   login.addActionListener(new LoginListener());
 	   allBookIds = new JMenuItem("All Book Ids");
 	   allBookIds.addActionListener(new AllBookIdsListener());
 	   allMemberIds = new JMenuItem("All Member Ids");
 	   allMemberIds.addActionListener(new AllMemberIdsListener());
 	   options.add(login);
 	   options.add(allBookIds);
 	   options.add(allMemberIds);
    }
    
    private void setMenuItems() {
        options = new JMenu("Options");  
  	   menuBar.add(options);
  	   login = new JMenuItem("Logout");
  	   login.addActionListener(new LoginListener());
  	   allBookIds = new JMenuItem("All Book Ids");
  	   allBookIds.addActionListener(new AllBookIdsListener());
  	   allMemberIds = new JMenuItem("All Member Ids");
  	   allMemberIds.addActionListener(new AllMemberIdsListener());
  	   options.add(login);
  	   options.add(allBookIds);
  	   options.add(allMemberIds);
        if (auth == null) {
        	return;
        } else if (auth == Auth.BOTH) {
        	login = new JMenuItem("Logout");
        }
        else if (auth == Auth.ADMIN) {
        	login = new JMenuItem("Logout");
        }
        else if (auth == Auth.LIBRARIAN) {
        	login = new JMenuItem("Logout");
        }
        else {
        	
        };
    }
    
    class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			LoginWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
			LoginWindow.INSTANCE.setVisible(true);
			
		}
    	
    }
    
    class AllBookIdsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showAllBookIdsPage();
			
		}
    	
    }
    
    class AllMemberIdsListener implements ActionListener {

    	@Override
		public void actionPerformed(ActionEvent e) {
    		showAllMembersPage();	
			
		}
    	
    }
    private void showAllBookIdsPage() {
		LibrarySystem.hideAllWindows();
//		AllBookIdsWindow.INSTANCE.init();
		
		List<String> ids = ci.allBookIds();
		Collections.sort(ids);
		StringBuilder sb = new StringBuilder();
		for(String s: ids) {
			sb.append(s + "\n");
		}
		System.out.println(sb.toString());
//		AllBookIdsWindow.INSTANCE.setData(sb.toString());
//		AllBookIdsWindow.INSTANCE.pack();
		//AllBookIdsWindow.INSTANCE.setSize(660,500);
//		Util.centerFrameOnDesktop(AllBookIdsWindow.INSTANCE);
//		AllBookIdsWindow.INSTANCE.setVisible(true);
    }
    
    private void showAllMembersPage() {
		LibrarySystem.hideAllWindows();
		AllMemberIdsWindow.INSTANCE.init();
		AllMemberIdsWindow.INSTANCE.pack();
		AllMemberIdsWindow.INSTANCE.setVisible(true);
		
		
		LibrarySystem.hideAllWindows();
//		AllBookIdsWindow.INSTANCE.init();
		
		List<String> ids = ci.allMemberIds();
		Collections.sort(ids);
		StringBuilder sb = new StringBuilder();
		for(String s: ids) {
			sb.append(s + "\n");
		}
		System.out.println(sb.toString());
		AllMemberIdsWindow.INSTANCE.setData(sb.toString());
		AllMemberIdsWindow.INSTANCE.pack();
		AllMemberIdsWindow.INSTANCE.setSize(660,500);
		Util.centerFrameOnDesktop(AllMemberIdsWindow.INSTANCE);
		AllMemberIdsWindow.INSTANCE.setVisible(true);
    }

	@Override
 	public boolean isInitialized() {
		return isInitialized;
	}


	@Override
	public void isInitialized(boolean val) {
		isInitialized =val;
		
	}
    
}
