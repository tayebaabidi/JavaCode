package addresslist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ContactListDemo {
	public static ArrayList<Contact> ab;
	static Contact a;
	static String s;
	static int v;
	static char r;
	static Scanner scan;
	public ContactListDemo(){
		
	}
	public static void main(String [] args) throws ClassNotFoundException, IOException{
		ab=new ArrayList<Contact> ();
		a=new Contact();
		scan=new Scanner(System.in);
		ContactList.read();
	    char i='e';
	    do{
	    	if(i=='r'){
	    		ContactList.read();
	        }
	    	else{
                System.out.println("\n Press a to add an entry or s for searching");
                r=scan.next().charAt(0);
    
                if (r== 'a'){
                	ContactList.insert();
    	        }
                else if (r== 's'){
    	         System.out.println("Press\n1:Search by name.\n2.Search by surename.\n3.Search by email address.\n");
    	         r=scan.next().charAt(0);
    	         System.out.println("please enter the term:");
    	         s=scan.next();
    	         ContactList.search(r, s);
               }
	    	}
           System.out.println("Press e to exite, r to read or any other value to continue:");
           i=scan.next().charAt(0);
	     }while(i!='e');
	     
	}
    
   }
    


