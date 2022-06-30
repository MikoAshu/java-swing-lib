package business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

final public class CheckoutRecordEntry implements Serializable {
    private final LocalDate dueDate;
    private final LocalDate checkoutDate;
    private final BookCopy bookCopy;

    public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    @Override
    public String toString() {
        return "CheckoutRecordEntry{" +
                "dueDate=" + dueDate +
                ", checkoutDate=" + checkoutDate +
                ", bookCopy=" + bookCopy +
                '}';
    }

    private static final long serialVersionUID = -2226197306790745013L;
}
