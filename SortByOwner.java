package project3;

import java.util.Comparator;

public class SortByOwner implements Comparator<Account> {

	@Override
	public int compare(Account a, Account b) {
		return a.getOwner().compareToIgnoreCase(b.getOwner());
	}
	
}
