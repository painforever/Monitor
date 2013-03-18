package View_Server;
/*
 * 在这个类中本来要用FileServer来用线程的方式作,但是最后
 * 还是没用,因为JTabel不好操作,所以直接把FileServer里接收
 * 文件的代码考了过来,直接放到了内部类线程的run方法里,这样
 * 可以值用model接操作到JTabel了(FileServer暂时没用上)
 * 由于Check_And_Display_File函数未调用,所以现在暂时不能
 * 在加载时就扫描有无图片
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import BLL_Server.FileServer;
import BLL_Server.Server_Main_BLL;

public class Image_Analyze extends JPanel implements MouseListener,ListSelectionListener,ActionListener{
	//BLL对象
	Server_Main_BLL server_main_bll=new Server_Main_BLL();
	BufferedImage image;//局部图片1
	BufferedImage image2;//局部图片2
	
	Timer timer;
	Image_Part_Panel image_part_panel;
    //图片的滚动条
	JScrollPane jScrollPane_for_image;
	JScrollPane jScrollPane_for_part_image;
	Socket client;
	Socket client_login;
	ServerSocket serverSocket;
	ServerSocket serverSocket_for_client;
	//图片的个数
	int image_count=0;
	//接收文件的类
	//FileServer file_server;
	//实时捡查图片的内部类
	myThread check_image_thread;
	//线程类
	Thread file_thread;
	//查看图片的线程类
	Thread check_image_file_thread;
	//监听用户的线程
	Thread wating_for_client_thread;
	Client_Thread client_thread;
	//以下是6个面板
	JPanel port_panel=new JPanel();
	JPanel image_panel=new JPanel();
	JPanel status_panel=new JPanel();
//	JPanel violation_plate_image_panel=new JPanel();
	JPanel violation_plate_panel=new JPanel();
	JPanel violation_car_info=new JPanel();
	//面板里的组件
	JLabel port_label=new JLabel("端口:");
	JTextField port_textfield=new JTextField("6000");
	JButton start_service_button=new JButton("start service");
	JButton quit_service_button=new JButton("quit");
	JLabel service_status_label=new JLabel("server has not been started.....");
	
	DefaultTableModel model;
	
	JTable table;
	Object table_data[][]={};
	JScrollPane jScrollPane;
	Object column_name[]={"violation image","violation time"}; //table里的列名
	JButton identify_button=new JButton("identify");
	JLabel plate_number_label=new JLabel("plate number:");
	JLabel violation_time_label=new JLabel("violation time:");
	JLabel violation_type_label=new JLabel("violation_type:");
	JTextField plate_number_textfield=new JTextField();
	JTextField violation_time_textfield=new JTextField();
	JButton add_button=new JButton("Add");
	JComboBox violation_type_comBox=new JComboBox();
	Image_Entire_Panel imageEntirePanel=new Image_Entire_Panel();
	public Image_Analyze()//构造
	{
		image_part_panel=new Image_Part_Panel();
		this.client_thread=new Client_Thread();
		this.wating_for_client_thread=new Thread(client_thread);
		model= new DefaultTableModel(table_data,column_name);
		try {
			this.serverSocket=new ServerSocket(3108);	
			this.serverSocket_for_client=new ServerSocket(6000);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		file_server=new FileServer();
		check_image_thread=new myThread();
//		file_thread=new Thread(file_server);
		check_image_file_thread=new Thread(check_image_thread);
		this.setLayout(null);
		//设置表格
		table=new JTable(model);
		table.setRowHeight(20);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//设置标题
		port_panel.setBorder(BorderFactory.createTitledBorder(""));
		image_panel.setBorder(BorderFactory.createTitledBorder("Image"));
		status_panel.setBorder(BorderFactory.createTitledBorder("status"));
	//	violation_plate_image_panel.setBorder(BorderFactory.createTitledBorder("violation plate image"));
		violation_plate_panel.setBorder(BorderFactory.createTitledBorder("violation plate"));
		violation_car_info.setBorder(BorderFactory.createTitledBorder("Violation car info"));
		//设定位置
		image_panel.setBounds(10,140,400,400);
		port_panel.setBounds(10,10,400,100);
		status_panel.setBounds(10,580,400,100);
	//	image_part_panel.setBounds(450,10,250,100);
	//	violation_plate_image_panel.setBounds(450,10,250,100);
		violation_plate_panel.setBounds(450,160,250,300);
		violation_car_info.setBounds(450,480,280,280);
		port_label.setBounds(20,30,100,40);
		port_textfield.setBounds(70,30,80,40);
		start_service_button.setBounds(160,30,110,40);
		quit_service_button.setBounds(280,30,110,40);
		service_status_label.setBounds(20,30,300,30);
		table.setBounds(10,10,450,450);
		jScrollPane.setBounds(15,20,220,200);
		identify_button.setBounds(80,230,100,40);
		plate_number_label.setBounds(10,30,90,30);
		violation_time_label.setBounds(10,90,90,30);
		violation_type_label.setBounds(10,150,90,30);
		plate_number_textfield.setBounds(100,30,160,30);
		violation_time_textfield.setBounds(100,90,160,30);
		add_button.setBounds(60,210,140,40);
		violation_type_comBox.setBounds(100,150,100,30);
		
		imageEntirePanel.setBounds(15,20,380,360);
		//设置所有面板的布局为null
		port_panel.setLayout(null);
		image_panel.setLayout(null);
		status_panel.setLayout(null);
		violation_car_info.setLayout(null);
//		violation_plate_image_panel.setLayout(null);
		violation_plate_panel.setLayout(null);
		//添加监听
		this.add_button.addMouseListener(this);
		this.identify_button.addMouseListener(this);
		this.start_service_button.addMouseListener(this);
		this.table.getSelectionModel().addListSelectionListener(this);
		this.addMouseListener(this);
		//添加控件
		this.add(port_panel);
		this.add(image_panel);
		this.add(status_panel);
	//	this.violation_plate_image_panel.add(image_part_panel);
	//	this.add(violation_plate_image_panel);
		this.add(violation_plate_panel);
		this.add(violation_car_info);
		this.port_panel.add(port_label);
		this.port_panel.add(port_textfield);
		this.port_panel.add(start_service_button);
		this.port_panel.add(quit_service_button);
		this.status_panel.add(service_status_label);
		this.violation_plate_panel.add(jScrollPane);
		this.violation_plate_panel.add(identify_button);
		this.violation_car_info.add(plate_number_label);
		this.violation_car_info.add(violation_time_label);
		this.violation_car_info.add(plate_number_textfield);
		this.violation_car_info.add(violation_time_textfield);
		this.violation_car_info.add(add_button);
	
		Load_All_Violation_Type();
		this.violation_car_info.add(violation_type_comBox);
		this.violation_car_info.add(violation_type_label);
		imageEntirePanel.setPreferredSize(new Dimension(1500,1500));
		jScrollPane_for_image=new JScrollPane(imageEntirePanel);
		jScrollPane_for_image.setBounds(15,20,380,360);
		this.image_panel.add(jScrollPane_for_image);
		
	//	image_part_panel.setPreferredSize(new Dimension(1300,1100));
	//	jScrollPane_for_part_image=new JScrollPane(image_part_panel);
	//	jScrollPane_for_part_image.setBounds(450,10,250,100);
	//	this.violation_plate_image_panel.add(jScrollPane_for_part_image);
		this.setSize(760, 820);
		this.setVisible(true);
	}
	private void Load_All_Violation_Type() 
	{
		Hashtable<String, String> all_violation=this.server_main_bll.All_Violation_Type();
		Iterator iterator=all_violation.entrySet().iterator();
		while(iterator.hasNext())
		{
			Entry entry=(Entry)iterator.next();
			this.violation_type_comBox.addItem(entry.getValue());
		}
	}
	//重新加载表格
    private void  Reload_Table() 
    {
    	this.remove(table);
		this.remove(jScrollPane);
		//设置表格
    	table=new JTable(table_data,column_name);
		jScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setBounds(10,10,450,450);
		jScrollPane.setBounds(15,20,220,200);
		this.table.addMouseListener(this);
		this.violation_plate_panel.add(jScrollPane);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawRect(0,0, 760, 820);
		if(this.image!=null)
           g.drawImage(image, 450,10,270,70,null);
        if(this.image2!=null)
           g.drawImage(image2, 450,80,270,70,null);
		}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==identify_button)
		{
			if(imageEntirePanel.flag==false)
			{
			   imageEntirePanel.flag=true;	
			   repaint();
			}
		}
		if(e.getSource()==start_service_button)
		{
			//file_thread.start();
			check_image_file_thread.start();
			service_status_label.setText("server started now!");
			wating_for_client_thread.start();
			timer=new Timer(1000, new myTimer());
			timer.start();
		}
		if(e.getSource()==identify_button)
		{
			String project_path=new File("").getAbsolutePath();
			File srcFile=new File(project_path+"/temp.jpg");
				//把图片变成全黑白
				BufferedImage temp_image;
				try {
					temp_image = ImageIO.read(srcFile);
					for (int i = 0; i < temp_image.getHeight(); i++) {
						for (int j = 0; j < temp_image.getWidth(); j++) {
							if(temp_image.getRGB(j, i)>=-8388608)
								temp_image.setRGB(j, i, -1);
							else
								temp_image.setRGB(j, i, -16777216);
						}
					}
				    this.image2=temp_image;
				    System.out.println("adjslkdjlsdjls");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		if(e.getSource()==add_button)
		{
			String type=this.violation_type_comBox.getSelectedItem().toString();
			String time=this.violation_time_textfield.getText();
			String plate_number=this.plate_number_textfield.getText();
			String image_path=this.model.getValueAt(table.getSelectedRow(), 0).toString();
			String result=server_main_bll.Add_Violation_Holder(plate_number, image_path, type, time);
			JOptionPane.showMessageDialog(this, result);
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
	}
	 public void Check_And_Display_File()
	 {
		   String path=new File("").getAbsolutePath();
			  File file=new File(path+"/images");
		      File[] files=file.listFiles();
		      if(file.length()==0) return;
		      int index=0;//表格的索引
		      this.table_data=new Object[files.length][2];
		      for(File fi:files)
		      {
		    	  int pos=fi.toString().indexOf(".");
		    	  if(pos!=-1)
		    	  {
		    		  if(fi.toString().substring(pos+1).equals("jpg")||fi.toString().substring(pos+1).equals("bmp")||fi.toString().substring(pos+1).equals("png")) 
		        	  {
		    			 //System.out.println(fi);
		    			  File image_file=new File(fi.toString());
		    			  String file_name=image_file.getName();//获得文件名
		    			  //读txt文件获取违章时间
		    			  BufferedReader reader;
						try {
							reader = new BufferedReader(new FileReader(fi.toString().substring(0, pos)+".txt"));
							String violation_time=reader.readLine();//获到违章时间
							this.table_data[index][0]=file_name;
							this.table_data[index][1]=violation_time;
							index++;
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		  } 
		    	  } 	  
		      }
		      if(image_count!=files.length)
		    	  
		      this.image_count=files.length;
	   }
    //内部类,等待传送图片
	class myThread implements Runnable
	{
		@Override
		public void run() {
			try {
				   while(true)
				   {					   
					   client=serverSocket.accept();
					  // Check_And_Delete_File();//先删除现有图片在发送新图片
					   DataInputStream from_client=new DataInputStream(client.getInputStream());
					   String file_name=from_client.readUTF();
					   from_client.close();
					   client.close();
					   //开始发送违章时间,这里采用新建一个与图片同名的txt文件并存入时间的办法
					   client=serverSocket.accept();
					   DataInputStream dis=new DataInputStream(client.getInputStream());
					   String violation_time=dis.readUTF();
					   dis.close();
					   client.close();
					   String path=new File("").getAbsolutePath();//开始创建txt文件
					   int dot_point=file_name.indexOf(".");		   
					   File txt_file=new File(path+"/images/"+file_name.substring(0, dot_point)+".txt");
					   txt_file.createNewFile();
					   BufferedWriter bWriter=new BufferedWriter(new FileWriter(txt_file));//写入违章日期
					   bWriter.write(violation_time);
					   bWriter.close();
					   //下面是发送真正的图片
					   client=serverSocket.accept();
					   //获取工程路径
					   String project_path=new File("").getAbsolutePath();
					   File file=new File(project_path+"/images/"+file_name);
					   file.createNewFile();
					   RandomAccessFile raf=new RandomAccessFile(file,"rw"); 
					   //创建网络接受流接受服务器文件数据

					   InputStream netIn=client.getInputStream();

					   InputStream in=new DataInputStream(new BufferedInputStream(netIn));
					   //创建缓冲区缓冲网络数据

					   byte[] buf=new byte[2048];

					   int num=in.read(buf);
					   while(num!=(-1)){//是否读完所有数据
					         raf.write(buf,0,num);//将数据写往文件

					         raf.skipBytes(num);//顺序写文件字节

					         num=in.read(buf);//继续从网络中读取文件s
					      }
					      in.close();
					      raf.close(); 
					      model.addRow(new String[]{file_name,violation_time});
				   }
			} catch (IOException e1) {}
		}	
	}
	class Client_Thread implements Runnable
	{
		@Override
		public void run() {
			try {
				client_login= serverSocket_for_client.accept();
				DataInputStream dis=new DataInputStream(client_login.getInputStream());
				String client_name=dis.readUTF();
				dis.close();
				client_login.close();
				service_status_label.setText(service_status_label.getText()+"    "+client_name+" has login in!");
			} catch (IOException e) {e.printStackTrace();}
		}	
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//首先获取图片决对路径
		String project_path=new File("").getAbsolutePath();
		String image_path=project_path+"/images/"+model.getValueAt(table.getSelectedRow(),0).toString();
		try {
			this.imageEntirePanel.image=ImageIO.read(new File(image_path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void Check_Temp()
	{
		try {
        	String image_path=new File("").getAbsolutePath()+"\\temp.jpg";
            File file=new File(image_path);
            if(!file.exists())
            	return;
			BufferedImage temp_image=ImageIO.read(file);
			this.image=temp_image;
			repaint();
//			this.image_part_panel.image=temp_image;
//			this.image_part_panel.flag=true;
//			this.image_part_panel.repaint();
		} 
        catch (IOException e) {}
	}
	class myTimer implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Check_Temp();
		}
		
	}
}
