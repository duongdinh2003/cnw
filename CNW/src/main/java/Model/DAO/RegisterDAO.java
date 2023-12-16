package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import context.DBContext;

public class RegisterDAO {

	public boolean registerUser(String fullname, String address, String phone_number, String email, String username, String password, String role) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBContext.openConnection();

            String insertQuery = "INSERT INTO user (full_name, address, phone_number, email, username, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, fullname);
            stmt.setString(2, address);
            stmt.setString(3, phone_number);
            stmt.setString(4, email);
            stmt.setString(5, username);
            stmt.setString(6, password);
            stmt.setString(7, role);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC driver not found.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred while connecting to the database.");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred.");
            ex.printStackTrace();
        } finally {
            // Đóng tài nguyên
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
}
