package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public LibraryMember searchMember(String memberId);
	public List<String> allBookIds();
	public void addMember(String id, String firstName, String lastName, String cell,
            String street, String city, String state, String zip) throws LibrarySystemException;
	Book getBook(String isbn);
	List<Book> readAllBooks();
	void updateBook(Book book);
}
