package BLL_Server;
import java.io.*;
import java.net.*; 

import View_Server.Image_Analyze;
public class FileServer implements Runnable {
	Socket client;
	ServerSocket serverSocket;
   public FileServer()
   {
	   try {
		this.serverSocket=new ServerSocket(3108);	
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
   public void Check_And_Delete_File()
   {
	   //获取工程文件夹
	   String path=new File("").getAbsolutePath();
		  File file=new File(path+"/images");
	      File[] files=file.listFiles();
	      for(File fi:files)
	      {
	    	  int pos=fi.toString().indexOf(".");
	    	  if(pos!=-1)
	    	  {
	    		  if(fi.toString().substring(pos+1).equals("jpg")||fi.toString().substring(pos+1).equals("bmp")||fi.toString().substring(pos+1).equals("png")) 
	        	  {
	    			 //System.out.println(fi);
	    			  File rubbish=new File(fi.toString());
	    			  rubbish.delete();
	    		  } 
	    	  } 	  
	      }
   }
   public void Receive_File()
   {
	   try {
		   while(true)
		   {
			   this.client=this.serverSocket.accept();
			  // Check_And_Delete_File();//先删除现有图片在发送新图片
			   DataInputStream from_client=new DataInputStream(this.client.getInputStream());
			   String file_name=from_client.readUTF();
			   from_client.close();
			   this.client.close();
			   //开始发送违章时间,这里采用新建一个与图片同名的txt文件并存入时间的办法
			   this.client=this.serverSocket.accept();
			   DataInputStream dis=new DataInputStream(this.client.getInputStream());
			   String violation_time=dis.readUTF();
			   dis.close();
			   this.client.close();
			   String path=new File("").getAbsolutePath();//开始创建txt文件
			   int dot_point=file_name.indexOf(".");		   
			   File txt_file=new File(path+"/images/"+file_name.substring(0, dot_point)+".txt");
			   txt_file.createNewFile();
			   BufferedWriter bWriter=new BufferedWriter(new FileWriter(txt_file));//写入违章日期
			   bWriter.write(violation_time);
			   bWriter.close();
			   //下面是发送真正的图片
			   this.client=this.serverSocket.accept();
			   //获取工程路径
			   String project_path=new File("").getAbsolutePath();
			   File file=new File(project_path+"/images/"+file_name);
			   file.createNewFile();
			   RandomAccessFile raf=new RandomAccessFile(file,"rw"); 
			   //创建网络接受流接受服务器文件数据

			   InputStream netIn=this.client.getInputStream();

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
		   }
	} catch (IOException e) {}
   }
@Override
public void run() {
	Receive_File();
}
}

