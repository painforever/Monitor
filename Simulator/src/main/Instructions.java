package main;
import java.math.BigInteger;

import org.omg.PortableInterceptor.AdapterNameHelper;

import Register.*;
public class Instructions {
    public static void LDR(int all_instructions[][],String instruction)
    {
    	String register_number=instruction.substring(8, 10);//binary
    	String memory_address=instruction.substring(10);//binary
    	memory_address=Handle_Index_Address(instruction, memory_address);
    	memory_address=Handle_Indirect_Address(instruction, memory_address);
    	Load_Register_From_Memory(register_number,memory_address);
    } 
    public static void STR(int all_instructions[][],String instruction)
    {
    	String register_number=instruction.substring(8, 10);//binary
    	String memory_address=instruction.substring(10);//binary
    	memory_address=Handle_Index_Address(instruction, memory_address);
    	memory_address=Handle_Indirect_Address(instruction, memory_address);
    	Store_Register_To_Memory(register_number, memory_address);
    }
    public static void JMP(int all_instructions[][],int offset)
    {
    	
    }
    public static void SIR(int all_instructions[][],String instruction)
    {
    	String immidiate_data=instruction.substring(10);
    	String register_number=instruction.substring(8,10);
    	Sub_Immediate(register_number, immidiate_data);
    }
    public static void AIR(int all_instructions[][],String instruction)
    {
    	String immidiate_data=instruction.substring(10);
    	String register_number=instruction.substring(8,10);
    	Add_Immediate(register_number, immidiate_data);
    }
    public static void LDA(int all_instructions[][],String instruction)
    {
    	Load_Register_with_Address(instruction);
    }
    public static void LDX(int all_instructions[][],String instruction)
    {
    	Load_Index_Register_From_Memory(instruction);
    }
    public static void STX(int all_instructions[][],String instruction)
    {
    	Store_Index_Register_To_Memory(instruction);
    }
    public static void Add_Immediate(String register_number, String value)//this method is to assist the AIR instruction
    {
    	int immidiate_data=Analyze_Instructions.Change_Binary_To_Decimal(value);
    	if(register_number.equals("00"))
    	{		
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R0.value);
    		int result=immidiate_data+register_value;
    		R0.value=Change_Decimal_To_Binary(result+"");
    	}
    	else if(register_number.equals("01"))
    	{
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R1.value);
    		int result=immidiate_data+register_value;
    		R1.value=Change_Decimal_To_Binary(result+"");
    	}
    	else if(register_number.equals("10"))
    	{
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R2.value);
    		int result=immidiate_data+register_value;
    		R2.value=Change_Decimal_To_Binary(result+"");
    	}
    	else if(register_number.equals("11"))
    	{
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R3.value);
    		int result=immidiate_data+register_value;
    		R3.value=Change_Decimal_To_Binary(result+"");
    	}
    }
    
    public static void Sub_Immediate(String register_number, String value)//this method is to assist the SIR instruction
    {
    	int immidiate_data=Analyze_Instructions.Change_Binary_To_Decimal(value);
    	if(register_number.equals("00"))
    	{		
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R0.value);
    		int result=immidiate_data-register_value;
    		R0.value=Change_Decimal_To_Binary(result+"");
    	}
    	else if(register_number.equals("01"))
    	{
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R1.value);
    		int result=immidiate_data-register_value;
    		R1.value=Change_Decimal_To_Binary(result+"");
    	}
    	else if(register_number.equals("10"))
    	{
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R2.value);
    		int result=immidiate_data-register_value;
    		R2.value=Change_Decimal_To_Binary(result+"");
    	}
    	else if(register_number.equals("11"))
    	{
    		int register_value=Analyze_Instructions.Change_Binary_To_Decimal(R3.value);
    		int result=immidiate_data-register_value;
    		R3.value=Change_Decimal_To_Binary(result+"");
    	}
    }
    
    public static void Store_Register_To_Memory(String register_number,String memory_address)//this is to assist STR and to be reused for future
    {
    	//get the decimal address from the memory_address to assign the corresponding index for 2-dimentional array
    	int index=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);
    	String address="";
    	if(register_number.equals("00"))
    	{
    		address=R0.value;
    		char address_char[]=address.toCharArray();
    		for (int i = 0; i <16; i++) 
    			Analyze_Instructions.memory[index][i]=Integer.parseInt(address_char[i]+"");//给16位内存赋值	   
    	}
     	else if(register_number.equals("01"))
     	{
     		address=R1.value;
    		char address_char[]=address.toCharArray();
    		for (int i = 0; i <16; i++) 
    			Analyze_Instructions.memory[index][i]=Integer.parseInt(address_char[i]+"");//给16位内存赋值	 
     	}
     	else if(register_number.equals("10"))
     	{
     		address=R2.value;
    		char address_char[]=address.toCharArray();
    		for (int i = 0; i <16; i++) 
    			Analyze_Instructions.memory[index][i]=Integer.parseInt(address_char[i]+"");//给16位内存赋值	 
     	}
     	else if(register_number.equals("11"))
     	{
     		address=R3.value;
    		char address_char[]=address.toCharArray();
    		for (int i = 0; i <16; i++) 
    			Analyze_Instructions.memory[index][i]=Integer.parseInt(address_char[i]+"");//给16位内存赋值	 
     	}
    }
    
    public static void Load_Register_From_Memory(String register_number,String memory_address)//assist the LDR, all params are binary
    {
    	int index=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);//index is decimal for the memory[][] 
    	String temp_binary="";//to store the binary in memory[index][]
    	for (int i = 0; i < 16; i++) 
			temp_binary+=Analyze_Instructions.memory[index][i];
    	//int result=Analyze_Instructions.Change_Binary_To_Decimal(temp_binary);
    	Assign_Register(register_number, temp_binary);
    }
  //this is to assist LDA, all params are binary, second param is the whole 16-bit instruction
    public static void Load_Register_with_Address(String instruction)
    {
    	//String I=instruction.substring(6,7);
    	String register_number=instruction.substring(8,10);
    	String memory_address=instruction.substring(10);
    	memory_address=Handle_Index_Address(instruction, memory_address);
    	memory_address=Handle_Indirect_Address(instruction, memory_address);
    	Assign_Register(register_number, memory_address);//memory_address是最终结果
    }
    
    public static void Load_Index_Register_From_Memory(String instruction)//this is to assist LDX
    {
    	String memory_address=instruction.substring(10);
    	memory_address=Handle_Index_Address(instruction, memory_address);
    	memory_address=Handle_Indirect_Address(instruction, memory_address);
    	int index=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);//index is the memory_address index
    	int content=Get_Content_By_Address(index);
    	X0.value=Change_Decimal_To_Binary(content+"");
    }
    
    public static void Store_Index_Register_To_Memory(String instruction)
    {
    	String memory_address=instruction.substring(10);
    	memory_address=Handle_Index_Address(instruction, memory_address);
    	memory_address=Handle_Indirect_Address(instruction, memory_address);
        int index=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);//index is the memory_address index
        String binary_value_for_X0=Change_Decimal_To_Binary(X0.value+"");// must be 6 length
        char binary_char[]=binary_value_for_X0.toCharArray();
        for (int i = 0; i < 6; i++) 
			Analyze_Instructions.memory[index][10+i]=Integer.parseInt(binary_char[i]+"");
    }
    
    public static String Change_Decimal_To_Binary(String decimal_data)//always return 16 bit binary
    {
    	BigInteger src = new BigInteger(decimal_data);//转换为BigInteger类型
    	String result=src.toString(2);
    	String sign=result.substring(0,1);//二进制的符号
    	String after_sign="";
    	if(sign.equals("-"))
    	{
    		after_sign=result.substring(1);
    		if(after_sign.length()<15)
        	{
        		int offset=15-after_sign.length();
        		for (int i = 0; i < offset; i++)
        			after_sign="0"+after_sign;
        	}
    		after_sign="1"+after_sign;
    	}
    	else
    	{
    		after_sign=result.substring(0);
    		if(after_sign.length()<15)
        	{
        		int offset=15-after_sign.length();
        		for (int i = 0; i < offset; i++)
        			after_sign="0"+after_sign;
        	}
    		after_sign="0"+after_sign;
    	}
    	return after_sign;//转换为2进制并输出结果
    }
    //这个方法是专门用来给R0-R3赋值的,为了减轻代码量
    public static void Assign_Register(String register_number,String value)//all params are binary
    {
    	if(value.length()==6)
    		value="0000000000"+value;
    	if(register_number.equals("00"))
    		R0.value=value;
     	else if(register_number.equals("01"))
     	    R1.value=value;
     	else if(register_number.equals("10"))
     	    R2.value=value;
     	else if(register_number.equals("11"))
     	    R3.value=value;
    }
    //这个方法是专门用来处里直接和间接寻址的,方法是无论指令是什么,每次都调用这个函数,如果是间接,则反回一个新地址,如果直接则反回原来的
    //返回的都是二进制值
    public static String Handle_Indirect_Address(String instruction,String memory_address)//binary
    {
    	String I=instruction.substring(6,7);
    	String final_address="";
    	if(I.equals("0")){}//是否是间接寻址
    	else if(I.equals("1"))
    	{
    		for (int j = 0; j < 16; j++)
    			final_address+=Analyze_Instructions.memory[Analyze_Instructions.Change_Binary_To_Decimal(memory_address+"")][j];
    		memory_address=final_address;
    	}
    	if(memory_address.length()==6)
    		memory_address="0000000000"+memory_address;
    	return memory_address;
    }
    public static String Handle_Index_Address(String instruction, String memory_address)
    {
    	String IX=instruction.substring(7,8);
    	int after_indexed_address=0;
    	if(IX.equals("1"))//如果有变址,就处理一下
    	{
    		int X0_value_decimal=Analyze_Instructions.Change_Binary_To_Decimal(X0.value);
    		after_indexed_address=X0_value_decimal+Analyze_Instructions.Change_Binary_To_Decimal(memory_address);
    		memory_address=Change_Decimal_To_Binary(after_indexed_address+"");
    	}
    	if(memory_address.length()==6)
    		memory_address="0000000000"+memory_address;
    	return memory_address;
    }
    public static int Get_Content_By_Address(int index)//this is to get the content through index from memory, for example:memory[index][]
    {
    	String binary_string="";
    	for (int i = 0; i < 16;i++) 
    		binary_string+=Analyze_Instructions.memory[index][i];
    	int result=Analyze_Instructions.Change_Binary_To_Decimal(binary_string);
    	return result;
    }
    public static String Get_Content_According_To_Register(String register_number)//根据register number返回里面的值
    {
    	String value="";
    	if(register_number.equals("00"))
    		value=R0.value;
    	else if(register_number.equals("01"))
    		value=R1.value;
    	else if(register_number.equals("10"))
    		value=R2.value;
    	else if(register_number.equals("11"))
    		value=R3.value;
    	return value;
    }
}
