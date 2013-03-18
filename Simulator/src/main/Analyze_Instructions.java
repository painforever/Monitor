package main;

import java.math.BigInteger;
import Register.*;
public class Analyze_Instructions {
	public static int instructions[][]=new int[4096][16];
	public static int memory[][]=new int[4096][16];
	//decode the opcode in the multi-rows of 16-bits instructions 
    public static String Decode_Opcode(int all_instructions[][],int start_row)//first param is all the rows of 16-bit instructions
    {                                                                         //second param is the start line to be executed(instruction)
    	PC.pc=start_row;
    	while(PC.pc<=all_instructions.length-1)
    	{
    		
    		String opcode="";//initiate the opcode
    		for (int i = 0; i < 6; i++) {
    			opcode+=all_instructions[PC.pc][i];//extract the opcode
			}
    		String instruction="";
    		for (int i = 0; i < 16; i++) 
				instruction+=all_instructions[PC.pc][i];//get the entire row of one single instruction
    		//System.out.println("指令:"+instruction+"PC:"+PC.pc);
    		if(opcode.equals("000001"))
    			Instructions.LDR(all_instructions,instruction);//001 LDR
    		else if(opcode.equals("000010"))
    			Instructions.STR(all_instructions,instruction);//002 STR
    		else if(opcode.equals("000110"))
    			Instructions.AIR(all_instructions, instruction);//006 AIR
    		else if(opcode.equals("000111"))
    			Instructions.SIR(all_instructions, instruction);//007 SIR
    		else if(opcode.equals("000011"))
    			Instructions.LDA(all_instructions, instruction);//003 LDA
    		else if(opcode.equals("101001")) 
    			Instructions.LDX(all_instructions, instruction);//041  LDX
    		else if(opcode.equals("101010"))
    			Instructions.STX(all_instructions, instruction);//042  STX
    		else if(opcode.equals("000100"))
    			Arithmetic_Instructions.ADD(all_instructions, instruction);//004 ADD
    		else if(opcode.equals("000101"))
    			Arithmetic_Instructions.SUB(all_instructions, instruction);// 005 SUB
    		else if(opcode.equals("010110"))
    			Logic_Instructions.TST(all_instructions, instruction);// 022 TST
    		else if(opcode.equals("010100"))
    			Arithmetic_Instructions.MUL(all_instructions, instruction);// 020  MUL
    		else if(opcode.equals("010101"))
    			Arithmetic_Instructions.DIV(all_instructions, instruction);//021 DIV
    		else if(opcode.equals("010111"))
    			Logic_Instructions.AND(all_instructions, instruction);//023 AND 
    		else if(opcode.equals("011000"))
    			Logic_Instructions.OR(all_instructions, instruction);//024 OR
    		else if(opcode.equals("011001"))
    			Logic_Instructions.NOT(all_instructions, instruction);//025 NOT
    		else if(opcode.equals("011111"))
    			Shift_Rotate_Instructions.SRC(all_instructions, instruction);//031 SRC
    		else if(opcode.equals("100000"))
    			Shift_Rotate_Instructions.RRC(all_instructions, instruction);//032 RRC
    		else if(opcode.equals("001101"))
    			Transfer_Instructions.JMP(all_instructions, instruction);//013  JMP
    		else if(opcode.equals("001010"))
    			Transfer_Instructions.JZ(all_instructions, instruction);// 010  JZ
    		else if(opcode.equals("001011"))
    			Transfer_Instructions.JNE(all_instructions, instruction);// 011 JNE
    		else if(opcode.equals("001100"))
    			Transfer_Instructions.JCC(all_instructions, instruction);//012  JCC
    		else if(opcode.equals("001110"))
    			Transfer_Instructions.JSR(all_instructions, instruction);//014  JSR
    		else if(opcode.equals("001111"))
    			Transfer_Instructions.RFS(all_instructions, instruction);//015 RFS
    		else if(opcode.equals("010000"))
    			Transfer_Instructions.SOB(all_instructions, instruction);//016 SOB
    		else if(opcode.equals("111101"))
    			IO_Instructions.IN(all_instructions, instruction);  //061  IN
    		else if(opcode.equals("111110"))
    			IO_Instructions.OUT(all_instructions, instruction);  //062 OUT
    		PC.pc++;//increment the row index so as to execute the next instruction
    	//	System.out.println("R3:"+R3.value);
    	//	System.out.println(PC.pc);
    	}
    	
//    	for (int i = 0; i < memory.length; i++) {
//    		for (int j = 0; j < memory[0].length; j++) {
//				System.out.print(memory[i][j]+",");
//			}
//			System.out.println();
//		}
    	return "";
    }
    public static int Change_Binary_To_Decimal(String binary_data)
    {
    	BigInteger src = new BigInteger(binary_data, 2);//change to BigInteger
    	if(binary_data.length()==6)//如果是6位,则不存在正负问题
 		   return Integer.valueOf(src.toString());//change to decimal
    	else if(binary_data.length()==16)//如果是16位,则需要处里正负问题 
    	{
    		String bit_16=binary_data.toString();
    		String sign=bit_16.substring(0,1);
    		String after_sign=bit_16.substring(1);
    		BigInteger negtive=new BigInteger(after_sign,2);
    		if(sign.equals("0"))//正数
    			return Integer.valueOf(src.toString());//change to decimal
    		else
    			return 0-Integer.valueOf(negtive.toString());//change to negtive decimal
    	}
    	else
    	{
    		return Integer.valueOf(src.toString());//change to decimal
    	}
    }
    public static String Change_Decimal_To_Binary(String decimal_data)
    {
    	BigInteger src = new BigInteger(decimal_data);//转换为BigInteger类型
    	return src.toString(2);//转换为2进制并输出结果
    }
    public static void Clean_RAM()
    {
    	for (int i = 0; i < memory.length; i++) {
    		for (int j = 0; j < memory[0].length; j++) {
				memory[i][j]=0;
			}
		}
    }
}
