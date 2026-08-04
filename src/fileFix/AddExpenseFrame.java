package src.fileFix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class AddExpenseFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JPanel titlePanel;
    JLabel titleLabel;
    JPanel borderPanel;
    JPanel bodyPanel;
    JPanel inputsPanel;

    JPanel amountPanel;
    JPanel freqPanel;
    JPanel sourcePanel;
    JPanel descriptionPanel;

    JLabel amountLabel;
    JLabel freqLabel;
    JLabel sourceLabel;
    JLabel descriptionLabel;

    JComboBox<String> freqCombo;
    JSpinner amountSpinner;
    JTextField sourceText;
    JTextField descriptionText;

    JButton cancelButton;
    JButton confirmButton;

    Expense expenseObject;
    User userObject;

    public AddExpenseFrame(User u, Expense E) {

        //setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Window size
        setBounds(100, 100, 700, 260);

        JPanel addExpensePanel = new JPanel();
        addExpensePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        addExpensePanel.setLayout(null);
        setContentPane(addExpensePanel);

        expenseObject = E;
        userObject = u;

        // Frequency options
        String[] frequencyArray = {"Yearly", "Monthly", "Biweekly", "One-Time"};

        // Title panel
        titlePanel = new JPanel();
        titlePanel.setBounds(0, 5, 700, 56);
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setLayout(null);
        addExpensePanel.add(titlePanel);

        titleLabel = new JLabel(" Add Expense");
        titleLabel.setBounds(10, 0, 500, 46);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setFont(new Font("Zilla Slab", Font.BOLD, 28));
        titleLabel.setForeground(new Color(236, 70, 47));
        titlePanel.add(titleLabel);

        borderPanel = new JPanel();
        borderPanel.setBackground(new Color(165, 27, 37));
        borderPanel.setBounds(0, 49, 700, 7);
        titlePanel.add(borderPanel);

        // Body panel
        bodyPanel = new JPanel();
        bodyPanel.setBackground(new Color(236, 70, 47));
        bodyPanel.setBounds(0, 59, 700, 200);
        bodyPanel.setLayout(null);
        addExpensePanel.add(bodyPanel);

        // Inputs panel
        inputsPanel = new JPanel();
        inputsPanel.setBackground(new Color(240, 94, 57));
        inputsPanel.setBounds(10, 10, 680, 90);
        bodyPanel.add(inputsPanel);

        // 4 columns: Amount, Frequency, Source, Description
        inputsPanel.setLayout(new GridLayout(1, 4, 10, 10));

        // Amount panel
        amountPanel = new JPanel();
        amountPanel.setBackground(new Color(243, 124, 95));
        amountPanel.setLayout(new FlowLayout());
        amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
        amountPanel.add(amountLabel);

        SpinnerNumberModel amountSpinnerModel =
                new SpinnerNumberModel(0.0, 0.0, 99999.99, 0.01);
        amountSpinner = new JSpinner(amountSpinnerModel);
        amountSpinner.setPreferredSize(new Dimension(106, 20));
        amountSpinner.setEditor(new JSpinner.NumberEditor(amountSpinner, "00.00"));
        amountPanel.add(amountSpinner);

        // Frequency panel
        freqPanel = new JPanel();
        freqPanel.setBackground(new Color(243, 124, 95));
        freqPanel.setLayout(new FlowLayout());
        freqLabel = new JLabel("Frequency:");
        freqLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
        freqPanel.add(freqLabel);

        freqCombo = new JComboBox<>(frequencyArray);
        freqCombo.setPreferredSize(new Dimension(106, 20));
        freqPanel.add(freqCombo);

        // Source panel
        sourcePanel = new JPanel();
        sourcePanel.setBackground(new Color(243, 124, 95));
        sourcePanel.setLayout(new FlowLayout());
        sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
        sourcePanel.add(sourceLabel);

        sourceText = new JTextField();
        sourceText.setPreferredSize(new Dimension(106, 20));
        sourcePanel.add(sourceText);

        // Description panel
        descriptionPanel = new JPanel();
        descriptionPanel.setBackground(new Color(243, 124, 95));
        descriptionPanel.setLayout(new FlowLayout());
        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
        descriptionPanel.add(descriptionLabel);

        descriptionText = new JTextField();
        descriptionText.setPreferredSize(new Dimension(106, 20));
        descriptionPanel.add(descriptionText);

        // Add panels to inputs
        inputsPanel.add(amountPanel);
        inputsPanel.add(freqPanel);
        inputsPanel.add(sourcePanel);
        inputsPanel.add(descriptionPanel);

        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(150, 120, 150, 40);
        cancelButton.setBorderPainted(false);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(new Color(236, 70, 47));
        cancelButton.setFont(new Font("Roboto Medium", Font.BOLD, 16));
        cancelButton.addActionListener(this);
        bodyPanel.add(cancelButton);

        // Confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(350, 120, 150, 40);
        confirmButton.setBorderPainted(false);
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setForeground(new Color(236, 70, 47));
        confirmButton.setFont(new Font("Roboto Medium", Font.BOLD, 16));
        confirmButton.addActionListener(this);
        bodyPanel.add(confirmButton);

        java.net.URL iconURL = getClass().getResource("modified-noun-purse-3362985.png");
		System.out.println("Icon URL: " + iconURL);

		if (iconURL != null) {
		    ImageIcon icon = new ImageIcon(iconURL);
		    setIconImage(icon.getImage());
		} else {
		    System.out.println("Icon not found");
		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cancelButton) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            return;
        }

        if (e.getSource() == confirmButton) {

            expenseObject.amount = (double) amountSpinner.getValue();

            switch (freqCombo.getSelectedIndex()) {
                case 0: expenseObject.yearlyfrequency = 1; break;   // Yearly
                case 1: expenseObject.yearlyfrequency = 12; break;  // Monthly
                case 2: expenseObject.yearlyfrequency = 24; break;  // Biweekly
                case 3: expenseObject.yearlyfrequency = 0; break;   // One-time
            }

            expenseObject.source = sourceText.getText();
            expenseObject.description = descriptionText.getText();

            ExpenseDAO.addExpense(expenseObject, userObject.userID);
            updateMonthlySavings.updateSavings(userObject);

            this.dispose();
        }
    }
}

