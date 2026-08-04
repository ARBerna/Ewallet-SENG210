package src.fileFix;

public class TestDB {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		}
		catch (Exception e) {
			System.out.println("Driver not loaded");
			e.printStackTrace();
		}
	}
}
