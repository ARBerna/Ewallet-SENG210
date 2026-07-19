package fileFix;

import java.util.ArrayList;

public class updateMonthlySavings {
	public static void updateSavings (User user) {
		
		double updatedSavings = 0;
		
		//get the most recently wage
		updatedSavings = user.getRecentWage().amount;
		
		//subtract the expenses
		ArrayList<Expense> expenses = user.getExpenses();
		for (Expense expense:expenses) {
			switch (expense.yearlyfrequency) {
				case 12:
					updatedSavings -= expense.amount;
					break;
				case 24:
					updatedSavings -= expense.amount * 2;
					break;
			}
		}
		
		//update the user's monthly savings
		user.monthlysavings = updatedSavings;
	}
}
