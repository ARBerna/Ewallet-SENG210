package src.fileFix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WageDAO {

    /**
     * 1. Permanently save a new income/wage source to the MySQL database.
     * Changed table target to match your live 'income_transactions' setup.
     */
    public static boolean addWage(int userId, String source, double amount, String month) {
        // Aligned with the MySQL schema column names: amount, month_name, source
        String sql = "INSERT INTO income_transactions (amount, month_name, source) VALUES (?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, amount);
            pstmt.setString(2, month);
            pstmt.setString(3, source);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            System.err.println("[WageDAO] Failed to insert wage log entry into MySQL.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 2. Fetch all saved wage records from MySQL.
     * Modified to return an ArrayList<Wage> instead of List<String[]> 
     * to eliminate data mapping gaps between your database and Swing UI components.
     */
    public static ArrayList<Wage> getWagesByUser(int userId) {
        ArrayList<Wage> wageList = new ArrayList<>();
        String sql = "SELECT amount, month_name, source FROM income_transactions";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String month  = rs.getString("month_name");
                String source = rs.getString("source");

                // Seamlessly construct real domain Wage objects straight out of the MySQL row data
                Wage dynamicWage = new Wage(source, amount, month);
                wageList.add(dynamicWage);
            }

        } catch (Exception e) {
            System.err.println("[WageDAO] Failed to retrieve user wage datasets from MySQL.");
            e.printStackTrace();
        }
        return wageList;
    }
}
