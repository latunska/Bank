package Project3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.TableModel;

public class BankGUI extends JFrame{
		
	/** buttons to add, delete, update, and clear account fields */
	private JButton add, delete, update, clear;
	
	/** array of textfields */
	private JTextField[] fields;
	
	private JLabel[] labels;
	
	private String labelTitles[];
		
	private JPanel buttons, info, totalInfo, userInput, table;
	
	private JRadioButton checking, savings;
	
	private Listener listener;
	
	private BankModel model;
	
	private JList jListArea;
	
	public static void main(String[] args) {
		BankGUI frame = new BankGUI ("Accounts");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public BankGUI(String title) {
		
		listener = new Listener();
		
		setTitle(title);
        setLayout(new GridLayout(2, 1));
        
		userInput = new JPanel();
	//	userInput.setLayout(new FlowLayout());
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
		info = new JPanel();
		info.setLayout(new GridLayout(8, 2));
		
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
		
		labels = new JLabel[7];
		labelTitles = new String[] {"Account Number: ","Account Owner: "
				,"Date Opened: ", "Current Balance: ", "Monthly Fee: ",
				"Interest Rate: ", "Minimum Balance: "};
		
		fields = new JTextField[7];
		
		//Adds text fields and labels
		for(int i = 0; i < 7; i++) {
			labels[i] = new JLabel(labelTitles[i]);
			info.add(labels[i]);
//			if (i == 3) {
//				info.add(arg0)
//			}
			fields[i] = new JTextField(10);
			info.add(fields[i]);
		}
		
		fields[5].setEnabled(false);
		fields[6].setEnabled(false);
	}
	
	private class Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			model = new BankModel();
			
			if (e.getSource() == add) {
				int type = 0;
				int number = 0;
				String owner = "";
				GregorianCalendar cal = new GregorianCalendar(2015, 10, 10);
				double bal = 0;
				double fee = 0;
				double rate = 0;
				double min = 0;
				
				try {
					number = Integer.parseInt(fields[0].getText());
					owner = fields[1].getText();
					bal = Double.parseDouble(fields[3].getText());
				}
				catch (NumberFormatException n){
					JOptionPane.showMessageDialog(null, "A field is empty.");
				}
				
				if (checking.isSelected()) {
					type = 0;
					try {
						fee = Double.parseDouble(fields[4].getText());
					}
					catch (NumberFormatException n){
						JOptionPane.showMessageDialog(null, "A field is empty.");
					}
				}
					
				if (savings.isSelected()) {
					type = 1;
					try {
						rate = Double.parseDouble(fields[5].getText());
						min = Double.parseDouble(fields[6].getText());
					}
					catch (NumberFormatException n){
						JOptionPane.showMessageDialog(null, "A field is empty.");
					}
				}
				model.addAccount(type, number, owner, cal, bal, fee,
						rate, min);
				jListArea.setModel(model);
			}
			if (e.getSource() == clear) {
				for (int i = 0; i < 7; i++) {
					fields[i].setText("");
				}
			}
			if (e.getSource() == checking) {
				fields[4].setEnabled(true);
				fields[5].setEnabled(false);
				fields[6].setEnabled(false);
			}
			if (e.getSource() == savings) {
				fields[4].setEnabled(false);
				fields[5].setEnabled(true);
				fields[6].setEnabled(true);
			}
		}
	}
}