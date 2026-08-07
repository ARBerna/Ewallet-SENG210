package src.fileFix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	//Create user
	public static boolean addUser(DatabaseUser user)
	{
		String sql = "INSERT INTO Users (Username, PasswordHash, Email) VALUES (?, ?, ?)";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1,  user.getUsername());
			stmt.setString(2, user.getPasswordHash());
			stmt.setString(3, user.getEmail());

			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	//Login validation
	public static boolean validateLogin(String username, String passwordHash)
	{
		String sql = "SELECT * FROM Users WHERE Username = ? AND PasswordHash = ?";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, username);
			stmt.setString(2, passwordHash);

			ResultSet rs = stmt.executeQuery();
			return rs.next(); //true if a matching user exists
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	// Get user by user name
	public static DatabaseUser getUserByUsername(String username)
	{
		String sql = "SELECT * FROM Users WHERE Username = ?";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql))
		{
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				return new DatabaseUser(
						rs.getInt("UserID"),
						rs.getString("Username"),
						rs.getString("PasswordHash"),
						rs.getString("Email"),
						rs.getString("CreatedAt")
				);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	// Get all users
	public static List<DatabaseUser> getAllUsers()
	{
		List<DatabaseUser> list = new ArrayList<>();
		String sql = "SELECT * FROM Users";

		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery())
		{
			while (rs.next())
			{
				list.add(new DatabaseUser(
						rs.getInt("UsrID"),
						rs.getString("Username"),
						rs.getString("PasswordHash"),
						rs.getString("Email"),
						rs.getString("CreatedAt")
				));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return list;
	}
	
	// Get username directly by its UserID integer
	public static String getUsernameById(int userID) {
		String sql = "SELECT Username FROM Users WHERE UserID = ?";
		try (Connection conn = Database.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("Username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "UnknownUser"; // Safe fallback string
	}

}
