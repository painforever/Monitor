package View_Client;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import BLL_Client.FileClient;
import BLL_Server.FileServer;

public class Client_Main extends JFrame implements MouseListener,ListSelectionListener {
	//客户端的socket
	Socket socket;
	//发送文件的类
	FileClient send_file_client;
	//线程类
	Thread file_thread;
	//文件选择组件
	JFileChooser jFileChooser;
    Image_Review_Panel imageReviewPanel=new Image_Review_Panel();
    JButton browse_button=new JButton("Browse files");
    JButton delete_button=new JButton("delete");
    JButton start_send_button=new JButton("start transport");
    JButton stop_button=new JButton("stop");
    //表格的定义
    DefaultTableModel model;
    JTable table;
	Object table_data[][]={{1,2,3,4,5,6,7},{1,2,3,4,5,6,7}};
	JScrollPane jScrollPane;
	Object column_name[]={"file name","size(bit)","path"}; //table里的列名
	public Client_Main(String username)
	{
		//只要登陆,就发一条信息给
		try {
			this.socket=new Socket(InetAddress.getLocalHost(),6000);
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(username);
			dos.close();
			this.socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();}
		send_file_client=new FileClient();
	//	file_thread=new Thread(send_file_client);
		model=new DefaultTableModel(new Object[][]{},column_name);
		jFileChooser=new JFileChooser();
		this.setSize(800,600);
	    this.setLocation(200,200);
		this.setVisible(true);
		this.setLayout(null);
		//设置表格
	//	table=new JTable(table_data,column_name);
		table=new JTable(model);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,300,300);
		jScrollPane.setBounds(400,30,350,300);
		browse_button.setBounds(20,400,130,30);
		delete_button.setBounds(170,400,130,30);
		start_send_button.setBounds(430,400,130,30);
		stop_button.setBounds(580,400,130,30);
		imageReviewPanel.setBounds(20,30,350,350);
		//添加监听
		this.browse_button.addMouseListener(this);
		this.delete_button.addMouseListener(this);
		table.getSelectionModel().addListSelectionListener(this);
		this.start_send_button.addMouseListener(this);
		this.add(jScrollPane);
		this.add(imageReviewPanel);
		this.add(browse_button);
		this.add(delete_button);
		this.add(start_send_button);
		this.add(stop_button);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	public static void main(String[] args) {
	//	new Client_Main();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==browse_button)
		{
			jFileChooser.setFileFilter(new MyFilter());
			int chooser_flag=jFileChooser.showOpenDialog(this);
			if(chooser_flag==0&&jFileChooser.getSelectedFile().getAbsolutePath()!=""&&jFileChooser.getSelectedFile().getAbsolutePath()!=null)
			{
				try {
					imageReviewPanel.image=ImageIO.read(new File(jFileChooser.getSelectedFile().getAbsolutePath()));
				} catch (IOException e1) {}
				if(imageReviewPanel.flag==false)
				{
					imageReviewPanel.flag=true;
					repaint();
				}
				String absolute_path=jFileChooser.getSelectedFile().getAbsolutePath();
				String file_name=jFileChooser.getSelectedFile().getName();
				File file=new File(absolute_path);
				model.insertRow(model.getRowCount(), new Object[]{file_name,file.length(),absolute_path});
			}
		}
		if(e.getSource()==delete_button)
		{
			if(table.getSelectedRows().length==0) 
			{
				JOptionPane.showMessageDialog(this, "please select at least 1 row!");
				return;
			}
			imageReviewPanel.image=null;
			try {
				model.removeRow(table.getSelectedRow());
				Reload_Table();
				repaint();
			} catch (Exception e2) {}
		}
		if(e.getSource()==start_send_button)
		{
		//	send_file_client.Send_File(this.model.getValueAt(table.getSelectedRow(),2).toString());
//		    this.send_file_client.file_path=this.model.getValueAt(table.getSelectedRow(),2).toString();
//		    this.send_file_client.file_name=this.model.getValueAt(table.getSelectedRow(),0).toString();
		//	file_thread.start();
		    this.send_file_client.Send_Name(this.model.getValueAt(table.getSelectedRow(),0).toString());
		    this.send_file_client.Send_Violation_Time(this.model.getValueAt(table.getSelectedRow(),2).toString());
		    this.send_file_client.Send_File(this.model.getValueAt(table.getSelectedRow(),2).toString());
		}
	}
	//重新加载表格
    private void  Reload_Table() 
    {
    	Object[][] tabel_data=new Object[this.table.getRowCount()][this.table.getColumnCount()];
    	for (int i = 0; i < this.table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				tabel_data[i][j]=model.getValueAt(this.table.getSelectedRow(), table.getSelectedColumn());
			}
		}  	
    	this.remove(table);
		this.remove(jScrollPane);
		//设置表格
		model=new DefaultTableModel(tabel_data,this.column_name);
    	table=new JTable(model);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,300,300);
		jScrollPane.setBounds(400,30,350,300);
		this.table.addMouseListener(this);
		this.table.getSelectionModel().addListSelectionListener(this);
		this.add(jScrollPane);
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
//内部类过滤文件
	class MyFilter extends FileFilter
	{
		@Override
		public boolean accept(File f) {
			String file_name=f.getName().toLowerCase();
			if(file_name.endsWith(".jpg")||file_name.endsWith(".bmp")||file_name.endsWith(".png")||f.isDirectory())
			{
			   return true;	
			}
			return false;
		}

		@Override
		public String getDescription() {
			return "图像文件(*.jpg,*png,*bmp)";
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			this.imageReviewPanel.image=ImageIO.read(new File(model.getValueAt(table.getSelectedRow(),2).toString()));
		} catch (IOException e1) {}
	}
}
