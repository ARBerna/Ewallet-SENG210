package src.fileFix;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	private static final String URL = "jdbc:mysql://localhost:3306/ewallet";
	private static final String USER = "root";
	private static final String PASS = System.getenv("DB_PASSWORD");

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
