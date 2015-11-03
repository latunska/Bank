/**
 * 
 */
package Project3;

import java.io.Serializable;
import java.util.Collections;
import java.util.GregorianCalendar;

public abstract class Account implements Serializable, Comparable<Account> {
	private static final long serialVersionUID = 1L;
	private int number;
	private String owner;
	private GregorianCalendar dateOpened;
	private double balance;
	
	private static long MIL_PER_YEAR = 31556952000L;
	private static long MIL_PER_MONTH = 2629746000L;
	private static long MIL_PER_DAY = 86400000L;
	
	public Account(int number, String owner, GregorianCalendar
			dateOpened, double balance) {
		super();
		this.number = number;
		this.owner = owner;
		this.dateOpened = dateOpened;
		this.balance = balance;
	}

	public Account(int num, String own, double bal) {
		number = num;
		owner = own;
		balance = bal;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public GregorianCalendar getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(GregorianCalendar dateOpened) {
		this.dateOpened = dateOpened;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean equals(Account a) {
		if ((a.getBalance() == balance) && 
			(a.getDateOpened() == dateOpened) && (a.getNumber() 
			== number) && (a.getOwner() == owner)) {
				return true;
			}
			else {
				return false;
			}
	}
	
	public String calendarString() {
		//FIXME: Day slightly off due to leap years/February/etc
		long time = dateOpened.getTimeInMillis();
		long years = time / MIL_PER_YEAR;
		long months = (time%MIL_PER_YEAR) / MIL_PER_MONTH;
		long days = (time%MIL_PER_MONTH) / MIL_PER_DAY;
		
		String str = months + "/" + days + "/" + (years + 1970L);
		return str;
	}
	
	public String toString() {
		String str = number + "      " + owner + "    " 
				+ calendarString() + "    " + balance;
		return str;
	}
	
}
