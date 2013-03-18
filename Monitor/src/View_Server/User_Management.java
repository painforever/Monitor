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
	//BLL����
	Server_Main_BLL server_main_bll=new Server_Main_BLL();
	//��ǰѡ�е�staff_id
	String current_staff_id="";
	JButton add_user_button=new JButton("Add New User");
	JButton delete_user_button=new JButton("delete user");
	JButton update_user_button=new JButton("update user");
	JTextField key_word_textfield=new JTextField();
	//���Ķ���
    JTable table;
	Object table_data[][]=null;
	JScrollPane jScrollPane;
	Object column_name[]={"staff id","username","real_name","staff type"}; //table�������
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawRect(0,0, 760, 820);
	}
    public User_Management()
    {
    	Load_All_User();
    	//���ñ��
    	table=new JTable(table_data,column_name);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,300,500);
		jScrollPane.setBounds(380,30,350,500);
    	this.setLayout(null);
    	this.add_user_button.setBounds(100,40,130,30);
    	this.key_word_textfield.setBounds(40,100,270,30);
    	this.delete_user_button.setBounds(30,140,130,30);
    	this.update_user_button.setBounds(190,140,130,30);
    	
    	//ע�����
    	add_user_button.addMouseListener(this);
    	delete_user_button.addMouseListener(this);
    	this.update_user_button.addMouseListener(this);
    	this.table.addMouseListener(this);
    	
    	//������
    	this.add(delete_user_button);
    	this.add(update_user_button);
    	this.add(add_user_button);
    	this.add(key_word_textfield);
    	this.add(jScrollPane);
    }
    //���¼��ر��
    private void  Reload_Table() 
    {
    	this.remove(table);
		this.remove(jScrollPane);
		//���ñ��
    	table=new JTable(table_data,column_name);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,300,500);
		jScrollPane.setBounds(380,30,350,500);
		this.table.addMouseListener(this);
		this.add(jScrollPane);
	}
    //��ʾ�����û�
    public void Load_All_User()
    {
    	ArrayList<User> all_user=server_main_bll.Load_All_User();
    	//��tabel����ʾ����
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
