/**
 * 
 */
package Project3;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Alex
 * @author Cari
 */
public abstract class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private int number;
	private String owner;
	private GregorianCalendar dateOpened;
	private double balance;
	
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
	
	public String toString() {
		String str = number + "/t" + owner + "/t" + dateOpened
				+ "/t" + balance;
		return str;
	}
}
