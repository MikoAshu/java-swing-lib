package librarysystem;

import business.LibrarySystemException;

import javax.swing.*;
import java.awt.*;

public class BookCopyAddRuleSet implements RuleSet{
    BookCopyAdd frame;
    @Override
    public void applyRules(Component component) throws LibrarySystemException {
        if (!(component instanceof JFrame))
            throw new LibrarySystemException("Internal Error retry soon");
        frame = (BookCopyAdd) component;
        noEmptyField();
    }

    private void noEmptyField() throws LibrarySystemException {
        if (frame.getIsbnValue().equals(""))
            throw new LibrarySystemException("Empty space is not allowed");
    }
}
