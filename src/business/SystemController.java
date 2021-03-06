package business;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
	public List<String[]> getCheckedOutBookCopy(String isbn) throws LibrarySystemException {
		DataAccess da = new DataAccessFacade();
		List<String[]> records = new ArrayList<>();
		Collection<LibraryMember> members = da.readMemberMap().values();
		System.out.println(members);
		for (LibraryMember member : members) {
			System.out.println(member);
			List<CheckoutRecordEntry> entries = member.getCheckoutRecord().getCheckoutRecordEntries();
			for (CheckoutRecordEntry entry : entries) {
				if (entry.getBookCopy().getBook().getIsbn().equals(isbn)) {
					String pattern = "MM/dd/yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String[] recs = new String[]{
							member.getFirstName() + " " + member.getLastName(),
							entry.getBookCopy().getBook().getTitle(),
							entry.getBookCopy().getBook().getIsbn(),
							Integer.toString(entry.getBookCopy().getCopyNum()),
							simpleDateFormat.format((entry.getCheckoutDate().getTime())),
							simpleDateFormat.format((entry.getDueDate().getTime())),
							calculateOverdue(simpleDateFormat.format((entry.getCheckoutDate().getTime())),
									simpleDateFormat.format((entry.getDueDate().getTime())),
									entry.getBookCopy().getBook().getMaxCheckoutLength()) + " days",
					};
					records.add(recs);
				}
			}
		}
		return records;
	}

	@Override
	public Collection<LibraryMember> getMembers() {
		DataAccess da = new DataAccessFacade();
		return da.readMemberMap().values();
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
					member.getFirstName() + " " + member.getLastName(),
					ch.getBookCopy().getBook().getTitle(),
					ch.getBookCopy().getBook().getIsbn(),
					Integer.toString(ch.getBookCopy().getCopyNum()),
                    simpleDateFormat.format((ch.getCheckoutDate().getTime())),
                    simpleDateFormat.format((ch.getDueDate().getTime())),
					calculateOverdue(simpleDateFormat.format((ch.getCheckoutDate().getTime())),
							simpleDateFormat.format((ch.getDueDate().getTime())),
							ch.getBookCopy().getBook().getMaxCheckoutLength()) + " days",
            };
            records.add(recs);
        }
        return records;
    }

    @Override
    public List<String[]> getMemberCheckoutEntries(String memberId) throws LibrarySystemException {
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
					member.getFirstName() + " " + member.getLastName(),
					ch.getBookCopy().getBook().getTitle(),
					ch.getBookCopy().getBook().getIsbn(),
					Integer.toString(ch.getBookCopy().getCopyNum()),
					simpleDateFormat.format((ch.getCheckoutDate().getTime())),
					simpleDateFormat.format((ch.getDueDate().getTime())),
					calculateOverdue(simpleDateFormat.format((ch.getCheckoutDate().getTime())),
							simpleDateFormat.format((ch.getDueDate().getTime())),
							ch.getBookCopy().getBook().getMaxCheckoutLength()) + " days",
			};
			records.add(recs);
		}
		return records;
    }

	@Override
	public int calculateOverdue(String startDate, String endDate, int timeAllowed) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate start = LocalDate.parse(startDate, df);
		LocalDate end = LocalDate.parse(endDate, df);
		long daysBetween = ChronoUnit.DAYS.between(start, end);
		System.out.println(daysBetween);
		if (daysBetween < timeAllowed)
			return 0;
		return (int)daysBetween - timeAllowed;
	}

	public static Auth getCurrentAuth() {
		return currentAuth;
	}

	@Override
	public Author[] getAuthors() {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookHashMap = da.readBooksMap();
		Set<Author> authors = new TreeSet<>();
		bookHashMap.forEach((k,v) -> {
			for (int i = 0; i < v.getAuthors().size(); i++) {
				authors.add(v.getAuthors().get(i));
			}
		});
		Author[] a = new Author[authors.size()];
		authors.toArray(a);
		return a;
	}

	@Override
	public void addNewBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) throws LibrarySystemException {
		if (isbn == null || title == null || authors == null)
			throw new LibrarySystemException("Empty fields is not allowed");
		Book b = new Book(isbn, title, maxCheckoutLength, authors);
		b.addCopy();
		DataAccess da = new DataAccessFacade();
		da.updateBook(b);
	}

	@Override
	public Author createNewAuthor(String firstName, String lastName, String phone, String bio, Address address) throws LibrarySystemException{
		if (firstName == null || firstName.equals("") ||
				lastName == null || lastName.equals("") ||
				phone == null || phone.equals("") ||
				bio == null || bio.equals("") ||
				address == null)
			throw new LibrarySystemException("Invalid argument for Author");
		return new Author(firstName, lastName, phone, address, bio);
	}

	@Override
	public Address createNewAddress(String street, String city, String state, String zip) throws LibrarySystemException {
		if (street == null || street.equals("") ||
				city == null || city.equals("") ||
				state == null || state.equals("") ||
				zip == null || zip.equals(""))
			throw new LibrarySystemException("No Empty fields allowed");
		return new Address(street, city, state, zip);
	}


}
