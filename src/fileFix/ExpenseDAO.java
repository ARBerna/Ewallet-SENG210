package src.fileFix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExpenseDAO {

	public static boolean addExpense(Expense e, int userId)
	{
		String sql = "INSERT INTO Expenses (UserID, Amount, Description, Date, Frequency, Source) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setInt(1,  userId);
			stmt.setDouble(2, e.amount);
			stmt.setString(3, e.description);
			stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
			stmt.setInt(5, e.yearlyfrequency);
			stmt.setString(6, e.source);

			int rows = stmt.executeUpdate();
			return rows > 0;
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
}
