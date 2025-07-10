package attendance.util;


	import java.sql.Connection;
	import java.sql.DriverManager;

	public class DBConnection {
	    private static final String URL = "jdbc:mysql://localhost:3306/attendance_db";
	    private static final String USER = "root@localhost";
	    private static final String PASS = "root123";

	    public static Connection getConnection() {
	        try {
	            return DriverManager.getConnection(URL, USER, PASS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}


