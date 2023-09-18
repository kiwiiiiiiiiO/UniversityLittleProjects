package N410977008_HW2;

public class Getter extends Thread {
	Target target;
	Getter(Target target){
		super();
		this.target=target;
	}
	public void run() {
		while (true) {
			try {
				int j =(int)(Math.random()*900)+100 ;
				Thread.sleep(j);	//停留0.5秒，每0.5秒印一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			get();
		}
	}
	void get() {
		synchronized(target) {
			while(target.que.size()<=0) {
				try {
					target.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int a =target.que.poll();
			System.out.println("  get)  "+a);
			target.notify();	
		}
	}
}
