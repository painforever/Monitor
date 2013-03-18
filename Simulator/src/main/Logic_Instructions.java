package main;
import org.omg.PortableInterceptor.AdapterNameHelper;

import Register.*;
public class Logic_Instructions {
    public static void TST(int all_instructions[][],String instruction)
    {
    	String reg1=instruction.substring(6,8);
    	String reg2=instruction.substring(8,10);
    	boolean result=Test_the_Equality_of_Register_and_Register(reg1, reg2);
    	if(result)
    		CC.value[3]='1';
    	else
    		CC.value[3]='0';
    }
    public static void AND(int all_instructions[][],String instruction)
    {
    	String reg1=instruction.substring(6,8);
    	String reg2=instruction.substring(8,10);
    	Logical_And_of_Register_and_Register(reg1, reg2);
    }
    public static void OR(int all_instructions[][],String instruction)
    {
    	String reg1=instruction.substring(6,8);
    	String reg2=instruction.substring(8,10);
    	Logical_Or_of_Register_and_Register(reg1, reg2);
    }
    public  static void NOT(int all_instructions[][],String instruction)
    {
    	String reg=instruction.substring(6,8);
    	Logical_Not_of_Register_To_Register(reg);
    }
    public static boolean Test_the_Equality_of_Register_and_Register(String register_number1,String register_number2)
    {
    	int reg1=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number1));
    	int reg2=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number2));
//    	System.out.println(register_number1+","+register_number2);
//    	System.out.println("result:"+reg1+","+reg2);
    	if(reg1==reg2)
    		return true;
    	else
    	    return false;
    }
    public static void Logical_And_of_Register_and_Register(String register_number1,String register_number2)
    {
    	int reg1=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number1));
    	int reg2=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number2));
    	int result=reg1&reg2;
    	Instructions.Assign_Register(register_number1, Instructions.Change_Decimal_To_Binary(result+""));
    }
    public static void Logical_Or_of_Register_and_Register(String register_number1,String register_number2)
    {
    	int reg1=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number1));
    	int reg2=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number2));
    	int result=reg1|reg2;
    	Instructions.Assign_Register(register_number1, Instructions.Change_Decimal_To_Binary(result+""));
    }
    public static void Logical_Not_of_Register_To_Register(String register_number)
    {
    	String content=Instructions.Get_Content_According_To_Register(register_number);
    	String result="";
    	char char_array[]=content.toCharArray();
    	for (int i = 0; i < 16; i++) {
			if(char_array[i]=='0')
				char_array[i]='1';
			else
				char_array[i]='0';
			result+=char_array[i];
		}
    	Instructions.Assign_Register(register_number, result);
    }
}
