package DAL_Server;
import java.sql.*;
import java.sql.Connection;

import com.mysql.jdbc.*;
public class Father_DAO {
	public Connection conn;
   public Connection get_Connection()
   {
	   try {
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Monitor?user=root&password=");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
       return conn;
   }
}
