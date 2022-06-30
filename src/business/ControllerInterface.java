package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	Book getBook(String isbn);
	List<Book> readAllBooks();
	void updateBook(Book book);
    public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;
    public List<String[]> getLibMemCheckoutEntries(String memberId) throws LibrarySystemException;
    public LibraryMember searchLibMember(String memberId);
	List<String[]> getLibMemberCheckoutEntries(String memberId) throws LibrarySystemException;
}
