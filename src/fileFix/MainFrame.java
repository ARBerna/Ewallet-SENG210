package fileFix;

import javax.swing.*;


import java.awt.*;

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
	            SwingUtilities.invokeLater(SavingCalcFrame::new);
	        });

	        currencyConversion.addActionListener(e -> {
	            SwingUtilities.invokeLater(CurrencyConversionFrame::new);
	        });

	        incomeReportButton.addActionListener(e -> {
	            SwingUtilities.invokeLater(() -> new PrintIncomeReport(actionU).setVisible(true));
	        });

	        exportIncomeButton.addActionListener(e -> {
	            String report = PrintIncomeReport.getSummary(actionU, "All", "All");
	            ReportExporter.exportTextToFile(report, "income_report.txt");
	        });

	        expenseReportButton.addActionListener(e -> {
	            SwingUtilities.invokeLater(() -> new PrintExpenseReport(actionU).setVisible(true));
	        });

	        addMonthlyIncomeButton.addActionListener(e -> {
	            SwingUtilities.invokeLater(() -> {
	                Wage w = new Wage("", 0.0, ""); 
	                addMonthlyIncome incomeFrame = new addMonthlyIncome(actionU, w);
	                incomeFrame.setVisible(true); 
	            });
	        });
	        
	        addExpenseButton.addActionListener(e -> {
	            SwingUtilities.invokeLater(() -> {
	                Expense exp = new Expense("", 0, 1); 
	                addExpense expenseFrame = new addExpense(actionU, exp);
	                expenseFrame.setVisible(true); 
	            });
	        });
		
		add(addExpenseButton);
		add(addMonthlyIncomeButton);
		add(expenseReportButton);
		add(exportIncomeButton);
		add(incomeReportButton);
		add(currencyConversion);
		add(savingBtn);
		setVisible(true);
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			try {
				new MainFrame().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		

	}
}
