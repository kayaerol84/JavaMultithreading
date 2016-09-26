package thread.terminatingExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.sun.javafx.css.CalculatedValue;

import thread.NamedThreadFactory;
import thread.common.CalculationTaskC;
import thread.common.FactorialTaskA;
import thread.common.FactorialTaskB;
import thread.common.LoopTaskA;
import thread.common.LoopTaskE;
import thread.common.LoopTaskF;
import thread.common.LoopTaskG;

public class TerminatingBlockedExecutorTasks {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		FactorialTaskB task1 = new FactorialTaskB(35, 1000);
		
		LoopTaskA task2= new LoopTaskA();
		LoopTaskG task3= new LoopTaskG();
		
		
		Future<Long> f1 = execService.submit(task1); 
		execService.execute(task2);
		Future<?> f2 = execService.submit(task2);
		
		Future<?> f3 = execService.submit(task3);
		
		execService.shutdown();
		
		
		TimeUnit.SECONDS.sleep(3); //TimeUnit.SECONDS.sleep(10);
		
		System.out.println("[" + currentThreadName + "] Invoking cancel() on all the tasks ");
		f1.cancel(true);
		f2.cancel(true);
		f3.cancel(true);
		
		System.out.println("[" + currentThreadName + "] Main thread ended");
		/*
		 * ******* [AmazingThread-3]<LoopTaskG1> DONE
		java.lang.InterruptedException: sleep interrupted
		<TASK=1> tick tick 4
			at java.lang.Thread.sleep(Native Method)
			at java.lang.Thread.sleep(Unknown Source)
			at java.util.concurrent.TimeUnit.sleep(Unknown Source)
			at thread.common.LoopTaskA.run(LoopTaskA.java:17)
			at java.util.concurrent.Executors$RunnableAdapter.call(Unknown Source)
			at java.util.concurrent.FutureTask.run(Unknown Source)
			at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
			at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
			at java.lang.Thread.run(Unknown Source)
		<TASK=1> tick tick 5

		
		 */
	}
}
