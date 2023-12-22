package database

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

import com.kms.katalon.core.annotation.Keyword
import com.sun.corba.se.pept.transport.Connection

public class connection {
	private static Connection connection = null;
	
		@Keyword
		def connectDB(String host, String port, String dbName, String username, String password) {
	
			String conn = "jdbc:postgresql://" + host + ":" +port + "/" + dbName + "?currentSchema=public"
			println(conn)
	
			//jdbc:postgresql://localhost:5432/db_name_sales?currentSchema=SCHEMA
	
			if(connection != null && !connection.isClosed()) {
				connection.close()
			}
			connection = DriverManager.getConnection(conn, username, password)
			return connection
		}
	
		@Keyword
		def executeQuery(String queryString) {
			Statement stm = connection.createStatement()
			ResultSet rs = stm.executeQuery(queryString)
			return rs
		}
	
		@Keyword
		def closeDatabaseConnection() {
			if(connection != null && !connection.isClosed()) {
				connection.close()
			}
			connection = null
		}
	
		@Keyword
		def executeUpdate(String queryString) {
			Statement stm = connection.createStatement()
			boolean result = stm.execute(queryString)
			return result
		}
}
