package dataaccess;

import java.util.HashMap;

import business.Book;
import business.LibraryMember;
import business.LibrarySystemException;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public void saveMember(LibraryMember member);
	public LibraryMember searchMember(String memberId);
	public Book searchBook(String isbn);
	public void saveNewMember(LibraryMember member);
	Book getBookByISBN(String isbn);

	void updateBook(Book book);
}
