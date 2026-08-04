package src.fileFix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class SavingCalcFrame extends JFrame {

	private JTextField itemField;
	private JSpinner priceSpinner;
	private DefaultListModel<PlannedPurchase> listModel;
	private JList<PlannedPurchase> purchaseJList;
	private JButton saveButton;
	private PlannedPurchase selectedPurchase = null;

	private static class PlannedPurchase {
		String id = UUID.randomUUID().toString();
		String name;
		double price;

		PlannedPurchase(String name, double price) {
			this.name = name;
			this.price = price;
		}

		@Override
		public String toString() {
			return String.format("%s - $%.2f", name, price);
		}
	}

	public SavingCalcFrame() {

		setTitle("Savings Calculator");
		setSize(700, 320); // Expanded frame width & height view layout
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//title panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(Color.BLACK);

		JLabel titleLabel = new JLabel("Savings Calculator");
		titleLabel.setBounds(0, 0, 421, 46);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(new Font("Zilla Slab", Font.BOLD, 28));
		titleLabel.setForeground(new Color(236, 70, 47));
		titlePanel.add(titleLabel); // Fixed missing structural layout add

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

		//Calculate button layout
		JButton calcButton = new JButton("Calculate");
		calcButton.setBounds(10, 100, 110, 35);
		calcButton.setBackground(Color.BLACK);
		calcButton.setForeground(new Color(236, 70, 47));
		bodyPanel.add(calcButton);

		//Save/Add Button (Create/Update feature)
		saveButton = new JButton("Save Plan");
		saveButton.setBounds(130, 100, 110, 35);
		saveButton.setBackground(Color.BLACK);
		saveButton.setForeground(new Color(236, 70, 47));
		bodyPanel.add(saveButton);

		// List view block
		JLabel listHeaderLabel = new JLabel("Planned Purchases:");
		listHeaderLabel.setBounds(270, 15, 180, 25);
		listHeaderLabel.setFont(new Font("Arial", Font.BOLD, 14));
		listHeaderLabel.setForeground(Color.WHITE);
		bodyPanel.add(listHeaderLabel);

		listModel = new DefaultListModel<>();
		purchaseJList = new JList<>(listModel);
		purchaseJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(purchaseJList);
		scrollPane.setBounds(270, 45, 390, 120);
		bodyPanel.add(scrollPane);

		// Modify/Delete layout
		JButton updateButton = new JButton("Modify");
		updateButton.setBounds(440, 175, 100, 30);
		updateButton.setBackground(Color.BLACK);
		updateButton.setForeground(new Color(236, 70, 47));
		bodyPanel.add(updateButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(560, 175, 100, 30);
		deleteButton.setBackground(Color.BLACK);
		deleteButton.setForeground(new Color(236, 70, 47));
		bodyPanel.add(deleteButton);

		//messgaedialog for button action
		calcButton.addActionListener(e -> {
			double price = (double) priceSpinner.getValue();
			double monthlySaving = 500.0; //Hardcode amount
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

		// CREATE & UPDATE execution flow
		saveButton.addActionListener(e -> {
			String name = itemField.getText().trim();
			double price = (double) priceSpinner.getValue();

			if (name.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Item name cannot be empty.", "Validation Error", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (selectedPurchase == null) {
				listModel.addElement(new PlannedPurchase(name, price));
			} else {
				selectedPurchase.name = name;
				selectedPurchase.price = price;
				purchaseJList.repaint();
				selectedPurchase = null;
				saveButton.setText("Save Plan");
			}
			itemField.setText("");
			priceSpinner.setValue(0.0);
		});

		// READ & PRE-POPULATE execution flow
		updateButton.addActionListener(e -> {
			PlannedPurchase selected = purchaseJList.getSelectedValue();
			if (selected == null) {
				JOptionPane.showMessageDialog(this, "Please select an item from the list to modify.", "No Selection", JOptionPane.WARNING_MESSAGE);
				return;
			}
			selectedPurchase = selected;
			itemField.setText(selected.name);
			priceSpinner.setValue(selected.price);
			saveButton.setText("Update Plan");
		});

		// DELETE execution flow
		deleteButton.addActionListener(e -> {
			int selectedIndex = purchaseJList.getSelectedIndex();
			if (selectedIndex == -1) {
				JOptionPane.showMessageDialog(this, "Please select an item to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
				return;
			}

			PlannedPurchase selected = listModel.get(selectedIndex);
			if (selectedPurchase != null && selectedPurchase.id.equals(selected.id)) {
				selectedPurchase = null;
				saveButton.setText("Save Plan");
				itemField.setText("");
				priceSpinner.setValue(0.0);
			}
			listModel.remove(selectedIndex);
		});

		add(titlePanel, BorderLayout.NORTH);
		add(bodyPanel, BorderLayout.CENTER);

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
}
