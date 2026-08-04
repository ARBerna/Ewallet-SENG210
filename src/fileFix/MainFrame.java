package src.fileFix;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

    public User actionU;

    public MainFrame(User authenticatedUser) {

        // Store the authenticated user (now includes userID)
        this.actionU = authenticatedUser;

        // Demo wages (you can remove these later)
        actionU.addWage(new Wage("Job A", 500, "January"));
        actionU.addWage(new Wage("Job B", 1200, "February"));
        actionU.addWage(new Wage("Side Work", 800, "March"));

        setTitle("E-Wallet App");
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                AddMonthlyIncomeFrame incomeFrame = new AddMonthlyIncomeFrame(actionU, w);
                incomeFrame.setVisible(true);
            });
        });

        addExpenseButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                Expense exp = new Expense("", 0, 1);
                AddExpenseFrame expenseFrame = new AddExpenseFrame(actionU, exp);
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

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // Start the Authentication Dialog
                AuthDialog auth = new AuthDialog(null);
                auth.setVisible(true);

                // Read validated authenticated user object
                User verifiedUser = auth.getAuthenticatedUser();

                if (verifiedUser != null) {
                    MainFrame frame = new MainFrame(verifiedUser);
                    frame.setVisible(true);
                } else {
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
