package project3;

import java.util.Comparator;

public class SortByDate implements Comparator<Account>{

	@Override
	public int compare(Account a, Account b) {
		long aTime = a.getDateOpened().getTimeInMillis();
		long bTime = b.getDateOpened().getTimeInMillis();
		return (int)(bTime - aTime);
	}

}
