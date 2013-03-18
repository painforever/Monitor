package main;

public class Memory_Unit {
    public int unit[]=new int[16];
    public Memory_Unit(int data)
    {
    	for (int i = 0; i < unit.length; i++) 
			unit[i]=data;
    }
    public Memory_Unit(){}
    
}
