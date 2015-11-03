package Project3;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.AbstractListModel;

public class BankModel extends AbstractListModel {

	private ArrayList<Account> acts;

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
			 acts.add(getSize(),new SavingsAccount(actNum, name, 
							dateOpened, bal, minBal, interestRate));
		}
		else if (act ==0){
			acts.add(getSize(),new CheckingAccount(actNum, name, 
							dateOpened, bal, monthFee));
		
		}
		fireIntervalAdded(acts, 0, acts.size());
		for(Account i:acts){
			System.out.println(i+",  "+ acts.size());
		}
	}

	public void deleteAccount(int actPos) {
		acts.remove(actPos);
		fireIntervalRemoved(acts, 0, getSize());
		System.out.println(actPos+",  "+ acts.size());
	}

	public void updateAccount(Account act, int actNum, String name, 
			GregorianCalendar dateOpened, double bal, double monthFee, 
			double interestRate, double minBal) {
		if (act instanceof SavingsAccount){
			 ((SavingsAccount) act).setMinBalance(minBal);
			 ((SavingsAccount) act).setInterestRate(interestRate);
			 act.setOwner(name);
			 act.setBalance(bal);
			 act.setDateOpened(dateOpened);
			 act.setNumber(actNum);
		}
		else if (act instanceof CheckingAccount){
			((CheckingAccount) act).setMonthlyFee(monthFee);
			act.setOwner(name);
			act.setBalance(bal);
			act.setDateOpened(dateOpened);
			act.setNumber(actNum);
		}
		fireContentsChanged(acts, 0, acts.size());
	}

	public void sortByOwner() {

	}

	public void sortByNumber() {

	}

	public void sortByName() {

	}

	public void loadBinary(String file) {
		// This will reference one line at a time
        String line = null;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.toString()) != null) {
                System.out.println(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                file + "");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + file + "");                  
        }
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
