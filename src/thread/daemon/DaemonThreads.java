package thread.daemon;

import thread.common.LoopTaskD;

public class DaemonThreads {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		
		Thread thread1 = new Thread(new LoopTaskD(1200));
		thread1.setDaemon(false );
		Thread thread2 = new Thread(new LoopTaskD(2200));
		
		// make this thread as daemon
		thread2.setDaemon(true);
		
		thread1.start();
		thread2.start();
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
}
