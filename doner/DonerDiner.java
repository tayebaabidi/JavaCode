package doner;
public class DonerDiner {

    public static void main(String[] args) {
     
		
        DonerSpit s = new DonerSpit();
	Thread customer1 = new DonerCustomer(s);
	Thread customer2 = new DonerCustomer(s);
        Thread customer3 = new DonerCustomer(s);
        
        Thread server1 = new DonerServer(s,"server 1");
	Thread server2 = new DonerServer(s,"server 2");
	server1.start();
	server2.start();
        customer1.start();
	customer2.start();
	customer3.start();
       
		
	}
}

