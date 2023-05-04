package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test5 
{
	public static void main(String[] args) throws Exception
	{
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/kalamsir","root","magnitia"); //for MySQL
		//Perform operations
		Statement st=con.createStatement();  
		//update in table
		try
		{
		 int r=st.executeUpdate("update students set course='web services' where rollno=3;");
		 System.out.println("count of rows have updated is "+r);
			if(r>=0)
			{
				System.out.println("Updation test passed");
			}
			else
			{
				System.out.println("Updation test failed");
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
