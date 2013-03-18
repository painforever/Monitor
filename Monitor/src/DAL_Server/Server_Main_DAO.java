package DAL_Server;
import Model.*;

import java.awt.geom.FlatteningPathIterator;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
public class Server_Main_DAO extends Father_DAO {
   public ArrayList<User> Load_All_User()
   {
	   ArrayList<User> all_user=new ArrayList<User>();
	  this.conn=get_Connection();
	  try {
		Statement stat_sql_all_user=this.conn.createStatement();
		ResultSet resultSet=stat_sql_all_user.executeQuery("select * from usermanager");
		while(resultSet.next())
		{
		    User user=new User();
		    user.staff_id=String.valueOf(resultSet.getInt("staff_id"));
		    user.username=resultSet.getString("username");
		    user.real_name=resultSet.getString("real_name");
		    user.staff_type=resultSet.getString("staff_type");
		    all_user.add(user);
		}
	} catch (SQLException e) {}
	return all_user;
	  
   }
   
   public boolean Add_New_User(String username,String password,String real_name,String staff_type)
   {
	   boolean success_flag=false;
	   this.conn=get_Connection();
	   try {
		  Statement stat_sql_add_user=this.conn.createStatement();
		  String sql_add_user="insert into usermanager (username,password,real_name,staff_type) values ('"+username+"','"+password+"','"+real_name+"','"+staff_type+"')";
		  stat_sql_add_user.executeUpdate(sql_add_user);
		  success_flag=true;
	   } catch (Exception e) {
		   success_flag=false;
	   }
	   return success_flag;
   }
   public boolean Delete_User(String staff_id)
   {
	   boolean delete_flag=false;
	   this.conn=get_Connection();
	   try {
		  Statement stat_sql_delete_user=this.conn.createStatement();
		  String sql_delete_user="delete from usermanager where staff_id='"+staff_id+"'";
		  stat_sql_delete_user.executeUpdate(sql_delete_user);
		  delete_flag=true;
	   } catch (Exception e) {delete_flag=false;}
	   return delete_flag;
   }
   public User Get_Single_User(String staff_id)
   {
	   this.conn=get_Connection();
	   try {
		Statement stat_sql_search=this.conn.createStatement();
		String sql_search="select * from usermanager where staff_id='"+staff_id+"'";
		ResultSet single_set=stat_sql_search.executeQuery(sql_search);
		single_set.next();
		User user=new User();
		user.staff_id=String.valueOf(single_set.getInt("staff_id"));
		user.username=single_set.getString("username");
		user.real_name=single_set.getString("real_name");
		user.staff_type=single_set.getString("staff_type");
		return user;
	} catch (Exception e) {return null;}
   }
   public boolean Update_User(String staff_id,String username,String password,String real_name,String staff_type)
   {
	   this.conn=get_Connection();
	   try {
		 Statement stat_sql_update=this.conn.createStatement();
		 String sql_update="update usermanager set username='"+username+"',real_name='"+real_name+"',staff_type='"+staff_type+"' where staff_id='"+staff_id+"'";
		 stat_sql_update.executeUpdate(sql_update);
		 return true;
	} catch (Exception e) {return false;}
   }
   public Hashtable<String, String> All_Violation_Type()
   {
	   this.conn=get_Connection();
	   Hashtable<String, String> violation_type=new Hashtable<String, String>();
	   try {
		Statement statement=this.conn.createStatement();
		ResultSet set=statement.executeQuery("select * from violation_name");
		while(set.next())
		{
			violation_type.put(String.valueOf(set.getInt("id")), set.getString("violation_type"));
		}
	} catch (SQLException e) 
	{
		violation_type=null;
		e.printStackTrace();
	}
	 return violation_type;  
   }
   public boolean Add_Violation_Holder(String brand_number,String image_path,String violation_type,String violation_time)
   {
	  this.conn=get_Connection();
	  try {
		Statement statement=this.conn.createStatement();
		String sql_insert="insert into brand (brand_number,image_path,violation_type,violation_time) values ('"+brand_number+"','"+image_path+"','"+violation_type+"','"+violation_time+"')";
		statement.executeUpdate(sql_insert);
		return true;
	} catch (Exception e) {return false;}
   }
   public String[][] Load_All_Violation_Holder()
   {
	   this.conn=get_Connection();
	   try {
		Statement statement=this.conn.createStatement();
		String sql="select holder.holder_name,holder.identify_number,holder.brand_number,brand.violation_time,holder.holder_sex,holder.holder_address,holder.holder_contact from holder,brand where holder.brand_number=brand.brand_number";
		ResultSet set=statement.executeQuery(sql);
		set.last();
		int size=set.getRow();
		set.beforeFirst();
		String[][] all_holders=new String[size][7];
		int index=0;
		while(set.next())
		{
			all_holders[index][0]=set.getString("holder_name");
			all_holders[index][1]=set.getString("identify_number");
			all_holders[index][2]=set.getString("brand_number");
			all_holders[index][3]=set.getString("violation_time");
			all_holders[index][4]=set.getString("holder_sex");
			all_holders[index][5]=set.getString("holder_address");
			all_holders[index][6]=set.getString("holder_contact");
			index++;
		}
		return all_holders;
	   } catch (SQLException e) {e.printStackTrace();return null;}	   
   }
   public String[][] Search_Through_Keyword(String according,String keyword)
   {
	   this.conn=get_Connection();
	   Statement statement;
	try {
		statement = this.conn.createStatement();
		String sql="select holder.holder_name,holder.identify_number,holder.brand_number,brand.violation_time,holder.holder_sex,holder.holder_address,holder.holder_contact from holder,brand where holder.brand_number=brand.brand_number";
		if(according=="plate_number")
		   sql+=" and brand.brand_number='"+keyword+"'";
		else if(according=="ID_Number")
		   sql+=" and holder.identify_number='"+keyword+"'";
		else if(according=="holder_name")
		   sql+=" and holder.holder_name='"+keyword+"'";
		  ResultSet set=statement.executeQuery(sql);
		  System.out.println(sql);
		  set.next();
		  String[][] holder=new String[1][7];
		  holder[0][0]=set.getString("holder_name");
		  holder[0][1]=set.getString("identify_number");
		  holder[0][2]=set.getString("brand_number");
		  holder[0][3]=set.getString("violation_time");
		  holder[0][4]=set.getString("holder_sex");
		  holder[0][5]=set.getString("holder_address");
		  holder[0][6]=set.getString("holder_contact");
		   return holder;
	} catch (SQLException e) {
		return null;
	}
	   
   }
   public boolean Delete_Violation_Brand(String plate_number)
   {
	   this.conn=get_Connection();
	   try {
		  Statement statement=this.conn.createStatement();
		  String sql_delete="delete from brand where brand_number='"+plate_number+"'";
		  statement.executeUpdate(sql_delete);
		  return true;
	   } catch (Exception e) {return false;}
   }
}
