package main;

import org.omg.PortableInterceptor.AdapterNameHelper;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import Register.CC;
import Register.PC;
import Register.R0;
import Register.R3;

public class Transfer_Instructions {
    public static void JZ(int all_instructions[][],String instruction)
    {
    	String register_number=instruction.substring(8,10);
    	String memory_address=instruction.substring(10);
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	String content=Instructions.Get_Content_According_To_Register(register_number);
    	int result=Analyze_Instructions.Change_Binary_To_Decimal(content);
    	if(result==0)
    		PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);
    }
    public static void JNE(int all_instructions[][],String instruction)
    {
    	String register_number=instruction.substring(8,10);
    	String memory_address=instruction.substring(10);
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	String content=Instructions.Get_Content_According_To_Register(register_number);
    	int result=Analyze_Instructions.Change_Binary_To_Decimal(content);
    	if(result!=0)
    		PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);
    }
    public static void JCC(int all_instructions[][],String instruction)
    {
    	String cc=instruction.substring(8,10);//condition code of cc that you want to test
    	String memory_address=instruction.substring(10);//binary
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	if(cc.equals("00"))
    	{
    		if(CC.value[0]=='1')
    		{PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);PC.pc--;}
    	}
    	if(cc.equals("01"))
    	{
    		if(CC.value[1]=='1')
    		{PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);PC.pc--;}
    	}
    	if(cc.equals("10"))
    	{
    		if(CC.value[2]=='1')
    		{PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);PC.pc--;}
    	}
    	if(cc.equals("11"))
    	{
    		if(CC.value[3]=='1')
    		{PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);PC.pc--;}
    	}
    }
    public static void JMP(int all_instructions[][],String instruction)
    {
    	String memory_address=instruction.substring(10);
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	int result=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);
    	PC.pc=result;PC.pc--;
    }
    public static void JSR(int all_instructions[][],String instruction)
    {
    	String memory_address=instruction.substring(10);
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	R3.value=Instructions.Change_Decimal_To_Binary((PC.pc+1)+"");
    	PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(memory_address);
    }
    public static void RFS(int all_instructions[][],String instruction)
    {
    	//get the immed, totally 10 bits
    	String immed=instruction.substring(6);
    	R0.value="000000"+immed;
    	PC.pc=Analyze_Instructions.Change_Binary_To_Decimal(R3.value);
    }
    public static void SOB(int all_instructions[][],String instruction)
    {
    	String register_number=instruction.substring(8,10);
    	String memory_address=instruction.substring(10);
    	memory_address=Instructions.Handle_Index_Address(instruction, memory_address);
    	memory_address=Instructions.Handle_Indirect_Address(instruction, memory_address);
    	int register_content_decimal=Analyze_Instructions.Change_Binary_To_Decimal(Instructions.Get_Content_According_To_Register(register_number));
    	register_content_decimal--;
    	Instructions.Assign_Register(register_number, Instructions.Change_Decimal_To_Binary((register_content_decimal)+""));
    	if(register_content_decimal>0)
    	{PC.pc=Analyze_Instructions.Change_Binary_To_Decimal((memory_address));PC.pc--;}
    	else
    	{} 	
    }
}
