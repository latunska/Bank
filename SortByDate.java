package Project3;

import java.util.Comparator;

public class SortByDate implements Comparator<Account>{

	@Override
	public int compare(Account a, Account b) {
		return a.getDateOpened().compareTo(b.getDateOpened());
	}

}
