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
	   //��ȡ�����ļ���
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
			  // Check_And_Delete_File();//��ɾ������ͼƬ�ڷ�����ͼƬ
			   DataInputStream from_client=new DataInputStream(this.client.getInputStream());
			   String file_name=from_client.readUTF();
			   from_client.close();
			   this.client.close();
			   //��ʼ����Υ��ʱ��,��������½�һ����ͼƬͬ����txt�ļ�������ʱ��İ취
			   this.client=this.serverSocket.accept();
			   DataInputStream dis=new DataInputStream(this.client.getInputStream());
			   String violation_time=dis.readUTF();
			   dis.close();
			   this.client.close();
			   String path=new File("").getAbsolutePath();//��ʼ����txt�ļ�
			   int dot_point=file_name.indexOf(".");		   
			   File txt_file=new File(path+"/images/"+file_name.substring(0, dot_point)+".txt");
			   txt_file.createNewFile();
			   BufferedWriter bWriter=new BufferedWriter(new FileWriter(txt_file));//д��Υ������
			   bWriter.write(violation_time);
			   bWriter.close();
			   //�����Ƿ���������ͼƬ
			   this.client=this.serverSocket.accept();
			   //��ȡ����·��
			   String project_path=new File("").getAbsolutePath();
			   File file=new File(project_path+"/images/"+file_name);
			   file.createNewFile();
			   RandomAccessFile raf=new RandomAccessFile(file,"rw"); 
			   //����������������ܷ������ļ�����

			   InputStream netIn=this.client.getInputStream();

			   InputStream in=new DataInputStream(new BufferedInputStream(netIn));
			   //����������������������

			   byte[] buf=new byte[2048];

			   int num=in.read(buf);
			   while(num!=(-1)){//�Ƿ������������
			         raf.write(buf,0,num);//������д���ļ�

			         raf.skipBytes(num);//˳��д�ļ��ֽ�

			         num=in.read(buf);//�����������ж�ȡ�ļ�s
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

