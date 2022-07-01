package librarysystem;

import business.LibrarySystemException;

import java.awt.*;

public interface RuleSet {
    void applyRules(Component component) throws LibrarySystemException;
}
