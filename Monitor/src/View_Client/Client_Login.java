package View_Client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BLL_Client.Client_Login_BLL;
import Model.User;
import View_Server.Server_Login;

public class Client_Login extends JFrame implements MouseListener{
	//BLL对象
	Client_Login_BLL client_login_bll=new Client_Login_BLL();
	JTextField Username=new JTextField();
	JTextField Password=new JTextField();
	JButton Login_Button=new JButton("Client Login");
	JButton Quit_Button=new JButton("Quit");
	JLabel Username_Label=new JLabel("Username:");
	JLabel Password_Label=new JLabel("Password:");
	public Client_Login()
	{
		this.setTitle("Violation Manager");
		this.setSize(700, 500);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		//设置各组件的大小和位置
		this.Username_Label.setBounds(100, 150, 100, 30);
		this.Password_Label.setBounds(100, 200, 100, 30);
		this.Username.setBounds(230,150,200,30);
		this.Password.setBounds(230,200,200,30);
		this.Login_Button.setBounds(80, 280, 130, 40);
		this.Quit_Button.setBounds(380, 280, 130, 40);
		//添加监听
		this.Login_Button.addMouseListener(this);
		//添加组件
		this.add(Login_Button);this.add(Quit_Button);
		this.add(Username);this.add(Password);
		this.add(Username_Label);
		this.add(Password_Label);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Client_Login();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==Login_Button)
		{
			User user=client_login_bll.Client_Login(Username.getText(), Password.getText());
			if(user==null)
				JOptionPane.showConfirmDialog(this, "username or password is wrong!");
			else {
				Client_Main clientMain=new Client_Main(user.username);
				this.setVisible(false);
			}
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
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
