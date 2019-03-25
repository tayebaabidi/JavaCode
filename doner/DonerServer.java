    
package doner;
public class DonerServer extends Thread{	
		
		public DonerSpit spit;
		private String server;
		
		public DonerServer (DonerSpit s1, String s2){
			spit = s1;
			server = s2;
		}
		
		public void run()
                {  MyServer();}
                
                private void MyServer(){
			try {
				while(true){
				synchronized(spit) {
				while (spit.producedDoner == 2 || spit.producedDoner == 1){
				System.out.print("Waiting for Customer's request"+ '\n');
                                spit.wait();
				}
                                
				spit.TakeSomeDoner(spit.producedDoner);
					
					}
				}
			} catch(InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		
}
