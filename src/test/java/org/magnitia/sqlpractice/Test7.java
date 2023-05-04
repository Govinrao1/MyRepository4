package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test7 
{
	public static void main(String[] args) throws Exception
	{
		//Get data from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter rollno");
		String x=sc.nextLine();
		System.out.println("Enter course name");
		String y=sc.nextLine();
		sc.close();
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//Perform operations
		Statement st=con.createStatement();  
		//update testing
		try
		{
			//perform update
			st.executeUpdate("update students set course='"+y+"' where rollno="+x+";");
			//Select updated data
			ResultSet res=st.executeQuery("select course from students where rollno="+x+";");
			res.next(); //goto 1st row in result set
			String z=res.getString(1); //take 1st column value(course column as per selection)
			if(y.equals(z))
			{
				System.out.println("Updated successfully");
			}
			else
			{
				System.out.println("Wrong updation");
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
