package com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TestProcedure {

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "jdbc:oracle:thin:";  
	   static final String DB_URL = "@ingnrgpilphp01:1521:ORCLILP";

	   //  Database credentials
	   static final String USER = "aja18core";
	   static final String PASS = "aja18core";
	   
	   // Table Name
	   static final String TABLE_NAME = "tbl_test_empid";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DbTransaction db = new DbTransaction(JDBC_DRIVER+DB_URL, USER, PASS, TABLE_NAME);

		
		
		int result;
		result = getTestsCount(db);
		System.out.println(result);
		ArrayList<Test> a = new ArrayList<>();
		a=getTests(db, 1);
		for(Test t:a) {
		System.out.println(t.title);
		}
		
	}
	
	public static int getTestsCount(DbTransaction dbt) {
		
		try (Connection con = dbt.getConnection();
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet count = stmt.executeQuery("SELECT COUNT(*) FROM "+dbt.getTableName());) {
			
			while(count.next())
			return count.getInt(1);
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}
	
	public static ArrayList<Test> getTests(DbTransaction dbt, int id) {
		ArrayList<Test> toBeReturned = new ArrayList<>();
		try (Connection cn = dbt.getConnection();
				Statement stmt=cn.createStatement();
				ResultSet list = stmt.executeQuery("SELECT * FROM " +dbt.getTableName() +" WHERE test_id > " + id);) {
			
			while (list.next()) {
				Test t = new Test(list.getInt("test_id"),list.getString("test_name"));
				toBeReturned.add(t);
			}
			return toBeReturned;
		} catch(Exception e) {
			return null;
		}
	}

}
