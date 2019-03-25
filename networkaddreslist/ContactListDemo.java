package networkaddreslist;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

//client part

public class ContactListDemo {
	public static ArrayList<Contact> ab;
	static Contact a;
	static String s;
	static Scanner scan;
	public static void main(String [] args) throws UnknownHostException, IOException, ClassNotFoundException{
		ab=new ArrayList<Contact> ();
		a=new Contact();
		scan=new Scanner(System.in);
	
    System.out.println("Rules");
    
    	    insert();
    	    System.out.println("contact successfully insertetd");
    	    char r;
    	    System.out.println("please Enter r to read"); 
    	     r=scan.next().charAt(0);
    	     if (r== 'r'){
    		read();
    	     }
    		}
  
	
	
    public static void insert() throws ClassNotFoundException, IOException{
      System.out.println("name, surename and email are mandatory fields in case of whispace caracter or empty character you will return! Please enter to add contact");
      s=scan.nextLine();
      System.out.println("enter name:");
      s=scan.nextLine();
      if(s.equals("")||s.equals(" ")||s.equals("\n")||s.equals("	"))return;
      a.setName(s);
      System.out.println("enter surename:");
      s=scan.nextLine();
      if(s.equals("")||s.equals(" ")||s.equals("\n")||s.equals("	"))return;
      a.setSure(s);
      System.out.println("enter email:");
      s=scan.nextLine();
      if(s.equals("")||s.equals(" ")||s.equals("\n")||s.equals("	"))return;
      a.setEmail(s);
      System.out.println("enter postal:");
      s=scan.nextLine();
      a.setPost(s);
      System.out.println("enter phone number:");
      s=scan.nextLine();
      a.setphoneNumber(s);
      ab.add(a);
      System.out.println("For inserting more contacts press c and for storing contact(s) press s:");
      char t=scan.next().charAt(0);
      if(t=='c'){
    	  insert();
      }
      else if(t=='s'){ 
    	  Socket socket1 = new Socket("localhost",4444);
    		ObjectOutputStream sendObj = new ObjectOutputStream(socket1.getOutputStream());
    		sendObj.writeObject(ab);
    		socket1.close();
      }
      }
    public static void read() throws UnknownHostException, IOException, ClassNotFoundException{
    	System.out.println("contacts List:");
		Socket socket2 = new Socket("localhost",5555);
		ObjectInputStream serverinpu = new ObjectInputStream(socket2.getInputStream());
		
		@SuppressWarnings("unchecked")
		ArrayList<Contact> incomingObject=(ArrayList<Contact>)serverinpu.readObject();
		
		if(!(incomingObject.isEmpty())){
    		for(Contact model : incomingObject) {
              System.out.println(model.toString());
		
    		}
    		
		}
		
			socket2.close();
	
		
    }
   }
    



