package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTransaction {
	private String url;
	private String tableName;
	private Connection connection;
	private String user;
	private String password;
	public DbTransaction(String url, String user,
			String password, String tableName) {
		super();
		this.url = url;
		this.tableName = tableName;
		this.user = user;
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Connection getConnection() {
		try {
		closeConnection();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection()
	{
	try
	{
	if(connection != null && connection.isClosed() == false)
	connection.close();
	connection = null;
	}
	catch(SQLException e)
	{
	e.printStackTrace();
	}
	}
	
	
}
