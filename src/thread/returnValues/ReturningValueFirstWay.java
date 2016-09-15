package thread.returnValues;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import thread.common.ValueReturningTaskA;

public class ReturningValueFirstWay {
	
	public static void main(String[] args) {
		
		Thread.currentThread().setName("Main Thread");
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		ValueReturningTaskA task1 = new ValueReturningTaskA(5, 8, 100);
		Thread thread1 = new Thread(task1, "Thread-1");
		
		ValueReturningTaskA task2 = new ValueReturningTaskA(3, 8, 2000);
		Thread thread2 = new Thread(task2, "Thread-2");
		
		ValueReturningTaskA task3 = new ValueReturningTaskA(1, 8, 2000);
		Thread thread3 = new Thread(task3, "Thread-3");
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println("Result 1 : " + task1.getSum());
		System.out.println("Result 2 : " + task2.getSum());
		System.out.println("Result 3 : " + task3.getSum());
		
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
	
	/*
	 * //ExecutorService executor = Executors.newCachedThreadPool();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		executor.submit(new LoopTaskB());
		executor.submit(new LoopTaskB());
		executor.submit(new LoopTaskB());
		executor.submit(new LoopTaskB());
		
		executor.shutdown();
	 */

}
