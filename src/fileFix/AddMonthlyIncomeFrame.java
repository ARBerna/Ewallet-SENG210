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
import javax.swing.ImageIcon;
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

public class AddMonthlyIncomeFrame extends JFrame implements ActionListener {

	//create ui objects
	JPanel    titlePanel;
	JLabel    titleLabel;
	JPanel    borderPanel;
	JPanel    bodyPanel;
	JPanel    inputsPanel;
	JPanel    amountPanel;
	JPanel    monthPanel;
	JLabel 	  monthLabel;
	JPanel 	  sourcePanel;
	JLabel    sourceLabel;
	JComboBox monthCombo;
	JLabel    amountLabel;
	JSpinner  amountSpinner;
	JButton   cancelButton;
	JButton   confirmButton;
	Wage      wageObject;
	User      userObject;
	private static final long serialVersionUID = 1L;
	private JPanel addIncomePanel;
	private JTextField sourceText;

	/**
	 * Create the frame.
	 */
	public AddMonthlyIncomeFrame(User u, Wage w) 
	{

		//setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 190);
		addIncomePanel = new JPanel();
		addIncomePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(addIncomePanel);
		addIncomePanel.setLayout(null);
		
		userObject = u;
		wageObject = w;
		
		//setup month array
		String[] monthArray = new String[12];
		monthArray[0]  = "January";
		monthArray[1]  = "February";
		monthArray[2]  = "March";
		monthArray[3]  = "April";
		monthArray[4]  = "May";
		monthArray[5]  = "June";
		monthArray[6]  = "July";
		monthArray[7]   = "August";
		monthArray[8]  = "September";
		monthArray[9]  = "October";
		monthArray[10] = "November";
		monthArray[11] = "December";
		
		//title panel
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 5, 431, 56);
		titlePanel.setBackground(new Color(0, 0, 0));
		addIncomePanel.add(titlePanel);
		
		//title label
		titleLabel = new JLabel(" Add Monthly Income");
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
		addIncomePanel.add(bodyPanel);
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
		monthPanel = new JPanel();
		monthPanel.setBackground(new Color(243, 124, 95));
		monthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//month label
		monthLabel = new JLabel("Month:");
		monthLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		monthPanel.add(monthLabel);
		
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
					.addComponent(monthPanel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(monthPanel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
						.addComponent(amountPanel, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		sourceText = new JTextField();
		sourceText.setMinimumSize(new Dimension(106, 20));
		sourceText.setPreferredSize(new Dimension(106, 20));
		sourcePanel.add(sourceText);
		sourceText.setColumns(10);
		
		//month combo
		monthCombo = new JComboBox(monthArray);
		monthCombo.setPreferredSize(new Dimension(106, 20));
		monthPanel.add(monthCombo);
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

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		java.net.URL iconURL = getClass().getResource("/fileFix/modified-noun-purse-3362985.png");
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
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //close window
			return;
		}
		else if (e.getSource() == confirmButton) {
			//update wage object
			wageObject.amount = (double) amountSpinner.getValue();
			wageObject.Month  = (String) monthCombo.getSelectedItem();
			wageObject.source = sourceText.getText();
			
			//update user object
			userObject.addWage(wageObject);
			updateMonthlySavings.updateSavings(userObject);
			
			this.dispose();
			return;
		}
		
	}
}
