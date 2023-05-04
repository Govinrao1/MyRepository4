package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test3 
{
	public static void main(String[] args) throws Exception
	{
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//1. connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//2. Perform operations
		Statement st=con.createStatement();  
		try
		{
			//Insert 3 rows into tab validate response
			String q="insert into students(name,course,grade) "
					+ "values('steve jobs','Appium','A'),"
					+ "('john komm','SQL','A'),"
					+ "('Thomas kutty','Micro services','A');";
			int r=st.executeUpdate(q);     
			System.out.println(r);
			if(r==3)
			{
				System.out.println("Insertion test passed");
			}
			else
			{
				System.out.println("Insertion test failed");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//4. disconnect from DB
		con.close(); 
	}
}
