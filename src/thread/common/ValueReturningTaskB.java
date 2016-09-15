package thread.common;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskB implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private ResultListener<Integer> listener;
	
	@Override
	public void run() {
		
		System.out.println("####### [" + Thread.currentThread().getName() + "] <" + taskId + "> tick tick starting #####");
		System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + "> sleeping for " + sleepTime + " millis");

		
		System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + "> tick tick " );

		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
		sum = a + b;

		System.out.println("******* [" + Thread.currentThread().getName() + "]<" + taskId + "> DONE");
		
		listener.notifyResult(sum);
		
	}

	public ValueReturningTaskB(int a, int b, long sleepTime, ResultListener<Integer> listener) {

		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskC" + instanceNumber;
		
		this.listener = listener;
	}
	
}
