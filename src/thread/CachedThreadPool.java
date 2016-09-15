package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
	
	/*
	 * FixedThreadPool vs CachedThreadPool
	 * number of threads are fixed
	 * other tasks have to wait for others to end
	 * cached > no wait queue concept
	 */

	public static void main(String[] args) {
		System.out.println("Main thread started");
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		
		executor.shutdown();
		
		/*
		 * all the tasks starts together at the same time
		 */
		
		System.out.println("Main thread ended");
	}
}
