package analyzer;

import java.sql.*;
import java.util.Scanner;

public class DataService {

    public void addData(int userId, Scanner sc) {
        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Category: ");
            String category = sc.next();

            System.out.print("Value: ");
            double value = sc.nextDouble();

            System.out.print("Description: ");
            sc.nextLine();
            String desc = sc.nextLine();

            String sql = "INSERT INTO data_entry(user_id, category, value, date, description) VALUES(?,?,?,CURDATE(),?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, category);
            ps.setDouble(3, value);
            ps.setString(4, desc);

            ps.executeUpdate();
            System.out.println("Data added successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewData(int userId) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM data_entry WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("category") + " | " +
                        rs.getDouble("value") + " | " +
                        rs.getDate("date") + " | " +
                        rs.getString("description"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteData(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM data_entry WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deleted successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}