package thread.common;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskA implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private volatile boolean done;
	
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
		
		done = true;
		
		synchronized (this) {
			System.out.println("[" + Thread.currentThread().getName()+ "] <" + taskId + "> NOTIFYING ........");
			this.notifyAll();
		}
	}

	public ValueReturningTaskA(int a, int b, long sleepTime) {

		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskC" + instanceNumber;

	}

	public int getSum() {
		if (!done) {
			synchronized (this) {
				try {
					System.out.println("[" + Thread.currentThread().getName() + "} is WAITING result from " + taskId + "... ");
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("[" + Thread.currentThread().getName() + "] WOKEN UP for <" + taskId + ">");
		}
		return sum;
	}

	
}
