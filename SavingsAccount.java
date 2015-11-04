package Project3;

import java.util.GregorianCalendar;

public class SavingsAccount extends Account {
	private static final long serialVersionUID = 1L;
	   
	private double minBalance;
	private double interestRate;
	
	public SavingsAccount(int number, String owner, GregorianCalendar
			dateOpened, double balance, double minBal, double rate) {
		super(number, owner, dateOpened, balance);
		minBalance = minBal;
		interestRate = rate;
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean equals(SavingsAccount a) {
		if ((super.equals(a) && (a.getInterestRate() == interestRate)
				&& a.getMinBalance() == minBalance)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		String str = super.toString() + ",        ,        " 
				+ minBalance + ",        "+ interestRate;
		return str;
	}
	
	//Testing purposes
	public static void main(String[] args) {
		GregorianCalendar cal = new GregorianCalendar(2015, 10, 27);
		SavingsAccount t = new SavingsAccount(109, "Cari", cal, 103.4,
				5.0, 4.0);
		System.out.println(t);
		
		SavingsAccount a = new SavingsAccount(109, "Cari", cal, 103.4,
				5.0, 4.0);
		SavingsAccount b = new SavingsAccount(104, "Cari", cal, 103.4,
				5.0, 4.0);
		
		System.out.println(a.equals(t));
		System.out.println(t.equals(b));
	}
}
