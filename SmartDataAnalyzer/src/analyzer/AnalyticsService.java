package analyzer;

import java.sql.*;

public class AnalyticsService {

    public void showAnalytics(int userId) {

        try (Connection con = DBConnection.getConnection()) {

            // Total sum
            String totalSql = "SELECT SUM(value) AS total FROM data_entry WHERE user_id=?";
            PreparedStatement ps1 = con.prepareStatement(totalSql);
            ps1.setInt(1, userId);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                System.out.println("Total Value: " + rs1.getDouble("total"));
            }

            // Category-wise
            String catSql = "SELECT category, SUM(value) FROM data_entry WHERE user_id=? GROUP BY category";
            PreparedStatement ps2 = con.prepareStatement(catSql);
            ps2.setInt(1, userId);
            ResultSet rs2 = ps2.executeQuery();

            System.out.println("\nCategory Wise:");
            while (rs2.next()) {
                System.out.println(rs2.getString(1) + " : " + rs2.getDouble(2));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}