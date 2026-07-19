package fileFix;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;


public class CurrencyConversionFrame extends JFrame {

	private JTextField currAmount;
	private JComboBox<String> boxCombo;
	private CurrencyConversion conversionLogic;
	
	
	public CurrencyConversionFrame() {
		
	conversionLogic = new CurrencyConversion(); //needed to assign
	
	setTitle("Currency Convertor");
	setSize(450, 250);
	setLocationRelativeTo(null);
	setLayout(new BorderLayout());
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	

	//border panel
	JPanel borderPanel = new JPanel();
	borderPanel.setBackground(new Color(165, 27, 37));
	borderPanel.setBounds(0, 49, 431, 7);
	
	//body panel
	JPanel bodyPanel = new JPanel();
	bodyPanel.setBackground(new Color(236, 70, 47));
	bodyPanel.setLayout(null);
	
	//AmountField
	JLabel amountLabel = new JLabel ("Amount:");
	amountLabel.setBounds(10, 20, 80, 25);
	bodyPanel.add(amountLabel);
	
	currAmount = new JTextField();
	currAmount.setBounds(90, 20, 150, 25);
	bodyPanel.add(currAmount);
	
	//Results button
	JButton calcButton = new JButton("Calculate");
	calcButton.setBounds(260, 30, 120, 35);
	calcButton.setBackground(Color.BLACK);
	calcButton.setForeground(new Color(236, 70, 47));
	bodyPanel.add(calcButton);
	
	//ComboBox options stored as strings to pass to arrayList boxCombo
	String[] boxOptions = {
			"USDOLLAR TO JPYEN",
			"YPYEN TO USDOLLAR",
			"USDOLLAR TO EURO",
			"EURO TO USDOLLAR",
			"USDOLLAR TO POUND",
			"POUND TO USDOLLAR",
			"USDOLLAR TO CADOLLAR",
			"CADOLLAR TO USDOLLAR",
	};
	
	boxCombo = new JComboBox<>(boxOptions);
	boxCombo.setBounds(10, 50, 200, 25);
	bodyPanel.add(boxCombo);
	
	//messageDialog for button
	calcButton.addActionListener(e -> {
		
		//funny parsing for strings to pass param types to logic class
		double amount = Double.parseDouble(currAmount.getText());
		String choice = boxCombo.getSelectedItem().toString();
		
		//pass to logic class && call method after formatting
		double result = conversionLogic.convertResult(amount, choice);
		String formattedResult = String.format("%.2f", result);
		
		JOptionPane.showMessageDialog(this, "Result from " + choice + ": " + formattedResult);
		
	});
	
	add(bodyPanel, BorderLayout.CENTER);
	
	
	
	setVisible(true);
	
	}
}
