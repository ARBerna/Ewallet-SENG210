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
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PrintIncomeReport extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel IncomeReportPanel;

	//create ui elements
	JPanel      TitlePanel;
	JLabel      TitleLabel;
	JPanel      BorderPanel;
	JPanel      SelectionPanel;
	JPanel      MonthPanel;
	JPanel      SourcePanel;
	JLabel      MonthLabel;
	JComboBox   MonthComboBox;
	JLabel      SourceLabel;
	JComboBox   SourceComboBox;
	JPanel      SummaryPanel;
	JButton     ExitButton;
	static User actionU;
	private JTextArea SummaryText;
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User u = new User("User", "Pass");
					Wage wage = new Wage("test", 10.0, "December");
					u.addWage(wage);
					Wage wage2 = new Wage("test1", 11.0, "September");
					u.addWage(wage2);
					Wage wage3 = new Wage("test2", 10.5, "August");
					u.addWage(wage3);
					PrintIncomeReport frame = new PrintIncomeReport(u);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	//get the summary as a text string
	//also includes filtering, 0 is all for yearlyfrequency, "all" is all for source
	public static String getSummary (User u, String monthFilter, String sourceFilter) {
		
		actionU = u;
		double   total       = 0;
		double[] monthTotal  = new double[12];
		String   summary     = "";
		String   totals;
		ArrayList<Wage> w = u.getWages();
		int wIndex         = 1;
		
		//iterate through all expenses
		if (!w.isEmpty()) {
			summary = "\nIndividual Incomes:\n";
			
			for (int i = 0; i < w.size(); i++) {
				
				if     ((monthFilter.equals("All"))           || (w.get(i).Month  == monthFilter)) {
					if ((sourceFilter.equals("All")) || (w.get(i).source == sourceFilter)) {	
						//add to total and expIndex
						total    += w.get(i).amount;
						//add amount
						summary += (" " + (wIndex) + ". Amount: " + w.get(i).amount + ", ");
						wIndex ++;
						//add frequency
						summary += "Month: " + w.get(i).Month + ", ";
						if      (w.get(i).Month.equals("January"))   { monthTotal[0] += w.get(i).amount; }
						else if (w.get(i).Month.equals("February"))  { monthTotal[1] += w.get(i).amount; }
						else if (w.get(i).Month.equals("March"))     { monthTotal[2] += w.get(i).amount; }
						else if (w.get(i).Month.equals("April"))     { monthTotal[3] += w.get(i).amount; }
						else if (w.get(i).Month.equals("May"))       { monthTotal[4] += w.get(i).amount; }
						else if (w.get(i).Month.equals("June"))      { monthTotal[5] += w.get(i).amount; }
						else if (w.get(i).Month.equals("July"))      { monthTotal[6] += w.get(i).amount; }
						else if (w.get(i).Month.equals("August"))    { monthTotal[7] += w.get(i).amount; }
						else if (w.get(i).Month.equals("September")) { monthTotal[8] += w.get(i).amount; }
						else if (w.get(i).Month.equals("October"))   { monthTotal[9] += w.get(i).amount; }
						else if (w.get(i).Month.equals("November"))  { monthTotal[10] += w.get(i).amount; }
						else if (w.get(i).Month.equals("December"))  { monthTotal[11] += w.get(i).amount; }
						
						//add source
						summary += "Source: " + w.get(i).source + "\n";
					}
				}
			}
			//check if any expenses were found
			if (wIndex > 1) {
				totals = "Total expenses: " + total;
				if (monthFilter.equals("All")) {
					totals += "\nTotal yearly income: "    + total
							+ "\nTotal January income: "   + monthTotal[0]
							+ "\nTotal February income: "  + monthTotal[1]
							+ "\nTotal March income: "     + monthTotal[2]
							+ "\nTotal April income: "     + monthTotal[3]
							+ "\nTotal May income: "       + monthTotal[4]
							+ "\nTotal June income: "      + monthTotal[5]
							+ "\nTotal July income: "      + monthTotal[6]
							+ "\nTotal August income: "    + monthTotal[7]
							+ "\nTotal September income: " + monthTotal[8]
							+ "\nTotal October income: "   + monthTotal[9]
							+ "\nTotal November income: "  + monthTotal[10]
							+ "\nTotal December income: "  + monthTotal[11];
				}
			
				summary = totals + summary;
			}
			else {
				summary = "No income Found.";
			}
		}
		else {
			Random rnd = new Random();
			if (rnd.nextInt(100) == 0) {
				summary = "But alas,\n"
					    + "the income was naught to be found!\n"
					    + "...\n"
					    + "...Perhaps you should consider employment.";
				
			}
			else if (rnd.nextInt(100) == 97) {
				summary = "No income F-\n"
						+ "-Wait!...\n"
						+ "...\n"
						+ "...Oh, nevermind,\n"
						+ "I thought I saw a penny on the ground.";
			}
			else {
				summary = "No income Found.";
			}
		}
		return summary;
	}
	
	//get sources for combobox
	private static String[] getSources (User u) {
		
		ArrayList<String> sourcesAL = new ArrayList<String>();
		
		ArrayList<Wage> w = u.getWages();
		 
		if (w.size() > 0) {
			for (int i = 0; i < w.size(); i++) {
				if (!sourcesAL.contains(w.get(i).source)) {
					sourcesAL.add(w.get(i).source);
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
	public PrintIncomeReport(User u) {
		
		actionU = u;
		
		//make arrays
		String[] monthArray = new String[13];
		monthArray[0]  = "All";
		monthArray[1]  = "January";
		monthArray[2]  = "February";
		monthArray[3]  = "March";
		monthArray[4]  = "April";
		monthArray[5]  = "May";
		monthArray[6]  = "June";
		monthArray[7]  = "July";
		monthArray[8]  = "August";
		monthArray[9]  = "September";
		monthArray[10] = "October";
		monthArray[11] = "November";
		monthArray[12] = "December";
		String[] sourceArray = getSources(u);
		
		//make summary
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 450, 485);
		IncomeReportPanel = new JPanel();
		IncomeReportPanel.setBackground(new Color(236, 70, 47));
		IncomeReportPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(IncomeReportPanel);
		IncomeReportPanel.setLayout(null);
		
		//add title panel
		TitlePanel = new JPanel();
		TitlePanel.setBackground(new Color(0, 0, 0));
		TitlePanel.setBounds(0, 0, 436, 54);
		IncomeReportPanel.add(TitlePanel);
		TitlePanel.setLayout(new BorderLayout(0, 0));
		
		//add title label
		TitleLabel = new JLabel(" Income Report");
		TitleLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		TitleLabel.setFont(new Font("Zilla Slab Medium", Font.BOLD, 28));
		TitleLabel.setForeground(new Color(236, 70, 47));
		TitlePanel.add(TitleLabel);
		
		//add border panel
		BorderPanel = new JPanel();
		BorderPanel.setBackground(new Color(165, 27, 37));
		BorderPanel.setBounds(0, 52, 436, 9);
		IncomeReportPanel.add(BorderPanel);
		
		//add selection panel
		SelectionPanel = new JPanel();
		SelectionPanel.setBounds(new Rectangle(0, 0, 5, 5));
		SelectionPanel.setBackground(new Color(240, 94, 57));
		SelectionPanel.setBounds(10, 71, 416, 80);
		IncomeReportPanel.add(SelectionPanel);
		
		//add frequency panel
		MonthPanel = new JPanel();
		MonthPanel.setBackground(new Color(243, 124, 95));
		MonthPanel.setPreferredSize(new Dimension(150, 55));
		
		//add source panel
		SourcePanel = new JPanel();
		SourcePanel.setBackground(new Color(243, 124, 95));
		SourcePanel.setPreferredSize(new Dimension(150, 55));
		SelectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 13));
		SelectionPanel.add(MonthPanel);
		
		//add frequency label
		MonthLabel = new JLabel("Month:");
		MonthLabel.setForeground(new Color(45, 45, 45));
		MonthLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 16));
		MonthPanel.add(MonthLabel);
		
		//add frequency combo box
		MonthComboBox = new JComboBox(monthArray);
		MonthComboBox.setPreferredSize(new Dimension(120, 18));
		MonthPanel.add(MonthComboBox);
		SelectionPanel.add(SourcePanel);
		MonthComboBox.addActionListener(this);
		
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
		IncomeReportPanel.add(SummaryPanel);
		
		JScrollPane SummaryTextPane = new JScrollPane();
		SummaryTextPane.setPreferredSize(new Dimension(390, 203));
		SummaryPanel.add(SummaryTextPane);
		
		SummaryText = new JTextArea(getSummary(u, (String) MonthComboBox.getSelectedItem(), (String) SourceComboBox.getSelectedItem()));
		SummaryTextPane.setViewportView(SummaryText);
		 
		//add exit button
		ExitButton = new JButton("Exit");
		ExitButton.setBorderPainted(false);
		ExitButton.addActionListener((ActionListener) this);
		ExitButton.setBackground(new Color(0, 0, 0));
		ExitButton.setForeground(new Color(236, 70, 47));
		ExitButton.setFont(new Font("Roboto Medium", Font.BOLD, 16));
		ExitButton.setBounds(138, 403, 157, 32);
		IncomeReportPanel.add(ExitButton);

		java.net.URL iconURL = getClass().getResource("/fileFix/modified-noun-purse-3362985.png");
		System.out.println("Icon URL: " + iconURL);

		if (iconURL != null) {
		    ImageIcon icon = new ImageIcon(iconURL);
		    setIconImage(icon.getImage());
		} else {
		    System.out.println("Icon not found");
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		//update summary when element (other than exit) is updated
		if (e.getSource() != ExitButton) {
			SummaryText.setText(getSummary(actionU, (String) MonthComboBox.getSelectedItem(), (String) SourceComboBox.getSelectedItem()));
		}
		else {
			this.dispose();
			return;
		}
	}
}
