package business;

import java.util.*;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

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
