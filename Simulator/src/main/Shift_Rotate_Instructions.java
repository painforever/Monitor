package main;

import com.sun.org.apache.regexp.internal.recompile;

public class Shift_Rotate_Instructions {
    public static void SRC(int all_instructions[][],String instruction)
    {
    	String LR=instruction.substring(6,7);//LR=1,left, LR=0,right
    	String AL=instruction.substring(9,10);//AL=1,logically, AL=2 arithmically
    	String register_number=instruction.substring(7,9);
    	String count=instruction.substring(12);//count is binay, need to be transfered
    	Shift_Register_by_Count(LR, AL, register_number, count);
    }
    public static void RRC(int all_instructions[][],String instruction)
    {
    	String LR=instruction.substring(6,7);//LR=1,left, LR=0,right
    	String AL=instruction.substring(9,10);//AL=1,logically, AL=2 arithmically
    	String register_number=instruction.substring(7,9);
    	String count=instruction.substring(12);//count is binay, need to be transfered
    	Rotate_Register_by_Count(LR, AL, register_number, count);
    }
    public static void Shift_Register_by_Count(String LR,String AL,String register_number,String count)
    {
    	String content_of_register=Instructions.Get_Content_According_To_Register(register_number);//get the content of register
    	int count_decimal=Analyze_Instructions.Change_Binary_To_Decimal(count);//change count to decimal
    	String result="";
    	//first judge ari shift or logic shift
    	if(AL.equals("1")&&LR.equals("1"))//logically,left
    	{
    		result=Logical_Shift_Left(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    	else if(AL.equals("1")&&LR.equals("0"))//logically,right
    	{
    		result=Logical_Shift_Right(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    	else if(AL.equals("0")&&LR.equals("1"))//ari left
    	{
    		result=Ari_Shift_Left(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    	else if(AL.equals('0')&&LR.equals("0"))//ari right
    	{
    		result=Ari_Shift_Right(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    }
    public static void Rotate_Register_by_Count(String LR,String AL,String register_number,String count)
    {
    	String content_of_register=Instructions.Get_Content_According_To_Register(register_number);//get the content of register
    	int count_decimal=Analyze_Instructions.Change_Binary_To_Decimal(count);//change count to decimal
    	String result="";
    	//first judge ari shift or logic shift
    	if(AL.equals("1")&&LR.equals("1"))//logically,left
    	{
    		result=Logical_Rotate_Left(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    	else if(AL.equals("1")&&LR.equals("0"))//logically,right
    	{
    		result=Logical_Rotate_Right(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    	else if(AL.equals("0")&&LR.equals("1"))//ari left
    	{
    		result=Ari_Rotate_Left(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    	else if(AL.equals('0')&&LR.equals("0"))//ari right
    	{
    		result=Ari_Rotate_Right(content_of_register, count_decimal);
    		Instructions.Assign_Register(register_number, result);
    	}
    }
    public static String Ari_Shift_Left(String content_of_register,int count)//binary
    {
    	String sign=content_of_register.substring(0,1);
    	String after_sign=content_of_register.substring(1);
    	String after_shift=after_sign.substring(count);
    	//移位完后补0,移了几位补几个0
    	for (int i = 0; i < count; i++) {
			after_shift+="0";
		}
    	String result=sign+after_shift;
    	return result;
    }
    public static String Ari_Shift_Right(String content_of_register,int count)
    {
    	String sign=content_of_register.substring(0,1);
    	String after_sign=content_of_register.substring(1);
    	String after_shift=after_sign.substring(0,after_sign.length()-count);
    	//移位完后补0,移了几位补几个0
    	for (int i = 0; i<count; i++)
    		after_shift="0"+after_shift;
    	String result=sign+after_shift;
    	return result;
    }
    public static String Logical_Shift_Left(String content_of_register,int count)
    {
    	String after_shift=content_of_register.substring(count);
    	for (int i = 0; i < count; i++)
    		after_shift+="0";
    	return after_shift;
    }
    public static String Logical_Shift_Right(String content_of_register,int count)
    {
    	String after_shift=content_of_register.substring(0,content_of_register.length()-count);
    	for (int i = 0; i < count; i++)
    		after_shift="0"+after_shift;
    	return after_shift;
    }
    //*********************************************************RRC***************************************************************
    public static String Ari_Rotate_Left(String content_of_register,int count)
    {
    	String sign=content_of_register.substring(0,1);
    	String after_sign=content_of_register.substring(1);
    	String former=after_sign.substring(0,count);
    	String latter=after_sign.substring(count);
    	return sign+latter+former;
    }
    public static String Ari_Rotate_Right(String content_of_register,int count)
    {
    	String sign=content_of_register.substring(0,1);
    	String after_sign=content_of_register.substring(1);
    	String former=after_sign.substring(0,after_sign.length()-count);
    	String latter=after_sign.substring(after_sign.length()-count);
    	return sign+latter+former;
    }
    public static String Logical_Rotate_Left(String content_of_register,int count)
    {
    	String former=content_of_register.substring(0,count);
    	String latter=content_of_register.substring(count);
    	return latter+former;
    }
    public static String Logical_Rotate_Right(String content_of_register,int count)
    {
    	String former=content_of_register.substring(0,content_of_register.length()-count);
    	String latter=content_of_register.substring(content_of_register.length()-count);
    	return latter+former;
    }
}
