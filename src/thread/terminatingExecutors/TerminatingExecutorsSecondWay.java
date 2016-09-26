package thread.terminatingExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.sun.javafx.css.CalculatedValue;

import thread.NamedThreadFactory;
import thread.common.CalculationTaskC;
import thread.common.FactorialTaskA;
import thread.common.LoopTaskE;
import thread.common.LoopTaskF;

public class TerminatingExecutorsSecondWay {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		CalculationTaskC task1 = new CalculationTaskC();
		LoopTaskF task2= new LoopTaskF();
		LoopTaskF task3= new LoopTaskF();
		
		
		Future<Long> f1 = execService.submit(task1); 
		Future<?> f2 = execService.submit(task2);
		
		Future<?> f3 = execService.submit(task3);
		
		execService.shutdown();
		
		try {
			TimeUnit.SECONDS.sleep(2);
			// TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("[" + currentThreadName + "] Interrupting CalcTaskC ");
		f1.cancel(true);
		

		System.out.println("[" + currentThreadName + "] Interrupting LoopTaskF-1 ");
		f2.cancel(true);

		System.out.println("[" + currentThreadName + "] Interrupting LoopTaskF-2 ");
		f3.cancel(true);
		
		System.out.println("[" + currentThreadName + "] Main thread ended");
	}
}
