package analyzer;

import java.sql.*;
import java.util.Scanner;

public class UserService {

    public boolean register(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO user(username, password) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int login(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT id FROM user WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}