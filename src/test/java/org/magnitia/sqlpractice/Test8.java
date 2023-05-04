package org.magnitia.sqlpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test8 
{
	public static void main(String[] args) throws Exception
	{
		//Get data from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter rollno");
		String x=sc.nextLine();
		System.out.println("Enter name");
		String y=sc.nextLine();
		System.out.println("Enter course");
		String z=sc.nextLine();
		System.out.println("Enter grade");
		String w=sc.nextLine();
		sc.close();
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
				           "jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//Perform operations
		Statement st=con.createStatement();  
		//insert testing
		try
		{
			//perform insert
			st.executeUpdate("insert into students values("+x+",'"+y+"','"+z+"','"+w+"');");             
			//Select total data after insertion
			ResultSet res=st.executeQuery("select * from students;");
			int count=0;
			while(res.next()) //goto each row in result set
			{
				String p=res.getString(1); //take 1st column(rollno) value in that row
				String q=res.getString(2); //take 2nd column(name) value in that row
				String r=res.getString(3); //take 3rd column(course) value in that row
				String s=res.getString(4); //take 4th column(grade) value in that row
				if(p.equals(x) && q.equals(y) && r.equals(z) && s.equals(w))
				{
					count++;
				}
			}
			if(count==1)
			{
				System.out.println("Correct insertion");
			}
			else if(count>1)
			{
				System.out.println("Duplicate insertion");
			}
			else  //count is 0
			{
				System.out.println("Wrong insertion");
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
