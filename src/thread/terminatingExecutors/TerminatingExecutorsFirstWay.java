package thread.terminatingExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import thread.NamedThreadFactory;
import thread.common.FactorialTaskA;
import thread.common.LoopTaskE;

public class TerminatingExecutorsFirstWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		LoopTaskE task1= new LoopTaskE();
		FactorialTaskA task2 = new FactorialTaskA(30, 1000);
		
		execService.execute(task1); // execService.submit((task1);
		execService.submit(task2);
		
		execService.shutdown();
		
		try {
			TimeUnit.SECONDS.sleep(15);
			// TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		task1.cancel();
		task2.cancel();
		
		System.out.println("[" + currentThreadName + "] Main thread ended");
	}
}
