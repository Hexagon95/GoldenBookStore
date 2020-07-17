package Database;

import java.sql.*;

public class Connector { // this class stores all of the methods & variables that are related to the database connection
	public static final String Driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:zadb;create=true";	
	
	public static Connection conn = null;
	public static Statement s = null;
	public static ResultSet rs = null;
	public static PreparedStatement ps = null;
	public static CallableStatement cs = null;
	
	public static void Register() { // It registers the Driver
		try {
			Class.forName(Driver);
			System.out.println("Driver has sucessfully registered!");
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	public static void Connect() { // It connects the application to the Driver
		try {
			conn = DriverManager.getConnection(JDBC_URL);
			System.out.println("Connection Sucessful!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void Disconnect() { // It disconnects the application to the Driver
		try {
			if(conn != null) {
				conn.close();
				System.out.println("Connection has ended!");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
