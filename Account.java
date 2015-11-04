package Project3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public abstract class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private int number;
	private String owner;
	private Calendar dateOpened;
	private double balance;
	
	private static long MIL_PER_YEAR = 31556952000L;
	private static long MIL_PER_MONTH = 2629746000L;
	private static long MIL_PER_DAY = 86400000L;
	
	public Account(int number, String owner, Calendar
			dateOpened, double balance) {
		super();
		this.number = number;
		this.owner = owner;
		this.dateOpened = dateOpened;
		this.balance = balance;
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

	public Calendar getDateOpened() {
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
		String str = dateOpened.get(Calendar.MONTH) + "/" + 
				dateOpened.get(Calendar.DAY_OF_MONTH) + "/" 
				+ dateOpened.get(Calendar.YEAR);
		System.out.println(str);
		return str;
	}
	
	public String toString() {
		String str = number + ",        " + owner + ",        " 
				+ calendarString() + ",        " + balance;
		return str;
	}
	
}