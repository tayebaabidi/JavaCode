package Chat;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class RemoteChatImpl implements RemoteChat {
	String name;
	ArrayList<String> resivemsg;//Gets message from server 
	ArrayList<MessageFormat> sendmsg;//Sends message to the server
	public RemoteChatImpl(String n){
		this.name=n;
		sendmsg = new ArrayList<MessageFormat>();
		resivemsg = new ArrayList<String>();
	}
	public String getname() {
		return name;
	}

	@Override
	public RemoteChat registerUser(String nNam) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		MessageFormat mf = new MessageFormat(msg);
		//critical section 
		synchronized (this) {
			sendmsg.add(mf);
		}
		
	}

	@Override
	public ArrayList<String> recieve() throws RemoteException {
		ArrayList<String> msgUser;
		//critical section
		synchronized (this) {
			
			msgUser = resivemsg;
			resivemsg = new ArrayList<String>();
		}

		return msgUser;
	}
	public ArrayList<MessageFormat> recieveUsrMsg() {
		
		ArrayList<MessageFormat> msgUse;
//critical section
		synchronized (this) {
			msgUse = sendmsg;
			sendmsg = new ArrayList<MessageFormat>();
		}

		return msgUse;
	}

	public void sendMsgToUsr(ArrayList<String> msg) {
		//critical section
		synchronized (this) {
			this.resivemsg.addAll(msg);
		}
	}

}