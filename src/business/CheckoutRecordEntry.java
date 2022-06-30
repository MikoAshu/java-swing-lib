package business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

final public class CheckoutRecordEntry implements Serializable {
    private final Calendar dueDate;
    private final Calendar checkoutDate;
    private final BookCopy bookCopy;

    public CheckoutRecordEntry(Calendar checkoutDate, Calendar dueDate, BookCopy bookCopy) {
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public Calendar getCheckoutDate() {
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
