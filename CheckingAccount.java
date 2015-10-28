/**
 * 
 */
package Project3;

import java.util.GregorianCalendar;

/**
 * @author Alex
 * @author Cari
 */
public class CheckingAccount extends Account {
	
	private static final long serialVersionUID = 1L;
	private double monthlyFee;
	
	public CheckingAccount(int num, String own, GregorianCalendar date,
			double bal, double fee) {
		super(num, own, date, bal);
		monthlyFee = fee;
	}

	public double getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean equals(CheckingAccount a) {
		if (super.equals(a) && (a.getMonthlyFee() == monthlyFee)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		String str = super.toString() + "\t" +  monthlyFee;
		return str;
	}
	
	//Testing purposes
	public static void main(String[] args) {
		GregorianCalendar cal = new GregorianCalendar(2015, 10, 27);
		CheckingAccount t = new CheckingAccount(109, "Cari", cal, 103.4,
				1.4);
		System.out.println(t);
		
		CheckingAccount a = new CheckingAccount(109, "Cari", cal, 103.4,
				1.4);
		CheckingAccount b = new CheckingAccount(104, "Cari", cal, 103.4,
				1.4);
		
		System.out.println(a.equals(t));
		System.out.println(t.equals(b));
	}
}