package thread.orderOfCompletion;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import thread.common.CalculationTaskA;
import thread.common.CalculationTaskB;
import thread.common.LoopTaskA;
import thread.common.TaskResult;
import thread.common.ValueReturningTaskA;

public class ReturningValueExecutorsSecondWay {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
				
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
	
		ExecutorService executor = Executors.newCachedThreadPool();
		// takes executor as a parameter
		CompletionService<TaskResult<String, Integer>> tasks = new ExecutorCompletionService<TaskResult<String, Integer>>(executor);
		
		Future<TaskResult<String, Integer>> result1 = tasks.submit(new CalculationTaskB(3, 5, 2300));
		Future<TaskResult<String, Integer>> result2 = tasks.submit(new CalculationTaskB(5, 8, 1300));
		Future<TaskResult<String, Integer>> result3 = tasks.submit(new CalculationTaskB(8, 13, 2000));
		
		/*Future<?> result4 = tasks.submit(new LoopTaskA()); */
		Future<TaskResult<String, Integer>> result5 = tasks.submit(new LoopTaskA(), new TaskResult<String, Integer>("task4", 999));		
		/*Future<Boolean> result6 = executor.submit(new LoopTaskA(), false);*/
		
		executor.shutdown();
		
		for (int i = 0; i < 4; i++) {
			// take() >> Retrieves and removes the Future representing the next completed task, waiting if none are yet present
			System.out.println(tasks.take().get());
		}
		
		 
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
	
}
