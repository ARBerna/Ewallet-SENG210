package src.fileFix;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class IncomeDAO {
	public static boolean addIncome(Wage w, int userId)
	{
		String sql = "INSERT INTO Income (UserID, Amount, Month, Source) VALUES (?, ?, ?, ?)";
		try(Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setInt(1, userId);;
			stmt.setDouble(2, w.amount);
			stmt.setString(3, w.Month);
			stmt.setString(4, w.source);
			int rows = stmt.executeUpdate();
			return rows > 0;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
}