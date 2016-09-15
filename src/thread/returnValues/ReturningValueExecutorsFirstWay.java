package thread.returnValues;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import thread.common.CalculationTaskA;
import thread.common.LoopTaskA;
import thread.common.ValueReturningTaskA;

public class ReturningValueExecutorsFirstWay {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
				
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
	
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Integer> result1 = executor.submit(new CalculationTaskA(3, 5, 1000));
		Future<Integer> result2 = executor.submit(new CalculationTaskA(5, 8, 1300));
		Future<Integer> result3 = executor.submit(new CalculationTaskA(8, 13, 2000));
		
		Future<?> result4 = executor.submit(new LoopTaskA());
		Future<Double> result5 = executor.submit(new LoopTaskA(), 999.888);		
		Future<Boolean> result6 = executor.submit(new LoopTaskA(), false);
		
		executor.shutdown();
		
		System.out.println("Result 1 : " + result1.get());
		System.out.println("Result 2 : " + result2.get());
		System.out.println("Result 3 : " + result3.get());
		System.out.println("Result 4 : " + result4.get());
		System.out.println("Result 5 : " + result5.get());
		System.out.println("Result 6 : " + result6.get());
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
	
}
