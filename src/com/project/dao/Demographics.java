package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Demographics {

	static final String JDBC_DRIVER = "com.jdbc:postgresql.jdbc.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/MIMIC";
	static final String USER = "postgres";
	static final String PASS = "Welcomead@2";
	public static Connection conn;
	
	public Demographics() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Connection failed: " + e.getMessage(), null, 1);
		}
	}
	
	public String[] getMaritalStatus() throws SQLException {
		ArrayList<String> maritalStatus = new ArrayList<String>();
			PreparedStatement getMartialStatusStmt = conn.prepareStatement("select distinct(marital_status) from admissions");
			
			ResultSet rs = getMartialStatusStmt.executeQuery();
			
			while(rs.next()) {
				maritalStatus.add(rs.getString(1));
			}
			
		return maritalStatus.toArray(new String[maritalStatus.size()]);
	}
	
	public String[] getEthinicity() throws SQLException {
		ArrayList<String> ethinicity = new ArrayList<String>();
			PreparedStatement getethinicityStmt = conn.prepareStatement("select distinct(ethinicity) from admissions");
			
			ResultSet rs = getethinicityStmt.executeQuery();
			ethinicity.add(new String());
			while(rs.next()) {
				ethinicity.add(rs.getString(1));
			}
			
		return ethinicity.toArray(new String[ethinicity.size()]);
	}
	
	public String[] getReligionStatus() throws SQLException {
		ArrayList<String> maritalStatus = new ArrayList<String>();
			PreparedStatement getMartialStatusStmt = conn.prepareStatement("select distinct(religion) from admissions");
			
			ResultSet rs = getMartialStatusStmt.executeQuery();
			
			while(rs.next()) {
				maritalStatus.add(rs.getString(1));
			}
			
		return maritalStatus.toArray(new String[maritalStatus.size()]);
	}
}
