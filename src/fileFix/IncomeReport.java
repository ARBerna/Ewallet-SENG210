package fileFix;

public class IncomeReport {
	double[] incomeAmounts = {2000, 1500, 500};
	String[] months = {"January", "Febuary", "Febuary"};
	String[] sourseIncome = {"Job", "Job", "Gift"};
	int i;
	
	
	public IncomeReport() 
	{
		double total = 0;
		
		System.out.println("Report\n");
		
		for (i = 0; i < incomeAmounts.length; ++i) 
		{
			System.out.println("Amount: " + incomeAmounts[i] + ", Month: " + months[i] + ", Source: " + sourseIncome[i]);
			total += incomeAmounts[i];
			
		}
			System.out.println("Total: " + total); 
	}
}
