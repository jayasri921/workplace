package org.jayasri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db","bank","bank");
	return con;
}
}