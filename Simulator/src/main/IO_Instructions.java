package main;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import Pop_Win.*;
import Register.PC;
import Register.R0;
public class IO_Instructions {
   public static String user_input="";
   public static void IN(int all_instructions[][],String instruction)
   {
	   
	   String device_id=instruction.substring(11);
	   String register_number=instruction.substring(7,9);
	   User_Input_Form saForm=new User_Input_Form();
	   //��ʱ��ȡ��user_input
	 
   	   boolean b=false;
   		//������Java��ͷ,�����β���ַ���
   	    Pattern pattern = Pattern.compile("[a-zA-Z]{1}");
   	   	Matcher matcher = pattern.matcher(user_input);
   	    b=matcher.matches();
   	   	if(b)
   	   	{
   	   	    String ascIIintValue =  Integer.toString(user_input.toCharArray()[0]);//��һ���ַ�ת��Ϊ������ascii��
   	   	    user_input=ascIIintValue;
   	   	}
	   String result_binary=Instructions.Change_Decimal_To_Binary(user_input+"");
	   Instructions.Assign_Register(register_number, result_binary);
	   System.out.println("user input:"+user_input);
   }
   public static void OUT(int all_instructions[][],String instruction)
   {
	   String device_id=instruction.substring(11);
	   String register_number=instruction.substring(7,9);
	   String binary_out=Instructions.Get_Content_According_To_Register(register_number);
	   int result=Analyze_Instructions.Change_Binary_To_Decimal(binary_out);
	   Simulator_View.result_Panel.jTextArea.setText("The colosest number is:"+result+"");
	   System.out.println("the result is:"+result);
	   PC.pc=99999;
   }
}
