package Chat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;


public class RChatServer implements RemoteChat {

    ArrayList<RemoteChatImpl> clients=new ArrayList<RemoteChatImpl>();
    int count=0;
    ArrayList<String> l = new ArrayList<String>();
	String sl;
	ObjectInputStream ro;
	ObjectOutputStream wo;
	public RChatServer(ObjectInputStream in, ObjectOutputStream out) {
		this.ro = in;
		this.wo = out;
		Distributer md = new Distributer(this.clients);
		md.start();
	}
	@Override
	public RemoteChat registerUser(String nam) throws RemoteException {
		//create new client
		RemoteChatImpl c = new RemoteChatImpl(nam);
		
		RemoteChat r = null;
		//critical section
		synchronized (this) {
			//add new client to the list of clients
			clients.add(c);
			//increase the number of current clients
			count++;
	   }
		//return new client
		try{
	      r=(RemoteChat) UnicastRemoteObject.exportObject(c,0);
		}
		catch(RemoteException e){
			System.err.println("Error!");
		}
		return r;
	}
	public void log(){
		//read log file
		try {
			Object f = ro.readObject();
		} catch (IOException e) {
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//write into the log file
		try {
			wo.writeObject(l);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void send(String msg) throws RemoteException {
	}

	@Override
	public ArrayList<String> recieve() throws RemoteException {
				return null;
	}
   public static void main(String args[]) throws Exception{
	    String f="ChatLogFile";
	    File file = new File(f);//create  a log file to store user(s) chats
		FileOutputStream fout = new FileOutputStream(file);
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		FileInputStream fin = new FileInputStream(file);
		ObjectInputStream oin = new ObjectInputStream(fin);

		RChatServer ser = new RChatServer(oin, oout);
		RemoteChat stub = (RemoteChat) UnicastRemoteObject.exportObject(ser, 0);
		Registry registry = LocateRegistry.getRegistry();
		registry.rebind("Service", stub);
		Thread.sleep(10000);
	   
   }
 
   class Distributer extends Thread {
  	ArrayList<RemoteChatImpl> clients;

  	public Distributer(ArrayList<RemoteChatImpl> clients) {
  		this.clients = clients;
  	}

  	public void run() {
  		while (true) {
  			// sleep for three seconds
  			try {
  				Thread.sleep(3000);
  			} catch (InterruptedException e) {
  				e.printStackTrace();
  			}

  			// 
  			ArrayList<MessageFormat> m = new ArrayList<MessageFormat>();

  			// receive all new messages from clients
  			for (RemoteChatImpl i : clients) {
  				ArrayList<MessageFormat> msg = i.recieveUsrMsg();
  				for (MessageFormat mf : msg) {
  					mf.setname(i.getname());
  				}
  				m.addAll(msg);
  			}

  			//sort chat messages by time
  			Collections.sort(m);

  			
  			ArrayList<String> msgs = new ArrayList<String>();
  			for (MessageFormat cm :m) {
  				msgs.add(cm.toString());
  				System.out.println(cm.toString());
  			}

  			// append messages to all clients
  			for (RemoteChatImpl c : clients) {
  				c.sendMsgToUsr(msgs);
  			}
          
  			l.addAll(msgs);
  			log();
  		}
  	}

   }
}


   

