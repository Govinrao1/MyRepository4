package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test2
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
			//3. execute a SQL query
			String q="create table students(rollno int auto_increment primary key,"
					+ "name varchar(100) not null,course varchar(100) not null,"
					+ "grade varchar(1) not null);";
			int r=st.executeUpdate(q); //Create a new table
			System.out.println(r);
			if(r>=0)
			{
				System.out.println("Table creation test passed");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Table creation test failed due to "+ex.getMessage());
		}
		//4. disconnect from DB
		con.close();  
	}
}
