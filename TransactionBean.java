package org.jayasri;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class TransactionBean {
	public void depositAmount(String to,float amt) throws ClassNotFoundException, SQLException{
		Connection con = DBCon.getConnection();
		Statement st=null;
		ResultSet rs=null;
		float balance=0.0f;
		String name="";
		float transf = 0.0f;
		PreparedStatement ps=null;
		Date date = new Date();
		st = con.createStatement();
		 rs = st.executeQuery("select * from transactions where accno="+to);
		while (rs.next()) {
			balance = rs.getFloat("balance");
			transf=amt;
			name=rs.getString("accName");
		}
		balance = balance+amt;
		ps=con.prepareStatement("insert into transactions values(?,?,?,?)");
		ps.setString(1, to);
		ps.setString(2, name);
		ps.setFloat(3, transf);
		ps.setFloat(4, balance);
		ps.executeUpdate();
		
		}
	public void withdrawAmount(String from,float amt) throws ClassNotFoundException, SQLException{
		Connection con = DBCon.getConnection();
		Statement st=null;
		ResultSet rs=null;
		float balance=0.0f;
		String name="";
		float transf = 0.0f;
		PreparedStatement ps=null;
		st = con.createStatement();
		rs = st.executeQuery("select * from transactions where accno="+from);
		while (rs.next()) {
			balance = rs.getFloat("balance");
			transf=amt;
			name=rs.getString("accName");
		}
		balance = balance-amt;
		ps=con.prepareStatement("insert into transactions values(?,?,?,?)");
		ps.setString(1, from);
		ps.setString(2, name);
		ps.setFloat(3, transf);
		ps.setFloat(4, balance);
		ps.executeUpdate();
	}
	public float balanceAmount(String accNo) throws SQLException, ClassNotFoundException{
		Connection con = DBCon.getConnection();
		float bal = 0.0f;
		String s = "select * from transactions where accno="+accNo;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(s);
		while(rs.next()){
			bal = rs.getFloat("balance");
		}
		return bal;
	}
	public ArrayList<Float> getLastTransactions(String accNo) throws ClassNotFoundException, SQLException{
		ArrayList<Float> alf = new ArrayList<>();
		Connection con = DBCon.getConnection();
		String s = "select * from transactions where accno="+accNo;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(s);
		while(rs.next()){
			alf.add(rs.getFloat(3));
		}
		return alf;
	}
}
