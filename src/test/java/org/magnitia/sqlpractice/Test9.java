package org.magnitia.sqlpractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Test9 
{
	public static void main(String[] args) throws Exception
	{
		//Open excel file
		File f=new File("src\\test\\resources\\Book1.xlsx");
		//Take READ permission
		FileInputStream fi=new FileInputStream(f);
		//Access that file as an Excel Workbook
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("Sheet1");
		int nour=sh.getPhysicalNumberOfRows(); //count of used rows
		int nouc=sh.getRow(0).getLastCellNum(); //count of used columns
		//Create result column(next to last column) with current date and time as heading
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		Cell rc=sh.getRow(0).createCell(nouc);
		rc.setCellValue("Results on "+sf.format(dt));
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//create object to run queries
		Statement st=con.createStatement();  
		//loop from 2nd row(index=1) in excel file due to 1st row has names of columns
		for(int i=1;i<nour;i++)
		{
			//take data from ith row in sheet1
			DataFormatter df=new DataFormatter();
			String x=df.formatCellValue(sh.getRow((i)).getCell(0));
			String y=df.formatCellValue(sh.getRow((i)).getCell(1));
			String z=df.formatCellValue(sh.getRow((i)).getCell(2));
			String w=df.formatCellValue(sh.getRow((i)).getCell(3));
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
					sh.getRow(i).createCell(nouc).setCellValue("correct insertion");
				}
				else if(count>1)
				{
					sh.getRow(i).createCell(nouc).setCellValue("Duplicate insertion");
				}
				else  //count is 0
				{
					sh.getRow(i).createCell(nouc).setCellValue("Wrong insertion");
				}
			}
			catch(Exception ex)
			{
				sh.getRow(i).createCell(nouc).setCellValue(ex.getMessage());
			}
		}
		sh.autoSizeColumn(nouc); //auto fit on column size
		//disconnect from DB
		con.close();
		//save and close excel
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo);
		wb.close();
		fo.close();
		fi.close();
	}
}
