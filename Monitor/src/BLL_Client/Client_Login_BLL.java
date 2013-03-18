package BLL_Client;
import DAL_Client.*;
import Model.User;
public class Client_Login_BLL {
    Client_Login_DAO client_login_dao=new Client_Login_DAO();
    public User Client_Login(String username,String password)
    {
    	User user=client_login_dao.Client_Login(username, password);
    	if(user==null)
    	{
    		return null;
    	}
    	else {
			return user;
		}
    }
}
