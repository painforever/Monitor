package test;

import java.net.DatagramSocket;
import java.net.SocketException;

public class TableTest
{
	public static void main(String[] args) {
		try {
			DatagramSocket datagramSocket=new DatagramSocket(1999);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}