package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

	private static final String serverName = "localhost";
	private static final String dbName = "cnw";
	private static final String userID = "root";
	private static final String password = "";
	private static final String portNumber = "3306";
	
	public static Connection openConnection() throws Exception {
		String url = "jdbc:mysql://"+serverName+":"+portNumber+"/"+dbName;
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, userID, password);
	}
}
