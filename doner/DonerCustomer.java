
package doner;
public class DonerCustomer extends Thread{
	
    public DonerSpit spit;
    public DonerServer DS = new DonerServer(spit,"");
    
	
    public DonerCustomer (DonerSpit a){
		spit = a;
	}
	
	public void run(){
        MyCustomer();
        }
        private void MyCustomer(){
		while(true){
			if (spit.producedDoner == 1 || spit.producedDoner == 0){
				synchronized(spit){
                                spit.notifyAll();
                               
				}
			}
			spit.EatDoner(spit.producedDoner);
		}
	}
	
}

	
