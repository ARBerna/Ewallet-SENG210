package fileFix;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	private static final String URL = "jdbc:mysql://localhost:3306/ewallet";
	private static final String USER = "root";
	private static final String PASS = "Trump2020!";
	
	public static Connection getConnection()
	{
		try
		{
			return DriverManager.getConnection(URL, USER, PASS);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
