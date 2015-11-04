package Project3;

//Need to create drop down calendar, convert to JTable at some point

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.TableModel;
import com.toedter.calendar.JDateChooser;

public class BankGUI extends JFrame {


	private static final long serialVersionUID = 1L;

	/** buttons to add, delete, update, and clear account fields */
	private JButton add, delete, update, clear;

	/** array of textfields */
	private JTextField[] fields;

	private JLabel[] labels;

	private String labelTitles[];

	private JMenuBar menuBar;

	private JMenu file, sort;

	private JMenuItem sortNumber, sortOwner, sortDate, saveText, loadText, saveBinary, loadBinary, saveXML, loadXML;

	private JPanel buttons, info, totalInfo, userInput, table, main;

	private JRadioButton checking, savings;

	private Listener listener;

	private BankModel model;

	private JList jListArea;

	private int index;

	private boolean changeable;

	private JDateChooser calendar;

	public static void main(String[] args) {
		BankGUI frame = new BankGUI("Accounts");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public BankGUI(String title) {

		index = -1;

		listener = new Listener();

		setTitle(title);
		setLayout(new GridLayout(2, 1));

		formatMenus();

		userInput = new JPanel();
		// userInput.setLayout(new FlowLayout());
		totalInfo = new JPanel();
		totalInfo.setLayout(new GridLayout(2, 1));

		table = new JPanel();
		table.setLayout(new GridLayout(1, 1));

		addButtons();
		addInfo();

		model = new BankModel();
		jListArea = new JList(model);
		JScrollPane scrollPane = new JScrollPane(jListArea);
		table.add(scrollPane);

		add(table);

		userInput.add(info, BorderLayout.EAST);

		userInput.add(buttons, BorderLayout.WEST);

		add(userInput);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					index = jListArea.locationToIndex(e.getPoint());

					if (model.getElementAt(index) instanceof SavingsAccount) {
						isSavings();
						double rate = (((SavingsAccount) model.getElementAt(index)).getInterestRate());
						double minBal = (((SavingsAccount) model.getElementAt(index)).getInterestRate());
						fields[5].setText(Double.toString(rate));
						fields[6].setText(Double.toString(minBal));
					}
					if (model.getElementAt(index) instanceof CheckingAccount) {
						isChecking();
						double fee = (((CheckingAccount) model.getElementAt(index)).getMonthlyFee());
						fields[4].setText(Double.toString(fee));
					}

					int num = (((Account) model.getElementAt(index)).getNumber());
					String owner = (((Account) model.getElementAt(index)).getOwner());
					double balance = (((Account) model.getElementAt(index)).getBalance());
					fields[0].setText(Integer.toString(num));
					fields[1].setText(owner);
					calendar.setCalendar(((Account) model.getElementAt(index)).getDateOpened());
					fields[3].setText(Double.toString(balance));

					changeable = true;
				}
			}
		};
		jListArea.addMouseListener(mouseListener);
	}

	private void isSavings() {
		clear();
		savings.setSelected(true);
		fields[4].setEnabled(false);
		fields[5].setEnabled(true);
		fields[6].setEnabled(true);
	}

	private void isChecking() {
		clear();
		checking.setSelected(true);
		fields[4].setEnabled(true);
		fields[5].setEnabled(false);
		fields[6].setEnabled(false);
	}

	private void clear() {
		for (int i = 0; i < 7; i++) {
			fields[i].setText("");
		}
	}

	private void formatMenus() {
		menuBar = new JMenuBar();
		file = new JMenu("File");
		sort = new JMenu("Sort");

		sortNumber = new JMenuItem("By Account Number");
		sortOwner = new JMenuItem("By Account Owner");
		sortDate = new JMenuItem("By Date Opened");

		sortNumber.addActionListener(listener);
		sortOwner.addActionListener(listener);
		sortDate.addActionListener(listener);

		sort.add(sortNumber);
		sort.add(sortOwner);
		sort.add(sortDate);

		loadBinary = new JMenuItem("Load from Binary");
		saveBinary = new JMenuItem("Save as Binary");
		loadText = new JMenuItem("Load from Text");
		saveText = new JMenuItem("Save as Text");
		loadXML = new JMenuItem("Load from XML");
		saveXML = new JMenuItem("Save as XML");

		saveBinary.addActionListener(listener);
		loadBinary.addActionListener(listener);
		saveText.addActionListener(listener);
		loadText.addActionListener(listener);
		saveXML.addActionListener(listener);
		loadXML.addActionListener(listener);

		file.add(loadBinary);
		file.add(saveBinary);
		file.add(loadText);
		file.add(saveText);
		file.add(loadXML);
		file.add(saveXML);
		// Add JMenuItems

		menuBar.add(file);
		menuBar.add(sort);
		setJMenuBar(menuBar);
	}

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

	private void addInfo() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date =null;
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
		

		// Instantiates and groups radio buttons
		ButtonGroup group = new ButtonGroup();
		checking = new JRadioButton("Checking", true);
		savings = new JRadioButton("Savings", false);
		group.add(checking);
		group.add(savings);

		// Adds radio buttons to panel and adds listener
		info.add(checking);
		checking.addActionListener(listener);
		info.add(savings);
		savings.addActionListener(listener);

		labels = new JLabel[7];
		labelTitles = new String[] { "Account Number: ", "Account Owner: ", "Date Opened: ", "Current Balance: ",
				"Monthly Fee: ", "Interest Rate: ", "Minimum Balance: " };

		fields = new JTextField[7];

		// Adds text fields and labels
		for (int i = 0; i < 7; i++) {
			labels[i] = new JLabel(labelTitles[i]);
			info.add(labels[i]);

			fields[i] = new JTextField(10);
			labels[i] = new JLabel(labelTitles[i]);
			if (i == 2) {
				info.add(calendar);
			} else {
				info.add(fields[i]);
			}

		}

		fields[5].setEnabled(false);
		fields[6].setEnabled(false);
	}

	private class Listener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == add) {
				Boolean flag=false; 
				int type = 0;
				int number = 0;
				String owner = "";
				GregorianCalendar cal = null;
				double bal = 0;
				double fee = 0;
				double rate = 0;
				double min = 0;

				try {
					number = Integer.parseInt(fields[0].getText());
					owner = fields[1].getText();
					bal = Double.parseDouble(fields[3].getText());
					
					// pulls the Date opened from the text field
					 cal = (GregorianCalendar) calendar.getCalendar();
					 System.out.println(calendar.getCalendar()+"");

				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(null, "A field is empty.");
					flag=true;
				}

				if (checking.isSelected()) {
					type = 0;
					try {
						fee = Double.parseDouble(fields[4].getText());
					} catch (NumberFormatException n) {
						JOptionPane.showMessageDialog(null, "A field is empty.");
						flag=true;
					}
				}

				if (savings.isSelected()) {
					type = 1;
					try {
						rate = Double.parseDouble(fields[5].getText());
						min = Double.parseDouble(fields[6].getText());
					} catch (NumberFormatException n) {
						JOptionPane.showMessageDialog(null, "A field is empty.");
						flag=true;
					}
				}
				if(!flag){
					model.addAccount(type, number, owner, cal, bal, fee, rate, min);
					jListArea.setModel(model);
				}
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
				if (index > 0) {
					int actNum = Integer.parseInt(fields[0].getText());
					String name = fields[1].getText();
					GregorianCalendar cal = (GregorianCalendar) calendar.getCalendar();
					double bal = Double.parseDouble(fields[3].getText());
					double monthFee = 0;
					double interestRate = 0;
					double minBal = 0;
					if (checking.isSelected()) {
						monthFee = Double.parseDouble(fields[4].getText());
					}
					if (savings.isSelected()) {
						interestRate = Double.parseDouble(fields[5].getText());
						minBal = Double.parseDouble(fields[6].getText());
					}
					model.updateAccount((Account) model.getElementAt(index), actNum, name, cal, bal, monthFee,
							interestRate, minBal);
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
					System.out.println("Saved to Temp");
				} catch (IOException x) {
					JOptionPane.showMessageDialog(null, "Did not save properly");
				}
			}

			if (e.getSource() == loadBinary) {
				try {
					model.loadBinary("BinTemp");
					System.out.println("Loaded file");
				} catch (IOException x) {
					JOptionPane.showMessageDialog(null, "Did not load properly.");
				}
			}

			if (e.getSource() == saveText) {
				try {
					model.saveText("Temp.txt");
					System.out.println("Saved to Temp");
				} catch (IOException x) {
					JOptionPane.showMessageDialog(null, "Did not save properly");
				}
			}

			if (e.getSource() == loadText) {
				model.loadText("Temp.txt");
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