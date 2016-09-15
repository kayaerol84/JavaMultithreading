package thread;

import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable {

	private static int count = 0;
	private int id ;
	@Override
	public void run() {
		System.out.println("<TASK=" + id + "> tick tick starting" );
		
		for (int i = 0; i < 10; i++) {
			System.out.println("<TASK=" + id + "> tick tick " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() *1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("<TASK=" + id + "> DONE" );
	}
	public LoopTaskA() {
		
		this.id = ++count;
	}
	
	

}
