
package doner;
public class DonerSpit {
	
    public int producedDoner = 0;
    public  void TakeSomeDoner(int i){
		 synchronized(this){	
		 	while(producedDoner < 1) {
		 	i++;
			System.out.println("Doner " + i + "  has produced"); 
			producedDoner ++;
			AddSaladAndDressing();
			try {
			Thread.sleep(5000);
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
				}
		 	}
		 }
	 }
	 private void AddSaladAndDressing (){
	System.out.println("Salad added.");
		}
	 public synchronized void EatDoner(int a){
		 	if(producedDoner >= 1) {
                                
				System.out.println("doner " + producedDoner + " has taken by the Customer");
                                producedDoner --;
				try {
				Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 		}
	 }
	 
	 
	 
}

