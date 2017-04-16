

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.*;

public class SymbolTable {

   
    private static ArrayList<Symbol> al = new ArrayList<Symbol>();
   
 
   public static void main(String[] args) {
		String inputFile = "C://Users//Talha Amin//Desktop//output.txt";
		String line = " ";
		
		
    	Scanner scan = new Scanner(System.in);
        SymbolTable  st = new SymbolTable();
       Symbol s = new Symbol();
       
    System.out.println("Enter Type: ");
    String type= scan.nextLine();
    
    System.out.println("Enter value: ");
    String value= scan.nextLine();
    
    s.setType(type);
    s.setValue(value);
    
    al.add(s);
    int i=0;
    for(Symbol  s1:al)
    {    
    	System.out.println("Key: "+i+" value="+s1.getValue()+"type="+s1.getType()+"");
    	i++;
    
    }
    
       
    }
}


