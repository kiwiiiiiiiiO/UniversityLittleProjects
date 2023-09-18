package N410977008_HW2;

public class Putter extends Thread{
	Target target;
	Putter(Target target){
		super();
		this.target=target;
	}
	public void run() {
		while (true) {
			try {
				int c=(int)(Math.random()*900)+100 ;
				Thread.sleep(c);	//停留0.5秒，每0.5秒印一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			put();
		}
	}
	void put() {
		synchronized(target) {
			while(target.que.size()>=target.limit) {
				try {
					target.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int a = (int)(Math.random()*100);	//產生0~99數字
			target.que.add(a);
			System.out.println(a+"  (put");
			target.notify();
			
		}
	}

}
