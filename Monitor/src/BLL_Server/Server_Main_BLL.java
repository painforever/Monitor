package BLL_Server;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Pattern;

import Model.*;
import DAL_Server.*;
public class Server_Main_BLL {
	Server_Main_DAO server_main_dao=new Server_Main_DAO();
   public ArrayList<User> Load_All_User()
   {
	   ArrayList<User> all_users=server_main_dao.Load_All_User();
	   return all_users;
   }
   public String Add_New_User(String username,String password,String real_name,String staff_type)
   {
	   String pattern="^[a-zA-Z]+$";
	   if(Pattern.matches(pattern, username)&&Pattern.matches(pattern, real_name))
	   {
		   boolean register_flag=server_main_dao.Add_New_User(username, password, real_name, staff_type);
		   if(register_flag) return "OK";
		   else return "Failed";
	   }
	   else{return "the input is invalid format!";}
   }
   public String Delete_User(String staff_id)
   {
	   boolean delete_flag= server_main_dao.Delete_User(staff_id);
	   if(delete_flag) return "OK";
	   else{return "Failed";}
   }
   public User Get_Single_User(String staff_id)
   {
	   User user=server_main_dao.Get_Single_User(staff_id);
	   if(user!=null) return user;
	   else{return null;}
   }
   public String Update_User(String staff_id,String username,String password,String real_name,String staff_type)
   {
	   boolean result=server_main_dao.Update_User(staff_id, username, password, real_name, staff_type);
	   if(result){return "OK";}
	   else{return "Failed";}
   }
   public Hashtable<String, String> All_Violation_Type()
   {
	   Hashtable<String, String> all_violation_type=server_main_dao.All_Violation_Type();
	   return all_violation_type;
   }
   public String Add_Violation_Holder(String brand_number,String image_path,String violation_type,String violation_time)
   {
	   boolean flag=server_main_dao.Add_Violation_Holder(brand_number, image_path, violation_type, violation_time);
	   if(flag)
	   {
		   return "add new violated holder success!";
	   }
	   else
		   return "failed to add!";
   }
   public String[][] Load_All_Violation_Holder()
   {
	   String[][] all_holders=server_main_dao.Load_All_Violation_Holder();
	   return all_holders;
   }
   public String[][] Search_Through_Keyword(String according,String keyword)
   {
	   String[][] holder=server_main_dao.Search_Through_Keyword(according, keyword);
	   return holder;
   }
   public String Delete_Violation_Brand(String plate_number)
   {
	   boolean flag=server_main_dao.Delete_Violation_Brand(plate_number);
	   if(flag)
	   {
		   return "delete success!";
	   }
	   else
		   return "failed to delete!";
   }
}
