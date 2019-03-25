package networkaddreslist;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
//Server part
public class ContactList implements Runnable{
	static File a;
	public static ObjectOutputStream oos;
	public static ArrayList<Contact> ab=new ArrayList<Contact>();
	int i;
	public ContactList(int i ) {
		this.i =i;	
			
	}
	
	public static void main(String[] args)  throws IOException, ClassNotFoundException {
		Thread t1 = new Thread(new ContactList(1));
		Thread t2 = new Thread(new ContactList(2));
		t1.start();
		t2.start();
		}
	public void run() {
		try{
		if(i==1)
		runServer();
		else if(i==2)
		runServer1();}
		catch (Exception ex){}
		
	}
			@SuppressWarnings("unchecked")
			public void runServer() throws IOException, ClassNotFoundException{
				ServerSocket serversokect = new ServerSocket(4444);
				System.out.println("Ready for connection to store");
				Socket socket = serversokect.accept();
				ObjectInputStream clientinput = new ObjectInputStream(socket.getInputStream());
				ArrayList<Contact> incomingObject=(ArrayList<Contact>)clientinput.readObject();
				store(incomingObject);
				serversokect.close();
			}
			
			@SuppressWarnings("unchecked")
			public void runServer1() throws IOException, ClassNotFoundException{
				ServerSocket serversokect1 = new ServerSocket(5555);
				System.out.println("Ready for connection to read");
				Socket socket1 = serversokect1.accept();//accept incoming connection return it as a socket
				ObjectOutputStream out = new ObjectOutputStream(socket1.getOutputStream());
				if(a.exists()){
		    		ObjectInputStream redf = new ObjectInputStream(new FileInputStream("AddressBookNet.ser"));
		    		ArrayList<Contact> cc=new ArrayList<Contact>();
		    		cc=(ArrayList<Contact>) redf.readObject();
		    		redf.close();
	    		out.writeObject(cc);
	    		serversokect1.close();
				}
	    		}
	    		

    @SuppressWarnings("unchecked")
	public static void store(ArrayList<Contact> cantact) throws IOException, ClassNotFoundException {
    	ArrayList<Contact> cc=new ArrayList<Contact>();
    	a=new File("AddressBookNet.ser");
    	if(a.exists()){
    		ObjectInputStream ois2;
			try {
				ois2 = new ObjectInputStream(new FileInputStream("AddressBookNet.ser"));
				cc=(ArrayList<Contact>) ois2.readObject();
	    		
	             ois2.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		}
    	    	try {
    	    		
    	    		cc.addAll(cantact);
    	    		oos = new ObjectOutputStream(new FileOutputStream("AddressBookNet.ser"));
			        oos.writeObject(cc);
			        oos.close();
    	    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}