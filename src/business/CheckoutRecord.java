package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    private final List<CheckoutRecordEntry> checkoutRecordEntries;

    CheckoutRecord() {
    	this.checkoutRecordEntries = new ArrayList<>();
    }

    public void addCheckOutEntry(CheckoutRecordEntry checkoutRecordEntry) {
        checkoutRecordEntries.add(checkoutRecordEntry);
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
        return checkoutRecordEntries;
    }

    private static final long serialVersionUID = -2222297306790745013L;
}
