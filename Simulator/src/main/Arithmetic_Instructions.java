package main;

import java.math.BigInteger;
import Register.*;

public class Arithmetic_Instructions {
	public static void ADD(int all_instructions[][],String instruction)
	{
		Add_Memory_To_Register(instruction);
	}
	public static void SUB(int all_instructions[][],String instruction)
	{
		Subtract_Memory_From_Register(instruction);
	}
	public static void MUL(int all_instructions[][],String instruction)
	{
		String rx=instruction.substring(6,8);//rx的二进制号
		String ry=instruction.substring(8,10);//ry的二进制号
		Multiply_Register_by_Register(rx,ry);
	}
	public static void DIV(int all_instructions[][],String instruction)
	{
		String rx=instruction.substring(6,8);//rx的二进制号
		String ry=instruction.substring(8,10);//ry的二进制号
		Divide_Register_by_Register(rx,ry);
	}
	
	public static void Subtract_Memory_From_Register(String instruction)
	{
		String memory_address=instruction.substring(10);
    	String register_number=instruction.substring(8,10);
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	int register_value=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number));//得到了register里的值
    	int content_of_address=Instructions.Get_Content_By_Address(Analyze_Instructions.Change_Binary_To_Decimal(memory_address));
    	String param=(register_value-content_of_address)+"";//如果减得结果小于0就取0,否则取结果
    	String final_binary=Instructions.Change_Decimal_To_Binary(param);
    	Instructions.Assign_Register(register_number, final_binary);
	}
    public static void Add_Memory_To_Register(String instruction)//assist the ADD method
    {
    	String memory_address=instruction.substring(10);
    	String register_number=instruction.substring(8,10);
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	int register_value=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number));//得到了register里的值
    	int content_of_address=Instructions.Get_Content_By_Address(Analyze_Instructions.Change_Binary_To_Decimal(memory_address));
    	String final_binary=Instructions.Change_Decimal_To_Binary((content_of_address+register_value)+"");
    	Instructions.Assign_Register(register_number, final_binary);
    }
    public static void Multiply_Register_by_Register(String rx,String ry)//this is to assist the MUL
    {
    	if(!rx.equals("00")||!rx.equals("10"))
		{System.out.println("register rx must use R0 or R2");return;}
		if(!ry.equals("00")||!ry.equals("10"))
		{System.out.println("register ry must use R0 or R2");return;}
		//先获取rx,ry的值
		int rx_value=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(rx));//decimal
		int ry_value=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(ry));//decimal
		int result_decimal=rx_value*ry_value;
		String result_binary=Change_to_32_Bit(result_decimal);
		String temp_binary="";
		if(rx.equals("00")&&ry.equals("10"))
		{
			for (int i = 0; i <= 15; i++) 
			{temp_binary+=result_binary.charAt(i);R0.value=temp_binary;}
			for (int i = 16; i <32 ; i++)
			{temp_binary="";temp_binary+=result_binary.charAt(i);R1.value=temp_binary;}
		}
		else if(rx.equals("10")&&ry.equals("00"))
		{
			for (int i = 0; i <= 15; i++) 
			{temp_binary+=result_binary.charAt(i);R2.value=temp_binary;}
			for (int i = 16; i <32; i++)
			{temp_binary="";temp_binary+=result_binary.charAt(i);R3.value=temp_binary;}
		}
    }
    public static void Divide_Register_by_Register(String rx,String ry)
    {
    	if(!rx.equals("00")||!rx.equals("10"))
		{System.out.println("register rx must use R0 or R2");return;}
		if(!ry.equals("00")||!ry.equals("10"))
		{System.out.println("register ry must use R0 or R2");return;}
		//先获取rx,ry的值
		int rx_value=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(rx));//decimal
		int ry_value=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(ry));//decimal
		int quotient_decimal=rx_value/ry_value;
		int remainder_decimal=rx_value%ry_value;
		String result_binary=Instructions.Change_Decimal_To_Binary(quotient_decimal+"")+Instructions.Change_Decimal_To_Binary(remainder_decimal+"");
		String temp_binary="";
		if(rx.equals("00")&&ry.equals("10"))
		{
			for (int i = 0; i <= 15; i++) 
			{temp_binary+=result_binary.charAt(i);R0.value=temp_binary;}
			for (int i = 16; i <32 ; i++)
			{temp_binary="";temp_binary+=result_binary.charAt(i);R1.value=temp_binary;}
		}
		else if(rx.equals("10")&&ry.equals("00"))
		{
			for (int i = 0; i <= 15; i++) 
			{temp_binary+=result_binary.charAt(i);R2.value=temp_binary;}
			for (int i = 16; i <32; i++)
			{temp_binary="";temp_binary+=result_binary.charAt(i);R3.value=temp_binary;}
		}
    }
    public static String Change_to_32_Bit(int input)//assist the MUL that return 32-bit 
    {
    	BigInteger src = new BigInteger(input+"");//转换为BigInteger类型
    	String original_binary=src.toString(2);//转换为2进制并输出结果
    	String sign=original_binary.substring(0,1);
    	String after_sign="";
    	if(sign.equals("-"))//if result less than 0
    	{
    		after_sign=original_binary.substring(1);
    		if(after_sign.length()<31)
    		{
    			int offset=31-after_sign.length();
    			for (int i = 0; i < offset; i++) 
					after_sign="0"+after_sign;
    		}
    		after_sign="1"+after_sign;
    	}
    	else
    	{
    		after_sign=original_binary.substring(0);
    		if(after_sign.length()<31)
    		{
    			int offset=31-after_sign.length();
    			for (int i = 0; i < offset; i++) 
					after_sign="0"+after_sign;
    		}
    		after_sign="0"+after_sign;
    	}
    	return after_sign;
    }
    public static void Assign_32bit_To_Register(String high_register_number,String low_register_number,String value)
    {
    	if(high_register_number.equals("00"))
    	{
    		//int result=Analyze_Instructions.Change_Binary_To_Decimal(binary_data)
    	}
    }
}
