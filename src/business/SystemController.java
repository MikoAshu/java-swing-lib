package business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import javax.xml.crypto.Data;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	private static SystemController INSTANCE;

	private SystemController() {
	}

	public static SystemController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SystemController();
		}
		return INSTANCE;
	}

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	@Override
    public void addMember(String id, String firstName, String lastName, String cell, String street, String city, String state, String zip) throws LibrarySystemException {
        if (id.length() == 0 || firstName.length() == 0 || lastName.length() == 0
                || cell.length() == 0 || street.length() == 0 || city.length() == 0
                || state.length() == 0 || zip.length() == 0) {
        	throw new LibrarySystemException("All fields must be non-empty");
        }
        Address address = new Address(street, city, state, zip);
        if (searchMember(id) != null) {
            throw new LibrarySystemException("Library Member with ID " + id + " already exists");
        }
        DataAccess da = new DataAccessFacade();
        LibraryMember member = new LibraryMember(id, firstName, lastName, cell, address);
        da.saveMember(member);
    }
	@Override
    public LibraryMember searchMember(String memberId) {
        DataAccess da = new DataAccessFacade();
        return da.searchMember(memberId);
    }
	@Override
	public Book getBook(String isbn) {
		DataAccess da = new DataAccessFacade();
		return da.getBookByISBN(isbn);
	}

	@Override
	public List<Book> readAllBooks() {
		DataAccess da = new DataAccessFacade();
		List<Book> books = new ArrayList<>();
		books.addAll(da.readBooksMap().values());
		return books;
	}

	@Override
	public void updateBook(Book book) {
		if (book == null) {
			System.out.println("null book can't be given");
			return;
		}
		DataAccess da = new DataAccessFacade();
		da.updateBook(book);

	}
	
    @Override
    public LibraryMember searchLibMember(String memberId) {
        DataAccess da = new DataAccessFacade();
        return da.searchMember(memberId);
    }
    

    @Override
    public List<String[]> getLibMemCheckoutEntries(String memberId) throws LibrarySystemException {
        LibraryMember member = searchLibMember(memberId);
        if (member == null) {
            throw new LibrarySystemException("Member with with id '" + memberId + "' does not exist");
        }
        List<CheckoutRecordEntry> checkoutBooks = member.getCheckoutRecord().getCheckoutRecordEntries();
        List<String[]> records = new ArrayList<>();
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (CheckoutRecordEntry ch : checkoutBooks) {
            String[] recs = new String[]{
                    memberId,
                    ch.getBookCopy().getBook().getIsbn(),
                    Integer.toString(ch.getBookCopy().getCopyNum()),
                    simpleDateFormat.format((ch.getCheckoutDate().getTime())),
                    simpleDateFormat.format((ch.getDueDate().getTime())),
            };
            records.add(recs);
        }
        return records;
    }
    
    @Override
    public void checkoutBook(String memberId, String isbn) throws LibrarySystemException {
        DataAccess da = new DataAccessFacade();
        Book book = da.searchBook(isbn);
        List<String[]> records = getLibMemberCheckoutEntries(memberId);

        if (book == null) {
            throw new LibrarySystemException("Book with ISBN '" + isbn + "' not found.");
        }
        LibraryMember member = searchLibMember(memberId);
        if (member == null) {
            throw new LibrarySystemException("Library member with ID '" + memberId + "' not found.");
        }
        if (!book.isAvailable()) {
            throw new LibrarySystemException("There are no available copies for book with ID '" + isbn + "' to checkout.");
        }

        for (String[] rec: records) {
            if (memberId.equals(rec[0]) && isbn.equals(rec[1]))
                throw new LibrarySystemException("Book ISBN " + rec[1] + " has been checkedout with the Member ID " + rec[0] );
        }

        Calendar calCurDate = Calendar.getInstance(); // Using today's date
        calCurDate.setTime(new Date());
        Calendar dueCalDate = Calendar.getInstance();
        dueCalDate.setTime(new Date()); // Using today's date
        dueCalDate.add(Calendar.DATE, book.getMaxCheckoutLength());
        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(
                calCurDate, dueCalDate,
                book.getNextAvailableCopy()
        );
        member.getCheckoutRecord().addCheckOutEntry(checkoutRecordEntry);
        da.updateMember(member);
        updateBook(book);
    }


	@Override
    public List<String[]> getLibMemberCheckoutEntries(String memberId) throws LibrarySystemException {
        LibraryMember member = searchLibMember(memberId);
        if (member == null) {
            throw new LibrarySystemException("Member with with id '" + memberId + "' does not exist");
        }
        List<CheckoutRecordEntry> checkoutBooks = new ArrayList<>();
        
        if(member.getCheckoutRecord() != null) {
        	 checkoutBooks = member.getCheckoutRecord().getCheckoutRecordEntries();
        }
       
        List<String[]> records = new ArrayList<>();
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (CheckoutRecordEntry ch : checkoutBooks) {
            String[] recs = new String[] {
                    memberId,
                    ch.getBookCopy().getBook().getIsbn(),
                    Integer.toString(ch.getBookCopy().getCopyNum()),
                    simpleDateFormat.format((ch.getCheckoutDate().getTime())),
                    simpleDateFormat.format((ch.getDueDate().getTime())),
            };
            records.add(recs);
        }
        return records;
    }
    
    @Override
    public List<String[]> getMemberCheckoutEntries(String memberId) throws LibrarySystemException {
        LibraryMember member = searchMember(memberId);
        if (member == null) {
            throw new LibrarySystemException("Member with with id '" + memberId + "' does not exist");
        }
        List<CheckoutRecordEntry> checkoutBooks = member.getCheckoutRecord().getCheckoutRecordEntries();
        List<String[]> records = new ArrayList<>();
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for (CheckoutRecordEntry ch : checkoutBooks) {
            String[] recs = new String[]{
                    memberId,
                    ch.getBookCopy().getBook().getIsbn(),
                    Integer.toString(ch.getBookCopy().getCopyNum()),
                    simpleDateFormat.format((ch.getCheckoutDate().getTime())),
                    simpleDateFormat.format((ch.getDueDate().getTime())),
            };
            records.add(recs);
        }
        return records;
    }

	public static Auth getCurrentAuth() {
		return currentAuth;
	}
}
