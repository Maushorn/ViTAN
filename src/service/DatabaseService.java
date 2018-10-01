package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {

	public static final String DBlocation = "C:\\Users\\janoc_000\\Desktop\\Projekt\\DB";
	public static final String connString = "jdbc:derby:" + DBlocation + ";create=true";
	
	
	/**Initializes Database.
	 * 
	 */
	public static void initialize() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	
}