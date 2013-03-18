package DAL_Server;

import java.sql.*;

import Model.*;
public class Server_Login_DAO extends Father_DAO {
   public User Login(String username,String password)
   {
	   int staff_id=0;
	   ResultSet result = null;
	   this.conn=get_Connection();
	   try {
		Statement stat_sql=conn.createStatement();
		result=stat_sql.executeQuery("select * from usermanager where username='"+username+"' and password='"+password+"' and staff_type='server'");
		result.next();
		staff_id=result.getInt("staff_id");
	    } catch (SQLException e) {
	    }
	   if(staff_id==0)
		   return null;
	   else
	   {
		   User user=new User();
		   try {
			user.username=result.getString("username");
			user.real_name=result.getString("real_name");
			user.staff_id=String.valueOf(result.getInt("staff_id"));
			user.staff_type=result.getString("staff_type");
		} catch (SQLException e) {}		  
		   return user;
	   }
   }
}
