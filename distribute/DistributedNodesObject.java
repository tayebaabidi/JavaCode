package distribute;
import java.util.UUID;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.Naming;
public class DistributedNodesObject implements DistributedNodes{
	
	String ServiceName=null;
        long ownMax=0;
	long currentMax=0;
        static boolean maxTrue=false;
	DistributedNodes rightNeighbour=null;
        DistributedNodes leftNeighbour=null;

	public DistributedNodesObject (String SName, boolean isBootStrapedoer){
		
		this.ServiceName=SName;
		UUID UniqueID=UUID.randomUUID();
		ownMax=(long)UniqueID.variant()+UniqueID.hashCode();
		System.out.println("This is the Max value for the current host" + ownMax);
		if(isBootStrapedoer){
		this.leftNeighbour=this.rightNeighbour=this;
		}
	}
	public void JoinNetwork(String rightHost){
		try {
		rightNeighbour=(DistributedNodes)Naming.lookup("rmi://localhost:4444/" + rightHost);
		String LeftHostOld=rightNeighbour.join(ServiceName);
		DistributedNodes LeftNeighbourOld= (DistributedNodes)Naming.lookup("rmi://localhost:4444/" + LeftHostOld);
		this.leftNeighbour=LeftNeighbourOld;
		LeftNeighbourOld.notifyRight(ServiceName);
System.out.println("Service Name " + ServiceName + " right neighbour "+rightNeighbour.getServiceName());
System.out.println("Service Name " + ServiceName + " left neighbour "+LeftNeighbourOld.getServiceName());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String join(String joinerReference) throws RemoteException {
		// TODO Auto-generated method stub
		String oldLeftNeighbour=leftNeighbour.getServiceName();
		try {
			System.out.println("Service Name "+ServiceName+" new left neighbour "+joinerReference);
			leftNeighbour=(DistributedNodes)Naming.lookup("rmi://localhost:4444/"+joinerReference);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldLeftNeighbour;
	}
	@Override
	public void notifyRight(String rightNeighbour) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Service Name " + ServiceName + " new right neighbour "+rightNeighbour);
			this.rightNeighbour=(DistributedNodes)Naming.lookup("rmi://localhost:4444/"+rightNeighbour);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getServiceName() throws RemoteException{
		// TODO Auto-generated method stub
		return ServiceName;
	}
	@Override
	public long getMax() throws RemoteException {
		// TODO Auto-generated method stub
		if(maxTrue)
		return ownMax;
		currentMax=ownMax;
		leftNeighbour.notifyMax(currentMax);
		synchronized(this){
			
			while(!maxTrue){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return currentMax;
	}
	@Override
	public void notifyMax(long newMax) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Service Name before "+ServiceName+" own Max: "+ownMax+" curr Max: "+currentMax+" new Max "+newMax);
		
			if(newMax > currentMax){
				currentMax=newMax;
				resetMax();
			}
			
			else if(newMax == currentMax){
				synchronized(this){
				if(!maxTrue){
					maxTrue=true;
				    
				    }
				this.notify();
				}
			}

			else{
				leftNeighbour.notifyMax(currentMax);
			}
	}
	@Override
	public void resetMax() throws RemoteException {
		// TODO Auto-generated method stub
		leftNeighbour.notifyMax(currentMax);
	}

}
