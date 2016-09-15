package thread.common;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculationTaskB implements Callable<TaskResult<String, Integer>> {

	private int a;
	private int b;
	private long sleepTime; 

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	
	
	@Override
	public TaskResult<String, Integer> call() throws Exception{
		
		System.out.println("####### [" + Thread.currentThread().getName() + "] <" + taskId + "> tick tick starting #####");
		System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + "> sleeping for " + sleepTime + " millis");

		
		System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + "> tick tick " );

		TimeUnit.MILLISECONDS.sleep(sleepTime);		

		System.out.println("******* [" + Thread.currentThread().getName() + "]<" + taskId + "> DONE");
		 
		return new TaskResult<String, Integer>(taskId, a + b);
	}

	public CalculationTaskB(int a, int b, long sleepTime) {

		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "CalcTaskA" + instanceNumber;
		 
	}
	
}
