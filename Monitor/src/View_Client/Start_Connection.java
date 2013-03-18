package View_Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Start_Connection extends JFrame implements MouseListener {

	JTextField IPAddress=new JTextField();
	JTextField Port=new JTextField("6000");
	JLabel IP_Label=new JLabel("IP Address:");
	JLabel port_Label=new JLabel("Port:");
	JButton Connect_Button=new JButton("Connect Server");
	JButton Quit_Button=new JButton("Quit");
	public Start_Connection()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setTitle("Traffic Violations System");
		this.setSize(500,500);
		this.setLocation(200,200);
		//设置组件们的大小和位置
		this.IP_Label.setBounds(120, 250, 130, 20);
		this.port_Label.setBounds(120, 300, 130, 20);
		this.IPAddress.setBounds(200, 250, 200, 30);
		this.Port.setBounds(200, 300, 200, 30);
		this.Connect_Button.setBounds(80, 400, 150, 40);
		this.Quit_Button.setBounds(300, 400, 150, 40);
		//添加组件
		this.add(IP_Label);
		this.add(port_Label);
		this.add(IPAddress);
		this.add(Port);
		this.add(Connect_Button);
		this.add(Quit_Button);
		//增加鼠标监听
		this.Quit_Button.addMouseListener(this);
		this.Connect_Button.addMouseListener(this);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Start_Connection();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==Connect_Button)
		{
		    System.out.println("kjfskllsss");	
		}
		else if(e.getSource()==Quit_Button)
		{
			System.exit(0);
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
