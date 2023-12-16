package Model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import context.DBContext;

public class checkLoginDAO {
	
	public boolean isExistedUser(String username, String password, String role) {
		boolean ok = false;
		try {
			Connection conn = DBContext.openConnection();
			Statement stm = conn.createStatement();
			String query = "select * from user where username = '" + username + "' and password = '" + password + "' and role = '" + role + "'";
			ResultSet rs = stm.executeQuery(query);
			
			while (rs.next()) {
				if ((username.equals(rs.getString("username"))) && (password.equals(rs.getString("password")))) {
					ok = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}
		
		return ok;
	}
}
