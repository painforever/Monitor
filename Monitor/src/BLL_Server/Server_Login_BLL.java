package BLL_Server;
import DAL_Server.*;
import Model.*;
public class Server_Login_BLL {
    //DALµÄLogin¶ÔÏó
	Server_Login_DAO server_login_dao=new Server_Login_DAO(); 
   public boolean Login(String username,String password)
   {
	   User user=server_login_dao.Login(username, password);
	   if(user==null)
	   {
		  return false;   
	   }
	   else
		   return true;
   }
}
