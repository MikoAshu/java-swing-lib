package business;

import java.util.ArrayList;
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


}
