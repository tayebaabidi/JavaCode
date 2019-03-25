package distribute;
import java.util.UUID;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class DistributedMaxNode1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Registry reg= LocateRegistry.getRegistry(4444);
			Boolean bootstrapExists=false;
			String[] currentHosts = reg.list();
			String ServiceName="";
			for(String hosts: currentHosts){
				if(hosts.equals("bootstrap")){
					bootstrapExists=true;
				}	
			}		
			try{
				DistributedNodesObject Host = null;
				if(!bootstrapExists){
					System.out.println("This is a bootstrap holder host");
					ServiceName = "bootstrap";
					Host = new DistributedNodesObject(ServiceName,true);
				}else{
			
					ServiceName=UUID.randomUUID().toString();
					System.out.println("Host started to be a " + ServiceName);
					Host=new DistributedNodesObject(ServiceName,false);
				}
			
				DistributedNodes Stub=(DistributedNodes)UnicastRemoteObject.exportObject(Host,0);
				reg.rebind(ServiceName, Stub);
				if(bootstrapExists){
			
				Host.JoinNetwork("bootstrap");
				}
				//hold until nodes join
				Thread.sleep(8000);
				long maxValue=Host.getMax();
				System.out.println("The maximum  distance is find at " + Host.ServiceName + ":" + maxValue);
			}

			catch(Exception e){e.printStackTrace();
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
