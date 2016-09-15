package thread;

import java.util.concurrent.TimeUnit;

public class NamingThreadSecondWay {
	
	public static void main(String[] args) {
		
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		new Thread(new LoopTaskC(), "MyThread-1").start();
		Thread t2 = new Thread(new LoopTaskC());
		// t2.setName("MyThread-2");
		t2.start();
		try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// after 800 milliseconds thread name is going to be changed 
		// JVM allows to change name in runtime. 
		t2.setName("MyThread-2");
		
		System.out.println("[" + currentThreadName + "] Main thread started");
	}

}
