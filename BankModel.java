package Project3;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.AbstractListModel;

public class BankModel extends AbstractListModel {

	ArrayList<Account> acts;

	BankModel() {
		acts = new ArrayList<Account>();
	}

	public Account findAccount(String name) {

		return null;
	}

	public void addAccount(int act, int actNum, String name, 
			GregorianCalendar dateOpened, double bal, double monthFee, 
				double interestRate, double minBal){
		if (act ==1){
			 acts.add(new SavingsAccount(actNum, name, 
					dateOpened, bal, minBal, interestRate));
		}
		else if (act ==0){
			acts.add(new CheckingAccount(actNum, name, 
					dateOpened, bal, monthFee));
		}
		fireIntervalAdded(acts, 0, acts.size());
	}

	public void deleteAccount(int actPos) {

	}

	public void updateAccount(Account act) {

	}

	public void sortByOwner() {

	}

	public void sortByNumber() {

	}

	public void sortByName() {

	}

	public void loadBinary(String file) {

	}

	public void saveBinary(String file) {

	}

	public void loadText(String file) {

	}

	public void saveText(String file) {

	}

	public void loadXML(String file) {

	}

	public void saveXML(String file) {

	}
	/*
	 * @Override protected void fireContentsChanged(Object source,int index0,
	 * int index1){ if(source instanceof Account){ while (Account)
	 * 
	 * if (source instanceof CheckingAccount){
	 * 
	 * } else if (source instanceof SavingsAccount){
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * @Override protected void fireIntervalAdded(Object source,int index0, int
	 * index1){
	 * 
	 * }
	 * 
	 * @Override protected void fireIntervalRemoved(Object source,int index0,
	 * int index1){
	 * 
	 * }
	 */

	@Override
	public Object getElementAt(int position) {
		return acts.get(position);
	}

	@Override
	public int getSize() {
		return acts.size();
	}

}
