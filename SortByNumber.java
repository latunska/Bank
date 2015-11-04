package project3;

import java.util.Comparator;

public class SortByNumber implements Comparator<Account>{

	@Override
	public int compare(Account o1, Account o2) {
		return o1.getNumber() - o2.getNumber();
	}

}
