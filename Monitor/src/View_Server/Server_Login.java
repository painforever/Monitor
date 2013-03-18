package View_Server;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import BLL_Server.*;
public class Server_Login extends JFrame implements MouseListener {
    //BLL对象
	Server_Login_BLL server_login_bll=new Server_Login_BLL();
	JTextField Username=new JTextField();
	JTextField Password=new JTextField();
	JButton Login_Button=new JButton("Login");
	JButton Quit_Button=new JButton("Quit");
	JLabel Username_Label=new JLabel("Username:");
	JLabel Password_Label=new JLabel("Password:");
	public Server_Login()
	{
		this.setTitle("Server Login");
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
		new Server_Login();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==Login_Button)
		{
			boolean flag=server_login_bll.Login(this.Username.getText(), this.Password.getText());
			if(flag)
			{
				Server_Main main=new Server_Main();
				this.setVisible(false);
			}
			else
				JOptionPane.showMessageDialog(this, "username or password is wrong!");
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
