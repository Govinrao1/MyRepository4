package org.magnitia.sqlpractice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Test11
{
	public static void main(String[] args) throws Exception
	{
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//call procedure
		CallableStatement st=con.prepareCall("{call addition(?,?)}");
		st.setInt(1,-100); //"inout" parameter
		st.setInt(2,-20); //"in"
		st.registerOutParameter(1,Types.INTEGER);
		st.execute();
		Integer x=(Integer) st.getObject(1,Integer.class);
		System.out.println(x);
		st.close();
		//disconnect from DB
		con.close();  
	}
}
