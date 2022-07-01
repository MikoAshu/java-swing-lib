package librarysystem;

import business.LibrarySystemException;

import javax.swing.*;
import java.awt.*;

public class AddNewBookRuleSet implements RuleSet{
    AddNewBook frame;
    @Override
    public void applyRules(Component component) throws LibrarySystemException {
//        if (!(component instanceof JFrame))
//            throw new LibrarySystemException("Internal Error retry soon");
        frame = (AddNewBook) component;
        noEmptyField();
        maxAllowedDateNumeric();
        maxAllowedRange();
    }

    private void maxAllowedRange() throws LibrarySystemException{
        int days = Integer.parseInt(frame.getMaxCheckoutValue());
        if (days < 7 || days > 21)
            throw new LibrarySystemException("Checkout is allowed only in the range of 7 - 21 days");
    }

    private void maxAllowedDateNumeric() throws LibrarySystemException {
        try {
            Integer.parseInt(frame.getMaxCheckoutValue());
        } catch (NumberFormatException e) {
            throw new LibrarySystemException("Max checkout value should be numeric");
        }
    }

    private void noEmptyField() throws LibrarySystemException {
        if (frame.getTitleValue().equals("")
            || frame.getBookISBNValue().equals("")
            || frame.getMaxCheckoutValue().equals(""))
            throw new LibrarySystemException("Empty field is not allowed");
    }
}
