package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ketNoi {
	public Connection cn;
	public void ketnoi() throws Exception{
		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		   cn = DriverManager.getConnection(
				   "jdbc:sqlserver://FLEXUY:1433;"
				   + "instanceName=SQLEXPRESS;"
				   + "databaseName=QlSach;"
				   + "user=sa;"
				   + "password=123456;"
				   + "encrypt=false;"
				   + "trustServerCertificate=true"
				);


		   System.out.println("Da ket noi");
		}
}
