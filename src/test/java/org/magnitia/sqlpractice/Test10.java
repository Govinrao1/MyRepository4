package org.magnitia.sqlpractice;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test10 
{
	public static void main(String[] args) throws Exception
	{
		//Get file name via current date and time
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		File f=new File("src\\test\\resources\\"+sf.format(dt)+".xlsx");
		//create new Excel file with a new sheet
		FileOutputStream fo=new FileOutputStream(f);
		Workbook wb=new XSSFWorkbook(); 
		//XSSFWorkbook class in POI-OOxml for xlsx file
		//HSSFWorkbook class in POI-OOxml for xls file
		wb.createSheet("Sheet1");
		Sheet sh=wb.getSheet("Sheet1");
		//Register driver class w.r.t RDBMS technology
		Class.forName("com.mysql.jdbc.Driver"); //for MySQL
		//connect to DB
		Connection con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/kalamsir","root","magnitia");
		//Perform an operation to get data from DB table
		Statement st=con.createStatement();  
		ResultSet res=st.executeQuery("select * from students;");
		ResultSetMetaData rsmd=res.getMetaData();
	    int colsinres=rsmd.getColumnCount();
	    //Create required number of columns in excel file's sheet
		Row datarow=sh.createRow(0); //create 1st row in excel sheet for column headings
		for(int i=1;i<=colsinres;i++) //goto each column in current row in result set
		{
			String z=rsmd.getColumnName(i); //take column name in result set
			datarow.createCell(i-1).setCellValue(z); //send that name to column in excel sheet
		}
		//Create required number of rows in excel file's sheet and copy data from result-set
		int r=1; //1th row means 2nd row in excel
		while(res.next()) //goto each row in result set
		{
			datarow=sh.createRow(r); //create next row in excel sheet
			for(int i=1;i<=colsinres;i++) //goto each column in current row in result set
			{
				String z=res.getString(i); //take value from column in result set
				datarow.createCell(i-1).setCellValue(z); //send value to column in excel sheet
			}
			r=r+1;
		}
		//Set right the size of all columns in excel file
		int nouc=sh.getRow(0).getLastCellNum();
		for(int i=0;i<nouc;i++) 
		{
			sh.autoSizeColumn(i);
		}
		//disconnect from DB
		con.close(); 
		//save and close excel file
		wb.write(fo); //save
		wb.close();
		fo.close();
	}
}
