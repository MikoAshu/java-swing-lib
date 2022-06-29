package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	 public static final SystemController INSTANCE = new SystemController();
	
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
	
	
}
