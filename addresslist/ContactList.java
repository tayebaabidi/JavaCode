package addresslist;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList{
	static File a;
	public static ObjectOutputStream oos;
	public static ArrayList<Contact> ab=new ArrayList<Contact>();
	//store method at first saves all the contacts if exists in a array list
	//then adds new contacts to the list and finally it stores the list to the file and does not overwrite the file.
    @SuppressWarnings("unchecked")
	public static void store(ArrayList<Contact> cantact) throws IOException, ClassNotFoundException {
    	ArrayList<Contact> cc=new ArrayList<Contact>();
    	a=new File("AddressBook.ser");
    	if(a.exists()){
    		ObjectInputStream ois2;
			try {
				ois2 = new ObjectInputStream(new FileInputStream("AddressBook.ser"));
				cc=(ArrayList<Contact>) ois2.readObject();
	    		
	             ois2.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    		
    		}
    	    	try {
    	    		
    	    		cc.addAll(cantact);
    	    		oos = new ObjectOutputStream(new FileOutputStream("AddressBook.ser"));
			        oos.writeObject(cc);
			        oos.close();
    	    		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    //Read method reads the objects of the file then saves it to an array list and displays it.
    	@SuppressWarnings("unchecked")
	public static void read(){
    	
    	try {
    		a=new File("AddressBook.ser");
        	
    		if(a.exists()){
    		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("AddressBook.ser"));
    		ArrayList<Contact> cc=new ArrayList<Contact>();
    		cc=(ArrayList<Contact>) ois.readObject();
    		ois.close();
    		if(!(cc.isEmpty())){
    			System.out.println("It is your contacts:");
    		for(Contact model : cc) {
                System.out.println(model.toString());
            }
    		}
    		
    		else
    			 System.out.println("you have not insert contact yet!");
    		
    		}
		} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    @SuppressWarnings("unchecked")
	public static void search(char i, String j){
	    try {
	    int k=0;
        a=new File("AddressBook.ser");
        if(a.exists()){
 		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("AddressBook.ser"));
 		ArrayList<Contact> cc=new ArrayList<Contact>();
 		cc=(ArrayList<Contact>) ois.readObject();
 		String name="", sure="", email="";
 		for(Contact model : cc) {
 			name=model.getName();
 			sure=model.getSure();
 			email=model.getEmail();
 			if( i=='1' && name.contains(j)){
 			 k++;
             System.out.println(model.toString());
 		     }
 			else if(i=='2'&& sure.contains(j)){
 		     k++;
             System.out.println(model.toString());
     		}
 			else if(i=='3'&& email.contains(j)){
 			 k++;
             System.out.println(model.toString());
     		}
 		
 		  }
 		if(k==0){
 			System.out.println("We can not find any record with this term!");
 		}
 		ois.close();
        }
        else{
        System.out.println("the file does not exist");
        }
 		
		} 
	    catch (IOException e) {
			e.printStackTrace();
		} 
	    catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
     }
    //The insert() method is used here for inserting contacts it will call store to store the contacts.
    public static void insert() throws ClassNotFoundException, IOException{
	     @SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
	     String s;
	     Contact a=new Contact();
         System.out.println("Be careful that name, surename and email are mandetory fields in case of whispace caracter or empty character you will return!\npress enter to continue:");
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
         if(t=='s'){
    	    store(ab);
    	
         }
         else if(t=='c'){
    	      insert(); 
        }
        }
    }