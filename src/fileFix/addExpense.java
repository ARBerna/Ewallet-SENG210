package fileFix;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class addExpense extends JFrame implements ActionListener {

	//create ui objects
	JPanel    titlePanel;
	JLabel    titleLabel;
	JPanel    borderPanel;
	JPanel    bodyPanel;
	JPanel    inputsPanel;
	JPanel    amountPanel;
	JPanel    freqPanel;
	JLabel 	  freqLabel;
	JPanel 	  sourcePanel;
	JLabel    sourceLabel;
	JComboBox freqCombo;
	JLabel    amountLabel;
	JSpinner  amountSpinner;
	JButton   cancelButton;
	JButton   confirmButton;
	Expense   expenseObject;
	User      userObject;
	private static final long serialVersionUID = 1L;
	private JPanel addExpensePanel;
	private JTextField sourceText;


	/**
	 * Create the frame.
	 */
	public addExpense(User u, Expense E) {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 190);
		addExpensePanel = new JPanel();
		addExpensePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(addExpensePanel);
		addExpensePanel.setLayout(null);
		
		expenseObject = E;
		userObject    = u;
		
		//setup month array
		String[] frequencyArray = new String[3];
		frequencyArray[0]  = "Yearly";
		frequencyArray[1]  = "Monthly";
		frequencyArray[2]  = "Biweekly";

		
		//title panel
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 5, 431, 56);
		titlePanel.setBackground(new Color(0, 0, 0));
		addExpensePanel.add(titlePanel);
		
		//title label
		titleLabel = new JLabel(" Add Expense");
		titleLabel.setBounds(0, 0, 421, 46);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Zilla Slab", Font.BOLD, 28));
		titleLabel.setForeground(new Color(236, 70, 47));
		
		//border panel
		borderPanel = new JPanel();
		borderPanel.setBackground(new Color(165, 27, 37));
		borderPanel.setBounds(0, 49, 430, 7);
		GroupLayout gl_borderPanel = new GroupLayout(borderPanel);
		gl_borderPanel.setHorizontalGroup(
			gl_borderPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 431, Short.MAX_VALUE)
		);
		gl_borderPanel.setVerticalGroup(
			gl_borderPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 28, Short.MAX_VALUE)
		);
		borderPanel.setLayout(gl_borderPanel);
		titlePanel.setLayout(null);
		titlePanel.add(titleLabel);
		titlePanel.add(borderPanel);
		
		//body panel
		bodyPanel = new JPanel();
		bodyPanel.setBackground(new Color(236, 70, 47));
		bodyPanel.setBounds(0, 59, 431, 199);
		addExpensePanel.add(bodyPanel);
		SpringLayout sl_bodyPanel = new SpringLayout();
		bodyPanel.setLayout(sl_bodyPanel);
		
		//inputs panel
		inputsPanel = new JPanel();
		sl_bodyPanel.putConstraint(SpringLayout.NORTH, inputsPanel, 10, SpringLayout.NORTH, bodyPanel);
		sl_bodyPanel.putConstraint(SpringLayout.WEST, inputsPanel, 10, SpringLayout.WEST, bodyPanel);
		sl_bodyPanel.putConstraint(SpringLayout.SOUTH, inputsPanel, 92, SpringLayout.NORTH, bodyPanel);
		sl_bodyPanel.putConstraint(SpringLayout.EAST, inputsPanel, 421, SpringLayout.WEST, bodyPanel);
		inputsPanel.setBackground(new Color(240, 94, 57));
		bodyPanel.add(inputsPanel);
		
		//amount panel
		amountPanel = new JPanel();
		amountPanel.setBackground(new Color(243, 124, 95));
		
		//month panel
		freqPanel = new JPanel();
		freqPanel.setBackground(new Color(243, 124, 95));
		freqPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//month label
		freqLabel = new JLabel("Frequency:");
		freqLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		freqPanel.add(freqLabel);
		
		//source panel
		sourcePanel = new JPanel();
		sourcePanel.setBackground(new Color(243, 124, 95));
		sourcePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//source label
		sourceLabel = new JLabel("Source:");
		sourceLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		sourcePanel.add(sourceLabel);
		GroupLayout gl_inputsPanel = new GroupLayout(inputsPanel);
		gl_inputsPanel.setHorizontalGroup(
			gl_inputsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(amountPanel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(freqPanel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sourcePanel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_inputsPanel.setVerticalGroup(
			gl_inputsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(sourcePanel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(freqPanel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(amountPanel, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		sourceText = new JTextField();
		sourceText.setMinimumSize(new Dimension(106, 20));
		sourceText.setPreferredSize(new Dimension(106, 20));
		sourcePanel.add(sourceText);
		sourceText.setColumns(10);
		
		//freq combo
		freqCombo = new JComboBox(frequencyArray);
		freqCombo.setPreferredSize(new Dimension(106, 20));
		freqPanel.add(freqCombo);
		amountPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//amount label
		amountLabel = new JLabel("Amount:");
		amountLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		amountPanel.add(amountLabel);
		
		//amount spinner
		final double PRICE_MIN    = 0.0;    //price spinner minimum
		final double PRICE_MAX    = 99999.99; //price spinner maximum
		final double PRICE_INC    = 0.01;   //price spinner increment
		final int    PRICE_WIDTH  = 50;     //price spinner width
		final int    PRICE_HEIGHT = 20;     //price spinner height
		SpinnerNumberModel amountSpinnerModel = new SpinnerNumberModel(PRICE_MIN, PRICE_MIN, PRICE_MAX, PRICE_INC);
		amountSpinner = new JSpinner(amountSpinnerModel);
		amountSpinner.setPreferredSize(new Dimension(106, 20));
		amountSpinner.setEditor(new JSpinner.NumberEditor(amountSpinner, "00.00"));
		amountPanel.add(amountSpinner);
		inputsPanel.setLayout(gl_inputsPanel);
		
		//cancel button
		cancelButton = new JButton("Cancel");
		sl_bodyPanel.putConstraint(SpringLayout.NORTH, cancelButton, 6, SpringLayout.SOUTH, inputsPanel);
		sl_bodyPanel.putConstraint(SpringLayout.WEST, cancelButton, 86, SpringLayout.WEST, bodyPanel);
		sl_bodyPanel.putConstraint(SpringLayout.EAST, cancelButton, -223, SpringLayout.EAST, bodyPanel);
		cancelButton.setBorderPainted(false);
		cancelButton.setBackground(new Color(0, 0, 0));
		cancelButton.setFont(new Font("Roboto Medium", Font.BOLD, 16));
		cancelButton.setForeground(new Color(236, 70, 47));
		bodyPanel.add(cancelButton);
		cancelButton.addActionListener(this);
		
		//confirm button
		confirmButton = new JButton("Confirm");
		sl_bodyPanel.putConstraint(SpringLayout.NORTH, confirmButton, 6, SpringLayout.SOUTH, inputsPanel);
		sl_bodyPanel.putConstraint(SpringLayout.WEST, confirmButton, 17, SpringLayout.EAST, cancelButton);
		sl_bodyPanel.putConstraint(SpringLayout.EAST, confirmButton, -84, SpringLayout.EAST, bodyPanel);
		confirmButton.setForeground(new Color(236, 70, 47));
		confirmButton.setFont(new Font("Roboto Medium", Font.BOLD, 16));
		confirmButton.setBorderPainted(false);
		confirmButton.setBackground(Color.BLACK);
		bodyPanel.add(confirmButton);
		confirmButton.addActionListener(this);

		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //close window
			return;
		}
		else if (e.getSource() == confirmButton) {
			//update expense object
			expenseObject.amount = (double) amountSpinner.getValue();
			switch (freqCombo.getSelectedIndex()) {
				case 0: 
					expenseObject.yearlyfrequency = 1; //yearly
					break;
				case 1:
					expenseObject.yearlyfrequency = 12; //monthly
					break;
				case 2:
					expenseObject.yearlyfrequency = 24; //biweekly
					break;
			}
			expenseObject.source = sourceText.getText();
			
			//update user object
			userObject.addExpense(expenseObject);
			
			//update savings
			updateMonthlySavings.updateSavings(userObject);
			
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //close window
			return;
		}
		
	}
}
