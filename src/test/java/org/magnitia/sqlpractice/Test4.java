package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test4 
{
	public static void main(String[] args) throws Exception
	{
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//Perform operations
		Statement st=con.createStatement();  
		//delete row from table
		try
		{
			int r=st.executeUpdate("delete from students where name='john komm';");   
			System.out.println("count of rows have deleted is "+r);
			if(r>=0)
			{
				System.out.println("Deletion test passed");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//disconnect from DB
		con.close();  
	}
}
