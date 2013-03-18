package main;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    public static List<Memory_Unit> memory=new ArrayList<Memory_Unit>();
    public Memory()
    {
    	for (int i = 0; i < memory.size(); i++) {
			for (int j = 0; j < memory.get(i).unit.length; j++) 
				memory.get(i).unit[j]=0;
		}
    }
}
