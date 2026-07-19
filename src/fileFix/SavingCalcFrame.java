package fileFix;

import javax.swing.*;
import java.awt.*;

public class SavingCalcFrame extends JFrame {
		
	private JTextField itemField;
	private JSpinner priceSpinner;
	
	public SavingCalcFrame() {
		
		setTitle("Savings Calculator");
		setSize(450, 250);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.BLACK);
		
		JLabel titleLabel = new JLabel("Savings Calculator");
		titleLabel.setBounds(0, 0, 421, 46);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Zilla Slab", Font.BOLD, 28));
		titleLabel.setForeground(new Color(236, 70, 47));
		
		//border panel
		JPanel borderPanel = new JPanel();
		borderPanel.setBackground(new Color(165, 27, 37));
		borderPanel.setBounds(0, 49, 431, 7);
		
		//body panel
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(new Color(236, 70, 47));
		bodyPanel.setLayout(null);
		
		//itemfield
		JLabel itemLabel = new JLabel ("Item:");
		itemLabel.setBounds(10, 20, 80, 25);
		bodyPanel.add(itemLabel);
		
		itemField = new JTextField();
		itemField.setBounds(90, 20, 150, 25);
		bodyPanel.add(itemField);
		
		//itemprice spinner
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setBounds(10, 50, 80, 20);
		bodyPanel.add(priceLabel);
		
		priceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 99999.0, 1.0));
		priceSpinner.setBounds(90, 50, 150, 25);
		bodyPanel.add(priceSpinner);
		
		//Calculate button
		JButton calcButton = new JButton("Calculate");
		calcButton.setBounds(260, 30, 120, 35);
		calcButton.setBackground(Color.BLACK);
		calcButton.setForeground(new Color(236, 70, 47));
		bodyPanel.add(calcButton);
		
		
		//messgaedialog for button action
		calcButton.addActionListener(e -> {
			double price = (double) priceSpinner.getValue();
			double monthlySaving = 500.0; //Hardcode - get from system in integration
			String item = itemField.getText();
			
			//validate before calculating
			if (monthlySaving <=0) {
				JOptionPane.showMessageDialog(this, "System savings not avaliable", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//maybe integrate savings from noahs features monthsCalc
			
			double monthsCalc = price / monthlySaving;
			long result = (long) Math.ceil(monthsCalc);
			
			JOptionPane.showMessageDialog(this, "Item: " + item + "\nPrice: $" + price + "\nMonthly Savings: $" + monthlySaving + "\n\nYou will need " + result + " months to afford this.", "Savings Result", JOptionPane.INFORMATION_MESSAGE);
			
		});
		
		add(titlePanel, BorderLayout.NORTH);
		add(bodyPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
	}
}
