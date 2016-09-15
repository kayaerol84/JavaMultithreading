package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NamingThreadFirstWay {
	
	public static void main(String[] args) {
		
		Thread.currentThread().setName("Main Thread");
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		//ExecutorService executor = Executors.newCachedThreadPool();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		executor.submit(new LoopTaskB());
		executor.submit(new LoopTaskB());
		executor.submit(new LoopTaskB());
		executor.submit(new LoopTaskB());
		
		executor.shutdown();
		
		System.out.println("[" + currentThreadName + "] Main thread started");
	}

}
