package View_Server;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import BLL_Server.Server_Main_BLL;
import Model.User;

public class User_Management extends JPanel implements MouseListener {
	//BLL对象
	Server_Main_BLL server_main_bll=new Server_Main_BLL();
	//当前选中的staff_id
	String current_staff_id="";
	JButton add_user_button=new JButton("Add New User");
	JButton delete_user_button=new JButton("delete user");
	JButton update_user_button=new JButton("update user");
	JTextField key_word_textfield=new JTextField();
	//表格的定义
    JTable table;
	Object table_data[][]=null;
	JScrollPane jScrollPane;
	Object column_name[]={"staff id","username","real_name","staff type"}; //table里的列名
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawRect(0,0, 760, 820);
	}
    public User_Management()
    {
    	Load_All_User();
    	//设置表格
    	table=new JTable(table_data,column_name);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,300,500);
		jScrollPane.setBounds(380,30,350,500);
    	this.setLayout(null);
    	this.add_user_button.setBounds(100,40,130,30);
    	this.key_word_textfield.setBounds(40,100,270,30);
    	this.delete_user_button.setBounds(30,140,130,30);
    	this.update_user_button.setBounds(190,140,130,30);
    	
    	//注册监听
    	add_user_button.addMouseListener(this);
    	delete_user_button.addMouseListener(this);
    	this.update_user_button.addMouseListener(this);
    	this.table.addMouseListener(this);
    	
    	//添加组件
    	this.add(delete_user_button);
    	this.add(update_user_button);
    	this.add(add_user_button);
    	this.add(key_word_textfield);
    	this.add(jScrollPane);
    }
    //重新加载表格
    private void  Reload_Table() 
    {
    	this.remove(table);
		this.remove(jScrollPane);
		//设置表格
    	table=new JTable(table_data,column_name);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,300,500);
		jScrollPane.setBounds(380,30,350,500);
		this.table.addMouseListener(this);
		this.add(jScrollPane);
	}
    //显示所有用户
    public void Load_All_User()
    {
    	ArrayList<User> all_user=server_main_bll.Load_All_User();
    	//在tabel中显示出来
    	this.table_data=new Object[all_user.size()][4];
    	try {
    		for (int i = 0; i < all_user.size(); i++) {
        		User user=all_user.get(i);
    			this.table_data[i][0]=user.staff_id;
    			this.table_data[i][1]=user.username;
    			this.table_data[i][2]=user.real_name;
    			this.table_data[i][3]=user.staff_type;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==add_user_button)
		{
			User_Form_Server userFormServer=new User_Form_Server();
			userFormServer.setVisible(true);
		}
		if(e.getSource()==table)
		{
			this.current_staff_id=table_data[table.getSelectedRow()][0].toString();
		}
		if(e.getSource()==delete_user_button)
		{
		    String result=server_main_bll.Delete_User(this.current_staff_id);
		    if(result=="OK") 
		    {
		    	JOptionPane.showMessageDialog(this, "Delete Success!");
		    }
		    else{JOptionPane.showMessageDialog(this, "Failed to Delete!");}
		    Load_All_User();
		    Reload_Table();
		}
		if(e.getSource()==update_user_button)
		{
			if(this.table.getSelectedRows().length>0)
			{
				User_Form_Server userFormServer=new User_Form_Server(this.table_data[table.getSelectedRow()][0].toString());
			}
			else{JOptionPane.showMessageDialog(this, "you need to select a row!");}
			Load_All_User();
			Reload_Table();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
