package View_Server;

import java.awt.Graphics;
import java.awt.JobAttributes;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.FlatteningPathIterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import BLL_Server.Server_Main_BLL;
import Model.User;
public class User_Form_Server  extends JFrame implements MouseListener{
	//如果要修改的话,需规定一个staff_id的变量来存储
	String staff_id="";
	//BLL 对象
	Server_Main_BLL server_main_bll=new Server_Main_BLL();
	JTextField username_textfield=new JTextField();
	JTextField password_textfield=new JTextField();
	JTextField passwordconfirm_textfield=new JTextField();
	JTextField realname_textfield=new JTextField();
	JComboBox stafftype_comBox=new JComboBox();
	JButton submmit_button=new JButton("submit");
	JButton cancel_button=new JButton("cancel");
	JButton update_button=new JButton("update");
    public User_Form_Server()
    {
    	Init("");
    }
    public User_Form_Server(String staff_id)
    {
    	Init(staff_id);
    	User user=server_main_bll.Get_Single_User(staff_id);
    	username_textfield.setText(user.username);
    	realname_textfield.setText(user.real_name);
    	stafftype_comBox.setSelectedItem(user.staff_type);	
    	this.setVisible(true);
    }
    private void Init(String staff_id) {//初始化所有的控件
        this.setLayout(null);
    	
    	this.username_textfield.setBounds(110,100,150,30);
    	this.password_textfield.setBounds(110,145,150,30);
    	this.passwordconfirm_textfield.setBounds(150, 195, 150, 30);
    	this.realname_textfield.setBounds(110, 245, 150, 30);
    	this.stafftype_comBox.setBounds(110,295,100,30);
    	this.stafftype_comBox.addItem("server");this.stafftype_comBox.addItem("client");
    	this.submmit_button.setBounds(80, 350, 140, 30);
    	this.update_button.setBounds(80, 350, 140, 30);
    	this.cancel_button.setBounds(250,350,140,30);
    	
    	//注册监听
    	this.cancel_button.addMouseListener(this);
    	
    	this.add(password_textfield);
    	this.add(username_textfield);
    	this.add(passwordconfirm_textfield);
    	this.add(realname_textfield);
    	this.add(stafftype_comBox);
    	this.add(submmit_button);
    	this.add(update_button);
    	this.add(cancel_button);
    	//添加监听
    	this.submmit_button.addMouseListener(this);
    	this.cancel_button.addMouseListener(this);
    	this.update_button.addMouseListener(this);
    	if(staff_id!="")
    	{
    		this.staff_id=staff_id;
    		this.password_textfield.setVisible(false);
    		this.passwordconfirm_textfield.setVisible(false);
    		this.update_button.setVisible(true);
    		this.submmit_button.setVisible(false);
    	}
    	else{this.submmit_button.setVisible(true);this.update_button.setVisible(false);}
    	this.setTitle("Add User");
    	this.setSize(500,800);
    	this.setVisible(true);
    	this.setLocation(200, 50);
	}
    @Override
    public void paint(Graphics g) {
    	// TODO Auto-generated method stub
    	super.paint(g);
    	g.drawString("username:", 50, 150);
    	g.drawString("password:", 50, 200);
    	g.drawString("password confirm:", 50, 250);
    	g.drawString("real name:", 50, 300);
    	g.drawString("staff type:", 50, 350);
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==cancel_button)
		    this.setVisible(false);	
		if(e.getSource()==submmit_button){
			if(password_textfield.getText().equals(passwordconfirm_textfield.getText()))
			{
				String result=server_main_bll.Add_New_User(username_textfield.getText(), password_textfield.getText(), realname_textfield.getText(), stafftype_comBox.getSelectedItem().toString());
				if(result=="OK")
					JOptionPane.showConfirmDialog(this, "register success!");	
				else if(result=="Failed")
				    JOptionPane.showConfirmDialog(this, "failed");
				else{JOptionPane.showConfirmDialog(this, result);}					
			}
			else{JOptionPane.showConfirmDialog(this, "2 times password input is not same!");}
		}
		if(e.getSource()==update_button)
		{
			String resultr=server_main_bll.Update_User(this.staff_id, username_textfield.getText(), password_textfield.getText(), realname_textfield.getText(), stafftype_comBox.getSelectedItem().toString());
			if(resultr=="OK")
			{
				JOptionPane.showMessageDialog(this, "update success!");
				this.setVisible(false);
			}
			else{JOptionPane.showMessageDialog(this, "Failed");}
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
    public static void main(String[] args) {
	//	new User_Form_Server();
	}
}
