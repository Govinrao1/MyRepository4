package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test1 
{
	public static void main(String[] args) throws Exception
	{
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//1. connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/classicmodels","root","magnitia");
		//2. Perform operations
		Statement st=con.createStatement();  
		try
		{
			//3. execute a SQL query
			int r=st.executeUpdate("create database kalamsir;"); //Create a new DB
			if(r==1)
			{
				System.out.println("DB creation test passed");
			}
		}
		catch(Exception ex)
		{
			System.out.println("DB creation test failed due to "+ex.getMessage());
		}
		//4. disconnect from DB
		con.close();  
	}
}
