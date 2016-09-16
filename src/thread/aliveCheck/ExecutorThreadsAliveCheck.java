package thread.aliveCheck;

import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import thread.NamedThreadFactory;
import thread.common.CalculationTaskA;
import thread.common.LoopTaskC;
import thread.common.LoopTaskD;

public class ExecutorThreadsAliveCheck {


	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		Future<?> f1 = execService.submit(new LoopTaskC());
		Future<Integer> f2 = execService.submit(new CalculationTaskA(2, 5, 7000));
		
		FutureTask<?> ft3 = new FutureTask<Void>(new LoopTaskC(), null);
		FutureTask<Integer> ft4 = new FutureTask<Integer>(new LoopTaskC(), 998);
		FutureTask<Integer> ft5 = new FutureTask<Integer>(new CalculationTaskA(3, 8, 2300));
		
		execService.execute(ft3);
		execService.execute(ft4);
		execService.execute(ft5);
		
		execService.shutdown();
		
		for (int i = 0; i < 6; i++) {
			TimeUnit.MILLISECONDS.sleep(600);
			System.out.println("[" + currentThreadName + "] Iteration " + i + " is LoopTaskC-1 is done = " + f1.isDone());
			System.out.println("[" + currentThreadName + "] Iteration " + i + " is CalcTaskA-1 is done = " + f2.isDone());
			
			System.out.println("[" + currentThreadName + "] Iteration " + i + " is LoopTaskC-2 is done = " + ft3.isDone());
			System.out.println("[" + currentThreadName + "] Iteration " + i + " is LoopTaskC-3 is done = " + ft4.isDone());
			System.out.println("[" + currentThreadName + "] Iteration " + i + " is CalcTaskA-2 is done = " + ft5.isDone());
			
		}
		
		/*
		 * 
		 */
		
		System.out.println("[" + currentThreadName + "] RETRIEVING RESULTS ***** ");
		
		System.out.println("[" + currentThreadName + "] LoopTaskA-1 result = " + f1.get());
		System.out.println("[" + currentThreadName + "] CalcTaskA-1 result = " + f2.get());		
		System.out.println("[" + currentThreadName + "] LoopTaskA-2 result = " + ft3.get());
		System.out.println("[" + currentThreadName + "] LoopTaskA-3 result = " + ft4.get());
		System.out.println("[" + currentThreadName + "] CalcTaskA-2 result = " + ft5.get());
		
		System.out.println("[" + currentThreadName + "] Main thread ends..../////");
	}
}
