package fileFix;

import javax.swing.*;


import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {

	public User actionU;

	public MainFrame() {

		// addeder here for a demo user as hardcoded
		actionU = new User("test", "123");

		actionU.addWage(new Wage("Job A", 500, "January"));
		actionU.addWage(new Wage("Job B", 1200, "February"));
		actionU.addWage(new Wage("Side Work", 800, "March"));

		setTitle("E-Wallet App");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(3, 1, 10, 10));

		JButton savingBtn = new JButton("Saving Calculator");

		JButton currencyConversion = new JButton("Currency Convertor");

		JButton incomeReportButton = new JButton("Income Report");

		JButton exportIncomeButton = new JButton("Export Income Report");

		JButton expenseReportButton = new JButton("Expense Report");

		JButton addMonthlyIncomeButton = new JButton("Add Monthly Income");

		JButton addExpenseButton = new JButton("Add Expense");

		savingBtn.addActionListener(e -> {
			new SavingCalcFrame();
		});

		currencyConversion.addActionListener(e -> {
			new CurrencyConversionFrame();
		});

		incomeReportButton.addActionListener(e -> {
			SwingUtilities.invokeLater(() -> {
				new PrintIncomeReport(actionU).setVisible(true); // foced open was not showing
			});
		});

		exportIncomeButton.addActionListener(e -> {

			String report = PrintIncomeReport.getSummary(actionU, "All", "All");

			ReportExporter.exportTextToFile(report, "income_report.txt");
		});

		expenseReportButton.addActionListener(e -> {

			SwingUtilities.invokeLater(() -> {
				new PrintExpenseReport(actionU).setVisible(true); // forced again
			});
		});

		addMonthlyIncomeButton.addActionListener(e -> {
			Wage w = new Wage("", 0.0, ""); // had to add this empty
			new addMonthlyIncome(actionU, w);
		});
		
		addExpenseButton.addActionListener( e -> {
			Expense exp = new Expense("", 0, 1); // another empty
			new addExpense(actionU, exp); //should open frame now
		});
		
		add(addExpenseButton);
		add(addMonthlyIncomeButton);
		add(expenseReportButton);
		add(exportIncomeButton);
		add(incomeReportButton);
		add(currencyConversion);
		add(savingBtn);

		java.net.URL iconURL = getClass().getResource("/fileFix/modified-noun-purse-3362985.png");
		System.out.println("Icon URL: " + iconURL);

		if (iconURL != null) {
		    ImageIcon icon = new ImageIcon(iconURL);
		    setIconImage(icon.getImage());
		} else {
		    System.out.println("Icon not found");
		}
        
        setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();

	}
}
