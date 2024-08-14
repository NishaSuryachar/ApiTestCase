package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {

	static Connection con=null;
	static ResultSet result = null;
	static FileUtility fLib=new FileUtility();

	public  void getConnection(String url,String username,String password) throws SQLException
	{
		try
		{
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) {

		}
	}
	public  boolean executeQueryVerifyAndGetData(String query,int columnIndex,String expectedData) throws SQLException
	{
		boolean flag=false;
	result=con.createStatement().executeQuery(query);
		while(result.next())
		{
				if(result.getString(columnIndex).equals(expectedData)) {
					flag=true;
					break;
				}
		}
			if(flag)
			{
				System.out.println(expectedData+"==>data verified in data base table");
				return true;
			}else {
				System.out.println(expectedData+"==>data is not verified in data base table");
				return false;
			}
		}
		

	public void getConnection1() throws SQLException
	{
		try
		{
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(fLib.getDataFromPropertyFile("DBUrl")
					, fLib.getDataFromPropertyFile("DB_Username")
					, fLib.getDataFromPropertyFile("DB_Password"));
		}
		catch (Exception e) {

		}
	}

	public  void closeConnection() throws SQLException
	{
		try
		{
			con.close();
		}
		catch (Exception e) {
		}
     }
	
	public ResultSet executeSelectQuery(String Query)
	{
		result=null;
		try
		{
			Statement state = con.createStatement();
			result = state.executeQuery(Query);
		}
		catch (Exception e) {
	     }
		  return result;
	}

	public int executeNonSelectQuery(String Query)
	{
		int result=0;
		try
		{
			Statement state = con.createStatement();
			result = state.executeUpdate(Query);
		}
		catch (Exception e) {
		}
		return result;
	}
}
