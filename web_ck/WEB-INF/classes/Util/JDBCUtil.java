package Util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import Util.JDBCUtil;

public class JDBCUtil {
	static Connection conn = null;
	public static Connection  getConnection()
	{
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url = "jdbc:mysql://qlkh.cdx3bjtsoiw5.us-east-1.rds.amazonaws.com:3306/QLKH?useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String username ="admin";
			String password ="Kiet#2003";
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Successfully!");
		}catch(SQLException e)
		{
			System.out.println("Connection Error " + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConnection ()
	
	{
		try {
			if (conn != null)
			{
				System.out.println("Close Connection!");
				conn.close();
			}
			
		}catch (SQLException e)
		{
			System.out.println("Connection Error...");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
	    
		
		JDBCUtil.getConnection();
	}
	public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }

}
