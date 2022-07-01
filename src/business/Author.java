package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable, Comparable<Author> {
	private String bio;
	public String getBio() {
		return bio;
	}
	
	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}



	private static final long serialVersionUID = 7508481940058530471L;
	@Override
	public int compareTo(Author o) {
		if (getFirstName().compareTo(o.getFirstName()) != 0)
			return getFirstName().compareTo(o.getFirstName());
		else if (getLastName().compareTo(o.getLastName()) != 0)
			return getLastName().compareTo(o.getLastName());
		else if (getTelephone().compareTo(o.getTelephone()) != 0)
			return getTelephone().compareTo(o.getTelephone());
		else if (getBio().compareTo(o.getBio()) != 0)
			return getBio().compareTo(o.getBio());
		else
			return getAddress().compareTo(o.getAddress());
	}
}
