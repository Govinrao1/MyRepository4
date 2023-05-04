package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test6
{
	public static void main(String[] args) throws Exception
	{
		//Get data from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter rollno");
		String x=sc.nextLine();
		sc.close();
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//Perform operations
		Statement st=con.createStatement();  
		//delete testing
		try
		{
			//perform delete
			st.executeUpdate("delete from students where rollno="+x+";");
			//Select total data after deletion
			ResultSet res=st.executeQuery("select * from students;");
			int count=0;
			while(res.next()) //goto each row in result set
			{
				String z=res.getString(1); //take 1st column(rollno is primary key) value
				if(z.equals(x))
				{
					count++;
				}
			}
			if(count==0)
			{
				System.out.println("correct deletion");
			}
			else
			{
				System.out.println("Wrong deletion");
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
