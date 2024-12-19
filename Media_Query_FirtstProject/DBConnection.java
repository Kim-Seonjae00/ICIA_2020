package project;

import java.sql.*;

public class DBConnection {

	public static Connection makeConnection() {
		Connection con = null;
		String user = "PROJECT1";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,password);
			System.out.println("DB ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("DB����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB���� ����");
			e.printStackTrace();
		}
		return con;	
	}
}
