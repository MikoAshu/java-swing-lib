package librarysystem;

import business.Author;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NewBookNewAuthor extends JFrame {
    private List<Author> authors;
    ControllerInterface sc;

    public NewBookNewAuthor() {
        sc = SystemController.getInstance();
        authors = new ArrayList<>();
    }
    
}
