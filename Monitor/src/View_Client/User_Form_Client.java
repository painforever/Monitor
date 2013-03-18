package View_Client;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class User_Form_Client  extends JFrame implements MouseListener{
	JTextField username_textfield=new JTextField();
	JTextField password_textfield=new JTextField();
	JTextField passwordconfirm_textfield=new JTextField();
	JTextField realname_textfield=new JTextField();
	JComboBox stafftype_comBox=new JComboBox();
	JButton submmit_button=new JButton("submit");
	JButton cancel_button=new JButton("cancel");
    public User_Form_Client()
    {
    	this.setLayout(null);
    	
    	this.username_textfield.setBounds(110,100,150,30);
    	this.password_textfield.setBounds(110,145,150,30);
    	this.passwordconfirm_textfield.setBounds(150, 195, 150, 30);
    	this.realname_textfield.setBounds(110, 245, 150, 30);
    	this.stafftype_comBox.setBounds(110,295,100,30);
    	this.stafftype_comBox.addItem("server");this.stafftype_comBox.addItem("client");
    	this.submmit_button.setBounds(80, 350, 140, 30);
    	this.cancel_button.setBounds(250,350,140,30);
    	
    	//×¢²á¼àÌý
    	this.cancel_button.addMouseListener(this);
    	
    	this.add(password_textfield);
    	this.add(username_textfield);
    	this.add(passwordconfirm_textfield);
    	this.add(realname_textfield);
    	this.add(stafftype_comBox);
    	this.add(submmit_button);
    	this.add(cancel_button);
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
		{
		    this.setVisible(false);	
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
		new User_Form_Client();
	}
}
