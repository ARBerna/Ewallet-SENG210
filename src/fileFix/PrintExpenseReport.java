package fileFix;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class PrintExpenseReport extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel ExpenseReportPanel;

	//create ui elements
	JPanel      TitlePanel;
	JLabel      TitleLabel;
	JPanel      BorderPanel;
	JPanel      SelectionPanel;
	JPanel      FrequencyPanel;
	JPanel      SourcePanel;
	JLabel      FrequencyLabel;
	JComboBox   FreqComboBox;
	JLabel      SourceLabel;
	JComboBox   SourceComboBox;
	JPanel      SummaryPanel;
	JButton     ExitButton;
	JTextArea   SummaryText;
	static User actionU;
	private JScrollPane SummaryScrollPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User u = new User("User", "Pass");
					Expense expense = new Expense("test", 10.0, 1);
					u.addExpense(expense);
					Expense expense2 = new Expense("test1", 11.0, 12);
					u.addExpense(expense2);
					Expense expense3 = new Expense("test2", 10.5, 24);
					u.addExpense(expense3);
					PrintExpenseReport frame = new PrintExpenseReport(u);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//get the summary as a text string
	//also includes filtering, 0 is all for yearlyfrequency, "all" is all for source
	private static String getSummary (User u, int freqFilter, String sourceFilter) {
		
		actionU = u;
		double total         = 0;
		double totalYear     = 0;
		double totalBiwk     = 0;
		double totalMonth    = 0;
		String summary       = "";
		String totals;
		ArrayList<Expense> e = u.getExpenses();
		int expIndex      = 1;
		
		//iterate through all expenses
		if (e.size() > 0) {
			summary = "\nIndividual Expenses:\n";
			
			for (int i = 0; i < e.size(); i++) {
				
				if     ((freqFilter == 0)            || (e.get(i).yearlyfrequency  == freqFilter)) {
					if ((sourceFilter.equals("All")) || (e.get(i).source == sourceFilter)) {	
						//add to total and expIndex
						total    += e.get(i).amount * e.get(i).yearlyfrequency;
						//add amount
						summary += (" " + (expIndex) + ". Price: " + e.get(i).amount + ", ");
						expIndex ++;
						//add frequency
						summary += "Frequency: ";
						switch(e.get(i).yearlyfrequency) {
							case 1:
								totalYear += e.get(i).amount;
								summary   += ("Yearly, ");
								break;
							case 12:
								totalMonth += e.get(i).amount;
								summary    += ("Monthly, ");
								break;
							case 24:
								totalBiwk += e.get(i).amount;
								summary   += ("Biweekly, ");
								break;
						}
						//add source
						summary += "Source: " + e.get(i).source + "\n";
					}
				}
			}
			//check if any expenses were found
			if (expIndex > 1) {
				totals = "Total expenses: " + total;
				if (freqFilter == 0) {
					totals += "\nTotal yearly expenses: " + totalYear + "\nTotal monthly expenses: " + totalMonth + "\nTotal biweekly expenses: " + totalBiwk + "\n";
				}
			
				summary = totals + summary;
			}
			else {
				summary = "No Expenses Found.";
			}
		}
		else {
			summary = "No Expenses Found.";
		}
		return summary;
	}
	
	//get sources for combobox
	private static String[] getSources (User u) {
		
		ArrayList<String> sourcesAL = new ArrayList<String>();
		
		ArrayList<Expense> e = u.getExpenses();
		 
		if (e.size() > 0) {
			for (int i = 0; i < e.size(); i++) {
				if (!sourcesAL.contains(e.get(i).source)) {
					sourcesAL.add(e.get(i).source);
				}	
			}
		}
		
		String[] sourcesArr = new String[sourcesAL.size() + 1];
		sourcesArr[0] = "All";
		
		for (int i = 0; i < sourcesAL.size(); i++) {
			sourcesArr[i + 1] = sourcesAL.get(i);
		}
		return sourcesArr;
	}
	
	/**
	 * Create the frame.
	 */
	public PrintExpenseReport(User u) {
		
		//make arrays
		String[] freqArray = new String[4];
		freqArray[0] = "All";
		freqArray[1] = "Yearly";
		freqArray[2] = "Biweekly";
		freqArray[3] = "Monthly";
		String[] sourceArray = getSources(u);
		
		//make summary
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 485);
		ExpenseReportPanel = new JPanel();
		ExpenseReportPanel.setBackground(new Color(236, 70, 47));
		ExpenseReportPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ExpenseReportPanel);
		ExpenseReportPanel.setLayout(null);
		
		//add title panel
		TitlePanel = new JPanel();
		TitlePanel.setBackground(new Color(0, 0, 0));
		TitlePanel.setBounds(0, 0, 436, 54);
		ExpenseReportPanel.add(TitlePanel);
		TitlePanel.setLayout(new BorderLayout(0, 0));
		
		//add title label
		TitleLabel = new JLabel(" Expense Report");
		TitleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		TitleLabel.setFont(new Font("Zilla Slab Medium", Font.BOLD, 28));
		TitleLabel.setForeground(new Color(236, 70, 47));
		TitlePanel.add(TitleLabel);
		
		//add border panel
		BorderPanel = new JPanel();
		BorderPanel.setBackground(new Color(165, 27, 37));
		BorderPanel.setBounds(0, 52, 436, 9);
		ExpenseReportPanel.add(BorderPanel);
		
		//add selection panel
		SelectionPanel = new JPanel();
		SelectionPanel.setBounds(new Rectangle(0, 0, 5, 5));
		SelectionPanel.setBackground(new Color(240, 94, 57));
		SelectionPanel.setBounds(10, 71, 416, 80);
		ExpenseReportPanel.add(SelectionPanel);
		
		//add frequency panel
		FrequencyPanel = new JPanel();
		FrequencyPanel.setBackground(new Color(243, 124, 95));
		FrequencyPanel.setPreferredSize(new Dimension(150, 55));
		
		//add source panel
		SourcePanel = new JPanel();
		SourcePanel.setBackground(new Color(243, 124, 95));
		SourcePanel.setPreferredSize(new Dimension(150, 55));
		SelectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 13));
		SelectionPanel.add(FrequencyPanel);
		
		//add frequency label
		FrequencyLabel = new JLabel("Frequency:");
		FrequencyLabel.setForeground(new Color(45, 45, 45));
		FrequencyLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 16));
		FrequencyPanel.add(FrequencyLabel);
		
		//add frequency combo box
		FreqComboBox = new JComboBox(freqArray);
		FreqComboBox.setPreferredSize(new Dimension(120, 18));
		FrequencyPanel.add(FreqComboBox);
		SelectionPanel.add(SourcePanel);
		FreqComboBox.addActionListener(this);
		
		//add source label
		SourceLabel = new JLabel("Source:");
		SourceLabel.setForeground(new Color(45, 45, 45));
		SourceLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 16));
		SourcePanel.add(SourceLabel);
		
		//add source combo box
		SourceComboBox = new JComboBox(sourceArray);
		SourceComboBox.setPreferredSize(new Dimension(120, 18));
		SourcePanel.add(SourceComboBox);
		SourceComboBox.addActionListener(this);
		
		//add summary panel
		SummaryPanel = new JPanel();
		FlowLayout fl_SummaryPanel = (FlowLayout) SummaryPanel.getLayout();
		fl_SummaryPanel.setVgap(15);
		SummaryPanel.setBackground(new Color(240, 94, 57));
		SummaryPanel.setBounds(10, 161, 416, 232);
		ExpenseReportPanel.add(SummaryPanel);
		
		SummaryScrollPane = new JScrollPane();
		SummaryScrollPane.setPreferredSize(new Dimension(390, 200));
		SummaryPanel.add(SummaryScrollPane);
		
		SummaryText = new JTextArea(getSummary(u, 0, (String) SourceComboBox.getSelectedItem()));
		SummaryText.setEditable(false);
		SummaryScrollPane.setViewportView(SummaryText);
		 
		//add exit button
		ExitButton = new JButton("Exit");
		ExitButton.setBorderPainted(false);
		ExitButton.addActionListener((ActionListener) this);
		ExitButton.setBackground(new Color(0, 0, 0));
		ExitButton.setForeground(new Color(236, 70, 47));
		ExitButton.setFont(new Font("Roboto Medium", Font.BOLD, 16));
		ExitButton.setBounds(138, 403, 157, 32);
		ExpenseReportPanel.add(ExitButton);
		
		//why wouldnt it work hahaha - BF
		setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		//update summary when element (other than exit) is updated
		if (e.getSource() != ExitButton) {
			//get frequency as int
			int freqInt          = 0;
			String selectedFreak = (String) FreqComboBox.getSelectedItem();
			if (selectedFreak.equals("Yearly")) {
				freqInt = 1;
			}
			else if (selectedFreak.equals("Monthly")) {
				freqInt = 12;
			}
			else if (selectedFreak.equals("Biweekly")) {
				freqInt = 24;
			}
				
			SummaryText.setText(getSummary(actionU, freqInt, (String) SourceComboBox.getSelectedItem()));
		}
		else {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //close window
			return;
		}
	}
}
