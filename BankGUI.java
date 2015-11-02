package Project3;

import java.awt.*;

import javax.swing.*;

public class BankGUI extends JFrame{
		
	/** buttons to add, delete, update, and clear account fields */
	private JButton add, delete, update, clear;
	
	/** array of textfields */
	private JTextField[] fields;
	
	private JLabel[] labels;
	
	private String labelTitles[];
		
	private JPanel buttons, info, userInput, table, main;
	
	private GridBagConstraints loc;
	
	public static void main(String[] args) {
		BankGUI frame = new BankGUI ("Accounts");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
	
	public BankGUI(String title) {
		
		setTitle(title);
        setLayout(new GridBagLayout());
        
        loc = new GridBagConstraints();
        loc.anchor = GridBagConstraints.NORTH;     
        loc.insets = new Insets(5, 5, 5, 5);
        
		userInput = new JPanel();
		userInput.setLayout(new GridBagLayout());
		
        addButtons();
        addInfo();
        
        loc.gridx = 0;
        userInput.add(info, loc);
        
        loc.gridx = 1;
		userInput.add(buttons, loc);
		
		add(userInput);
	}
	
	private void addButtons() {
		buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
		
		buttons.setOpaque(true);
		buttons.setBackground(Color.DARK_GRAY);
		
		loc.insets = new Insets(5, 5, 5, 5);
		
		add = new JButton("Add");
		loc.gridy = 0;
		buttons.add(add, loc);
		
		delete = new JButton("Delete");
		loc.gridy = 1;
		buttons.add(delete, loc);
		
		update = new JButton("Update");
		loc.gridy = 2;
		buttons.add(update, loc);
		
		clear = new JButton("Clear");
		loc.gridy = 3;
		buttons.add(clear, loc);
	}
	
	private void addInfo() {
		info = new JPanel();
		info.setLayout(new GridBagLayout());
		
		loc.insets = new Insets(2, 2, 2, 2);
		loc.gridx = 0;
		
		labels = new JLabel[7];
		labelTitles = new String[] {"Account Number: ","Account Owner: "
				,"Date Opened: ", "Current Balance: ", "Monthly Fee: ",
				"Interest Rate: ", "Minimum Balance: "};
		
		for(int i = 0; i < 7; i++) {
			labels[i] = new JLabel(labelTitles[i]);
			loc.gridy = i;
			info.add(labels[i], loc);
		}
		
		loc.gridx = 1;
		
		fields = new JTextField[7];
		for (int i = 0; i < 7; i++) {
			fields[i] = new JTextField(10);
			loc.gridy = i;
			info.add(fields[i], loc);
		}
	}
}
