package Chat;
import java.rmi.*;
import java.util.ArrayList;



public interface RemoteChat extends Remote {
public RemoteChat registerUser(String nNam)throws RemoteException;//Register users
public void send(String msg) throws RemoteException;//Sends message to server
public ArrayList<String> recieve()throws RemoteException;//Gets message form server 
}