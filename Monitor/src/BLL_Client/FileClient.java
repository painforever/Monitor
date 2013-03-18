package BLL_Client;
import java.io.*;
import java.net.*; 
import java.util.Date;
public class FileClient {
	public Socket socket;
	public String file_path;
	public String file_name;
	public String violation_time;
    public FileClient()
    {
    }
    public void Send_Name(String file_name)
    {
    	try {
			this.socket=new Socket(InetAddress.getLocalHost(),3108);
			DataOutputStream to_server=new DataOutputStream(this.socket.getOutputStream());
			to_server.writeUTF(file_name);
			to_server.close();
			this.socket.close();
			this.socket=null;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void Send_Violation_Time(String path)
    {
    	File file=new File(path);
    	Long last_modify=file.lastModified();
    	Date date=new Date(last_modify);
        try {
			this.socket=new Socket(InetAddress.getLocalHost(),3108);
			DataOutputStream to_server=new DataOutputStream(this.socket.getOutputStream());
			to_server.writeUTF(date.toLocaleString());
			to_server.close();
			this.socket.close();
			this.socket=null;
		} catch (Exception e) {}	
    }
    public void Send_File(String path)
    {
    	try {
			this.socket=new Socket(InetAddress.getLocalHost(),3108);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	File file=new File(path);
    	try {
			FileInputStream fis=new FileInputStream(file);
			OutputStream netOut=socket.getOutputStream();
			OutputStream doc=new DataOutputStream(new BufferedOutputStream(netOut));
			  //创建文件读取缓冲区

		      byte[] buf=new byte[2048];

		      int num=fis.read(buf);

		      while(num!=(-1)){//是否读完文件

		         doc.write(buf,0,num);//把文件数据写出网络缓冲区

		         doc.flush();//刷新缓冲区把数据写往客户端

		         num=fis.read(buf);//继续从文件中读取数据

		      }

		      fis.close();

		      doc.close();
		      this.socket.close();
		      this.socket=null;
		} catch (FileNotFoundException e) {} catch (IOException e) {e.printStackTrace();}
    	
    }
}
