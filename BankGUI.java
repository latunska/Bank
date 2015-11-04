package Project3;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;
/*****************************************************************
This class creates a GUI that allows users to add two different
types of accounts, and also to modify, save, load, and sort
those accounts.
 
@author Alex
@author Carolyn
@version November 2015
******************************************************************/


public class BankGUI extends JFrame{
		
	/** buttons to add, delete, update, and clear account fields */
	private JButton add, delete, update, clear;
	
	/** array of textfields */
	private JTextField[] fields;
	
	/** array of JLabels */
	private JLabel[] labels;
	
	/** Array of strings that holds the text to go in labels */
	private String labelTitles[];
	
	/** A menu bar */
	private JMenuBar menuBar;
	
	/** Menus for file and sort */
	private JMenu file, sort;
	
	/** Menu items for various sorts, saves, and loads */
	private JMenuItem sortNumber, sortOwner, sortDate, saveText,
		loadText, saveBinary, loadBinary, saveXML, loadXML;
	
	/** JPanels used to contain all content */
	private JPanel buttons, info, totalInfo, userInput, table, main;
	
	/** Radio buttons for checking and savings */
	private JRadioButton checking, savings;
	
	/** The action listener */
	private Listener listener;
	
	/** The bank model */
	private BankModel model;
	
	/** Either the JList or JTable */
//	private JList jListArea;
	private JTable jTableArea;
	
	/** int that holds where on the list or table user has clicked */
	private int index;
	
	/** boolean to make user click each account to delete it */
	private boolean changeable;
	
	/** a calendar */
	private JDateChooser calendar;
	
	/*****************************************************************
	Main method that constructs JFrame
	
	@param args
	******************************************************************/
	public static void main(String[] args) {
		BankGUI frame = new BankGUI ("Accounts");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 600);
		frame.setVisible(true);
	}
	
	/*****************************************************************
	Constructor for GUI. Sets up all components of GUI.
	
	@param title sets title of JFrame
	******************************************************************/
	public BankGUI(String title) {
		
		index = -1;
		
		listener = new Listener();
		
		setTitle(title);
		setLayout(new GridLayout(2, 1));
        
        formatMenus();
        
		userInput = new JPanel();
		totalInfo = new JPanel();
		totalInfo.setLayout(new GridLayout(2, 1));
		
		table = new JPanel();
		table.setLayout(new GridLayout(1, 1));
		
        addButtons();
        addInfo();
        
        model = new BankModel();
        jTableArea = new JTable(model);
//      jListArea = new JList(model);
//      JScrollPane scrollPane = new JScrollPane(jListArea);
        JScrollPane scrollPane = new JScrollPane(jTableArea);
        table.add(scrollPane);

        add(table);
        
        userInput.add(info, BorderLayout.EAST);
        
		userInput.add(buttons, BorderLayout.WEST);
		
		add(userInput);
		
//		MouseListener mouseListener = new MouseAdapter() {
//		     public void mouseClicked(MouseEvent e) {
//		         if (e.getClickCount() == 1) {
//		             index = jListArea.locationToIndex(e.getPoint());
//		             
//		             if (model.getElementAt(index) instanceof 
//		            		 SavingsAccount) {
//		        		 isSavings();
//		        		 double rate = (((SavingsAccount)model.
//		        			    getElementAt(index)).getInterestRate());
//		        		 double minBal = (((SavingsAccount)model.
//		        				getElementAt(index)).getInterestRate());
//		        		 fields[5].setText(Double.toString(rate));
//		        		 fields[6].setText(Double.toString(minBal));
//		        	 }
//		             if (model.getElementAt(index) instanceof 
//		            		 CheckingAccount) {
//		            	 isChecking();
//		            	 double fee = (((CheckingAccount)model.
//		            			 getElementAt(index)).getMonthlyFee());
//		            	 fields[4].setText(Double.toString(fee));
//		             }
//		             
//		             int num = (((Account)model.getElementAt(index))
//		            		 .getNumber());
//		             String owner = (((Account)model.getElementAt
//		            		 (index)).getOwner());
//		             double balance = (((Account)model.getElementAt
//		            		 (index)).getBalance());
//		             fields[0].setText(Integer.toString(num));
//		             fields[1].setText(owner);
//					 calendar.setCalendar(((Account) model.getElementAt
//					 (index)).getDateOpened());
//		             fields[3].setText(Double.toString(balance));
//		             
//		             changeable = true;
//		          }
//		     }
//		 };
//		 jListArea.addMouseListener(mouseListener);
//	}
		/**************************************************************
		Creates an object that informs the GUI if a point on the JTable
		has been clicked.
		**************************************************************/
	
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					index = jTableArea.rowAtPoint(e.getPoint());
	             
					if (model.getValueAt(index, 4) instanceof String) {
						isSavings();
						fields[4].setText((String)model.getValueAt
								(index, 4));
					}
					else if (model.getValueAt(index, 4) instanceof 
							Double) {
						isChecking();
						fields[4].setText(Double.toString((double)model
								.getValueAt(index, 4)));
					}
					if (model.getValueAt(index, 5) instanceof String) {
						fields[5].setText((String)model.getValueAt
								(index, 5));
					}
					else if (model.getValueAt(index, 5) instanceof 
							Double) {
						fields[5].setText(Double.toString((double)model
								.getValueAt(index, 5)));
					}
					if (model.getValueAt(index, 6) instanceof String) {
						fields[6].setText((String)model.getValueAt
								(index, 6));
					}
					else if (model.getValueAt(index, 6) instanceof 
							Double) {
						fields[6].setText(Double.toString((double)model
								.getValueAt(index, 6)));
					} 
					fields[0].setText(Integer.toString((int)model
							.getValueAt(index, 0)));
					fields[1].setText((String)model.getValueAt
							(index, 1));
					SimpleDateFormat sdf = new SimpleDateFormat
							("MM/dd/yyyy");
					Date date = null;
					try {
						date = sdf.parse((String)model.getValueAt
								(index, 2));
					} catch (ParseException x) {
						
						x.printStackTrace();
					}
					calendar.setDate(date);
					fields[3].setText(Double.toString((double)model
							.getValueAt(index, 3)));
	             
					changeable = true;
				}
			}
		};
		jTableArea.addMouseListener(mouseListener);
	}
	
	/*****************************************************************
	Method that puts components in correct states for a savings
	account being selected
	******************************************************************/
	private void isSavings() {
		clear();
		savings.setSelected(true);
		fields[4].setEnabled(false);
		fields[5].setEnabled(true);
		fields[6].setEnabled(true);
	}
	
	/*****************************************************************
	Method that puts components in correct states for a checking
	account being selected
	******************************************************************/
	private void isChecking() {
		clear();
		checking.setSelected(true);
		fields[4].setEnabled(true);
		fields[5].setEnabled(false);
		fields[6].setEnabled(false);
	}
	
	/*****************************************************************
	Clears all text fields
	******************************************************************/
	private void clear() {
		for (int i = 0; i < 7; i++) {
			fields[i].setText("");
		}
	}
	
	/*****************************************************************
	Instantiates and organizes menus
	******************************************************************/
	private void formatMenus() {
		menuBar = new JMenuBar();
        file = new JMenu("File");
        sort = new JMenu("Sort");
        
        sortNumber = new JMenuItem("By Account Number");
        sortOwner = new JMenuItem("By Account Owner");
        sortDate = new JMenuItem("By Date Opened");
        
        //Registers menu items with action listener
        sortNumber.addActionListener(listener);
        sortOwner.addActionListener(listener);
        sortDate.addActionListener(listener);
        
        //Adds menu items to menu
        sort.add(sortNumber);
        sort.add(sortOwner);
        sort.add(sortDate);

        loadBinary = new JMenuItem("Load from Binary");
        saveBinary = new JMenuItem("Save as Binary");
        loadText = new JMenuItem("Load from Text");
        saveText = new JMenuItem("Save as Text");
        loadXML = new JMenuItem("Load from XML");
        saveXML = new JMenuItem("Save as XML");
        
        //Registers menu items with action listener
        saveBinary.addActionListener(listener);
        loadBinary.addActionListener(listener);
        saveText.addActionListener(listener);
        loadText.addActionListener(listener);
        saveXML.addActionListener(listener);
        loadXML.addActionListener(listener);
        
        //Adds menu items to items
        file.add(loadBinary);
        file.add(saveBinary);
        file.add(loadText);
        file.add(saveText);
        file.add(loadXML);
        file.add(saveXML);
        
        
		menuBar.add(file);
        menuBar.add(sort);
        setJMenuBar(menuBar);
	}
	
	/*****************************************************************
	Instantiates and organizes buttons.
	******************************************************************/
	private void addButtons() {
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(4, 1));
		
		buttons.setOpaque(true);
		buttons.setBackground(Color.DARK_GRAY);
		
		add = new JButton("Add");
		add.addActionListener(listener);
		buttons.add(add);
		
		delete = new JButton("Delete");
		delete.addActionListener(listener);
		buttons.add(delete);
		
		update = new JButton("Update");
		update.addActionListener(listener);
		buttons.add(update);
		
		clear = new JButton("Clear");
		clear.addActionListener(listener);
		buttons.add(clear);
	}
	
	/*****************************************************************
	Instantiates and organizes text fields and corresponding labels
	******************************************************************/
	private void addInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			date = sdf.parse("11/04/2015");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		info = new JPanel();
		info.setLayout(new GridLayout(8, 2));
		
		//sets up the calendar button and initial date
		calendar = new JDateChooser(date);
		calendar.setDate(date);
		
		//Instantiates and groups radio buttons
		ButtonGroup group = new ButtonGroup();
		checking = new JRadioButton("Checking", true);
		savings = new JRadioButton("Savings", false);
		group.add(checking);
		group.add(savings);
		
		//Adds radio buttons to panel and adds listener
		info.add(checking);
		checking.addActionListener(listener);
		info.add(savings);
		savings.addActionListener(listener);
		
		//Creates labels and names labels
		labels = new JLabel[7];
		labelTitles = new String[] {"Account Number: ","Account Owner: "
				,"Date Opened: ", "Current Balance: ", "Monthly Fee: ",
				"Interest Rate: ", "Minimum Balance: "};
		
		fields = new JTextField[7];
		
		//Adds text fields and labels
		for(int i = 0; i < 7; i++) {
			labels[i] = new JLabel(labelTitles[i]);
			info.add(labels[i]);

			fields[i] = new JTextField(10);
			if (i == 2) {
				info.add(calendar);
			} else {
				info.add(fields[i]);
			}
		}
		
		fields[5].setEnabled(false);
		fields[6].setEnabled(false);
	}
	
	/*****************************************************************
	ActionListener for the BankGUI class
	
	@author Carolyn
	@author Alex
	******************************************************************/
	private class Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == add) {
				Boolean flag = false;
				int type = 0;
				int number = 0;
				String owner = "";
				GregorianCalendar cal = null;
				double bal = 0;
				double fee = 0;
				double rate = 0;
				double min = 0;
				
				//Tries to parse fields
				try {
					number = Integer.parseInt(fields[0].getText());
					owner = fields[1].getText();
					bal = Double.parseDouble(fields[3].getText());
					// pulls the Date opened from the text field
					cal = (GregorianCalendar) calendar.getCalendar();
					System.out.println("Here " + cal);
					if (checking.isSelected()) {
						type = 0;
						//Tries to parse fields and add account
						try {
							fee = Double.parseDouble(fields[4]
									.getText());
							model.addAccount(type, number, owner, cal, 
									bal, fee, rate, min);
						}
						catch (NumberFormatException n){
							throw n;
						}
					}
					//Tries to parse fields and add account	
					if (savings.isSelected()) {
						type = 1;
						try {
							rate = Double.parseDouble(fields[5]
									.getText());
							min = Double.parseDouble(fields[6]
									.getText());
							if (bal < min) {
								throw new IllegalArgumentException();
							}
							model.addAccount(type, number, owner, cal, 
									bal, fee, rate, min);
						}
						catch (NumberFormatException n){
							throw n;
						}
						catch (IllegalArgumentException a) {
							throw a;
						}
					}
				}
				catch (NumberFormatException n){
					JOptionPane.showMessageDialog(null, "Either a "
								+ "field is empty or contains incorrect"
								+ " information (ex. 'a' for balance.");
				}
				catch (IllegalArgumentException a) {
					JOptionPane.showMessageDialog(null, "Your balance "
							+ "is below the minimum balance allowed.");
				}
				//Check on this
				jTableArea.setModel(model);
			}
			if (e.getSource() == clear) {
				clear();
			}
			if (e.getSource() == checking) {
				isChecking();
			}
			if (e.getSource() == savings) {
				isSavings();
			}
			if (e.getSource() == update) {
				if (index >= 0) {
					int actNum = Integer.parseInt(fields[0].getText());
					String name = fields[1].getText();
					GregorianCalendar cal = (GregorianCalendar) 
							calendar.getCalendar();
					double bal = Double.parseDouble(fields[3]
							.getText());
					double monthFee = 0;
					double interestRate = 0;
					double minBal = 0;
					if (checking.isSelected()) {
						monthFee = Double.parseDouble(fields[4]
								.getText());
					}
					if (savings.isSelected()) {
						interestRate = Double.parseDouble(fields[5]
								.getText());
						minBal = Double.parseDouble(fields[6]
								.getText());
					}
					model.updateAccount(index, actNum, name, cal, bal,
							monthFee, interestRate, minBal);
				}
			}
			
			if (e.getSource() == delete) {
				if (changeable) {
					model.deleteAccount(index);
					changeable = false;
					index = -1;
				}
			}
			
			if (e.getSource() == saveBinary) {
				try {
					model.saveBinary("BinTemp");
				}
				catch (IOException x) {
					JOptionPane.showMessageDialog(null, "Did not save "
							+ "properly");
				}
			}
			
			if (e.getSource() == loadBinary) {
				try {
					model.loadBinary("BinTemp");
				}
				catch (IOException x) {
					JOptionPane.showMessageDialog(null, "Did not load "
							+ "properly.");
				}
			}
			
			if (e.getSource() == saveText) {
				try {
					model.saveText("TextTemp.txt");
				}
				catch (IOException x) {
					JOptionPane.showMessageDialog(null, "Did not save "
							+ "properly");
				}
			}

			if (e.getSource() == loadText) {
				model.loadText("TextTemp.txt");
			}

			if (e.getSource() == sortNumber) {
				model.sortByNumber();
				index = -1;
			}
			
			if (e.getSource() == sortOwner) {
				model.sortByOwner();
				index = -1;
			}
			
			if (e.getSource() == sortDate) {
				model.sortByDate();
				index = -1;
			}
		}
	}
}