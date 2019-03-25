package distribute;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DistributedNodes extends Remote{
public String join(String joinerReference) throws RemoteException;
public void notifyRight(String rightNeighbour) throws RemoteException;
public long getMax()throws RemoteException;
public void notifyMax(long newMax)throws RemoteException;
public void resetMax()throws RemoteException;
public String getServiceName() throws RemoteException;


}
