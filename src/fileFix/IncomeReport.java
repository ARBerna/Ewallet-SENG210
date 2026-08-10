package src.fileFix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

public class IncomeReport {
	double[] incomeAmounts;
	String[] months;
	String[] sourseIncome;
	int i;


	public IncomeReport()
	{
		// Fetch the dynamic data from the database before running the report logic
		fetchDataFromDatabase();

		double total = 0;

		System.out.println("Report\n");

		for (i = 0; i < incomeAmounts.length; ++i)
		{
			System.out.println("Amount: " + incomeAmounts[i] + ", Month: " + months[i] + ", Source: " + sourseIncome[i]);
			total += incomeAmounts[i];

		}
			System.out.println("Total: " + total);
	}

	
	private void fetchDataFromDatabase() {
		String query = "SELECT amount, month_name, income_source  AS source FROM income_transactions";
		
		List<Double> amountsList = new ArrayList<>();
		List<String> monthsList = new ArrayList<>();
		List<String> sourcesList = new ArrayList<>();

		try (Connection connection = Database.getConnection(); 
			 PreparedStatement preparedStatement = connection.prepareStatement(query);
			 ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				amountsList.add(resultSet.getDouble("amount"));
				monthsList.add(resultSet.getString("month_name"));
				sourcesList.add(resultSet.getString("income_source"));
			}

		} catch (SQLException e) {
			System.err.println("Error pulling income report data from MySQL database.");
			e.printStackTrace();
		}

		incomeAmounts = new double[amountsList.size()];
		months = new String[monthsList.size()];
		sourseIncome = new String[sourcesList.size()];

		for (int j = 0; j < amountsList.size(); j++) {
			incomeAmounts[j] = amountsList.get(j);
			months[j] = monthsList.get(j);
			sourseIncome[j] = sourcesList.get(j);
		}
	}
}
