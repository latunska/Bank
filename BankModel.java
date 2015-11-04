package project3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

//public class BankModel extends AbstractListModel {
//
//	private ArrayList<Account> acts;
//
//	BankModel() {
//		acts = new ArrayList<Account>();
//	}
//
//	public Account findAccount(String name) {
//
//		return null;
//	}
//
//	public void addAccount(int act, int actNum, String name, 
//			GregorianCalendar dateOpened, double bal, double monthFee, 
//				double interestRate, double minBal){
//		if (act ==1){
//			 acts.add(getSize(),new SavingsAccount(actNum, name, 
//							dateOpened, bal, minBal, interestRate));
//		}
//		else if (act ==0){
//			acts.add(getSize(),new CheckingAccount(actNum, name, 
//							dateOpened, bal, monthFee));
//		
//		}
//		fireIntervalAdded(acts, 0, acts.size());
//		for(Account i:acts){
//			System.out.println(i+",  "+ acts.size());
//		}
//	}
//
//	public void deleteAccount(int actPos) {
//		acts.remove(actPos);
//		fireIntervalRemoved(acts, 0, getSize());
//		System.out.println(actPos+",  "+ acts.size());
//	}
//
//	public void updateAccount(Account act, int actNum, String name, 
//			GregorianCalendar dateOpened, double bal, double monthFee, 
//			double interestRate, double minBal) {
//		if (act instanceof SavingsAccount){
//			 ((SavingsAccount) act).setMinBalance(minBal);
//			 ((SavingsAccount) act).setInterestRate(interestRate);
//			 act.setOwner(name);
//			 act.setBalance(bal);
//			 act.setDateOpened(dateOpened);
//			 act.setNumber(actNum);
//		}
//		else if (act instanceof CheckingAccount){
//			((CheckingAccount) act).setMonthlyFee(monthFee);
//			act.setOwner(name);
//			act.setBalance(bal);
//			act.setDateOpened(dateOpened);
//			act.setNumber(actNum);
//		}
//		fireContentsChanged(acts, 0, acts.size());
//	}
//
//	public void sortByOwner() {
//		Collections.sort(acts, new SortByOwner());
//		fireContentsChanged(acts, 0, acts.size());
//	}
//
//	public void sortByNumber() {
//		Collections.sort(acts, new SortByNumber());
//		fireContentsChanged(acts, 0, acts.size());
//	}
//
//	public void sortByDate() {
//		Collections.sort(acts, new SortByDate());
//		fireContentsChanged(acts, 0, acts.size());
//	}
//
//	public void loadBinary(String file) throws IOException {
//		try{
//			   FileInputStream fin = new FileInputStream(file);
//			   ObjectInputStream ois = new ObjectInputStream(fin);
//			   acts = (ArrayList<Account>) ois.readObject();
//			   ois.close();
//			   fireIntervalAdded(acts, 0, acts.size());
//			   
//		   }catch(Exception ex){
//			   ex.printStackTrace();
//		   } 
//	}
//
//	public void saveBinary(String file) throws IOException {
//		ObjectOutputStream oos = null;
//		FileOutputStream fout = null;
//		try{
//		    fout = new FileOutputStream(file, false);
//		    oos = new ObjectOutputStream(fout);
//		    oos.writeObject(acts);
//		} catch (Exception ex) {
//		    ex.printStackTrace();
//		} finally {
//		    if(oos  != null){
//		        oos.close();
//		    } 
//		}
//	}
//
//public void loadText(String file) {
//	// This will reference one line at a time
//    String line = null;
//    String[] parts=null;
//    String[]lines;
//    String[] sigh;
//    int[] date={0,0,0};
//	try {
//        // FileReader reads text files in the default encoding.
//        FileReader fileReader = 
//            new FileReader(file);
//
//        // Always wrap FileReader in BufferedReader.
//        BufferedReader bufferedReader = 
//            new BufferedReader(fileReader);
//
//        while((line = bufferedReader.readLine()) != null) {
//           lines=line.split(",        ");
//           System.out.println(lines.length+" "+lines[1]);
//           for(int j=0;j<lines.length;j++){
//           parts=lines[j].split(",");
//           System.out.println(parts.length+" "+parts[j]);
//           sigh=parts[3].split("/");
//           for(int i=0; i<sigh.length;i++){
//        	  date[i]= Integer.parseInt(sigh[i]);
//           }
//           GregorianCalendar calend=new GregorianCalendar(date[2],date[0],date[1]);
//           if(parts[0]=="SavingsAccount"){
//        	   
//        	   acts.add(new SavingsAccount(Integer.parseInt(parts[1]),parts[2],calend,Double.parseDouble(parts[5]),Double.parseDouble(parts[6]),Double.parseDouble(parts[7])));
//           }
//           if(parts[0]=="CheckingAccount"){
//        	   acts.add(new CheckingAccount(Integer.parseInt(parts[1]),parts[2],calend,Double.parseDouble(parts[4]),Double.parseDouble(parts[5])));
//           }
//           }
//        }   
//
//        // Always close files.
//        bufferedReader.close();         
//    }
//    catch(FileNotFoundException ex) {
//        System.out.println(
//            "Unable to open file '" + 
//            file + "");                
//    }
//    catch(IOException ex) {
//        System.out.println(
//            "Error reading file '" 
//            + file + "");                  
//    }
//}
//
//	public void saveText(String name) throws IOException {
//		File file = new File(name);
//		file.createNewFile();
//		try {
//			// FileWriter writes text files in the default encoding.
//			FileWriter writer = 
//					new FileWriter(file);
//
//			// BufferWriter holds the text
//			BufferedWriter buffer = 
//					new BufferedWriter(writer);
//
//			for(Account i:acts) {
//				buffer.write(i.getClass()+",        "+i.toString(),15, i.toString().length());
//				buffer.newLine();
//			}   
//
//        
//			buffer.close();         
//		}
//		catch(FileNotFoundException ex) {
//			System.out.println(
//					"Unable to open file '" + 
//							file + "");                
//		}
//		catch (IOException ex) {
//			System.out.println(
//					"Error reading file '" 
//							+ file + "");                  
//		}
//	}
//
//	public void loadXML(String file) {
//
//	}
//
//	public void saveXML(String file) {
//
//	}
//
//	@Override
//	public Object getElementAt(int position) {
//		return acts.get(position);
//	}
//
//	@Override
//	public int getSize() {
//		return acts.size();
//	}
//
//}

public class BankModel extends AbstractTableModel {
	
	private String[] columnNames = {"Account Number", "Account Owner", 
			"Date Opened", "Current Balance", "Monthly Fee",
			"Interest Rate", "Minimum Balance"};
	private Vector<Account> acts;
	
	public BankModel() {
		acts = new Vector<Account>();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return acts.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void addAccount(int act, int actNum, String name, 
			GregorianCalendar dateOpened, double bal, double monthFee, 
				double interestRate, double minBal){
		if (act ==1){
			 acts.add(acts.size(),new SavingsAccount(actNum, name, 
							dateOpened, bal, minBal, interestRate));
		}
		else if (act ==0){
			acts.add(acts.size(),new CheckingAccount(actNum, name, 
							dateOpened, bal, monthFee));
		
		}
		fireTableRowsInserted(0, acts.size());
		for(Account i:acts){
			System.out.println(i+",  "+ acts.size());
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Account a = (Account)acts.get(rowIndex);
		Object o = null;
		switch (columnIndex) {
		case 0:
			o = a.getNumber();
			break;
		case 1:
			o = a.getOwner();
			break;
		case 2:
			o =  a.calendarString();
			break;
		case 3:
			o = a.getBalance();
			break;
		case 4:
			if (acts.get(rowIndex) instanceof CheckingAccount) {
				o = ((CheckingAccount) a).getMonthlyFee();
			}
			else {
				o = "NA";
			}
			break;
		case 5:
			if (acts.get(rowIndex) instanceof SavingsAccount) {
				o = ((SavingsAccount) a).getInterestRate();
			}
			else {
				o = "NA";
			}
			break;
		case 6:
			if (acts.get(rowIndex) instanceof SavingsAccount) {
				o = ((SavingsAccount) a).getMinBalance();
			}
			else {
				o = "NA";
			}
			break;
		}
		return o;
	}
	
	public void updateAccount(int a, int actNum, String name, 
			GregorianCalendar dateOpened, double bal, double monthFee, 
			double interestRate, double minBal) {
		Account act = acts.get(a);
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
		fireTableRowsUpdated(0, acts.size());
	}
	
	public void deleteAccount(int actPos) {
		acts.removeElementAt(actPos);
		fireTableRowsDeleted(0, acts.size());
		System.out.println(actPos+",  "+ acts.size());
	}
	
	public void saveBinary(String file) throws IOException {
	ObjectOutputStream oos = null;
	FileOutputStream fout = null;
		try{
			fout = new FileOutputStream(file, false);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(acts);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if(oos  != null){
				oos.close();
			} 
		}
	}
	
	public void loadBinary(String file) throws IOException {
	try{
		   FileInputStream fin = new FileInputStream(file);
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   acts = (Vector<Account>) ois.readObject();
		   ois.close();
		   fireTableRowsInserted(0, acts.size());
		   
	   }catch(Exception ex){
		   ex.printStackTrace();
	   } 
	}
	
	public void loadText(String file) {
		// This will reference one line at a time
	    String line = null;
	    String[] parts=null;
	    String[]lines;
	    int[] date={0,0,0};
	    
		try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = 
	            new FileReader(file);
	
	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = 
	            new BufferedReader(fileReader);
	
	        while((line = bufferedReader.readLine()) != null) {
	        	System.out.println(line);
	            lines = line.split(",        ");
	            
	            int actNum = Integer.parseInt(lines[1]);
	            String actOwn = lines[2];
	            String cal = lines[3];
	            Double balance = Double.parseDouble(lines[4]);
	            
	            parts = cal.split("/");
	            int month = Integer.parseInt(parts[0]);
	            int day = Integer.parseInt(parts[1]);
	            int year = Integer.parseInt(parts[2]);
	            
	            GregorianCalendar c = new GregorianCalendar(year, month, day);
	            
	            if (lines[0].equals("Checking")) {
		            Double fee = Double.parseDouble(lines[5]);
	            	acts.add(new CheckingAccount(actNum, actOwn, c,
	            			balance, fee));
	            }
	            else {
		            Double rate = Double.parseDouble(lines[6]);
		            Double min = Double.parseDouble(lines[7]);
	            	acts.add(new SavingsAccount(actNum, actOwn, c,
	            			balance, rate, min));
	            }
	        fireTableRowsInserted(0, acts.size());
	        }
	        
	
	        // Always close files.
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	    	JOptionPane.showMessageDialog(null, 
	            "Unable to open file '" + 
	            file + "");                
	    }
	    catch(IOException ex) {
	    	JOptionPane.showMessageDialog(null, 
	            "Error reading file '" 
	            + file + "");                  
	    }
	}
	
	public void saveText(String name) throws IOException {
		File file = new File(name);
		file.createNewFile();
		try {
			// FileWriter writes text files in the default encoding.
			FileWriter writer = 
					new FileWriter(file);

			// BufferWriter holds the text
			BufferedWriter buffer = 
					new BufferedWriter(writer);

			for(Account i:acts) {
				if (i instanceof CheckingAccount) {
					String str = i.getClass() + ",        " + ((CheckingAccount) i).toString();
					buffer.write(str,15,((CheckingAccount) i).toString().length() + 24);
				}
				else {
					String str = i.getClass() + ",        " + ((SavingsAccount) i).toString();
					buffer.write(str,15,((SavingsAccount) i).toString().length() + 23);
				}
				buffer.newLine();
			}   

        
			buffer.close();         
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							file + "");                
		}
		catch (IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ file + "");                  
		}
	}
	
	public void sortByNumber() {
		Collections.sort(acts, new SortByNumber());
		fireTableRowsUpdated(0, acts.size());
	}
	
	public void sortByOwner() {
		Collections.sort(acts, new SortByOwner());
		fireTableRowsUpdated(0, acts.size());
	}

	public void sortByDate() {
		Collections.sort(acts, new SortByDate());
		fireTableRowsUpdated(0, acts.size());
	}
}