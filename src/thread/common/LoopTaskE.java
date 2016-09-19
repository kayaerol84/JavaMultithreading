package threads.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskE implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private volatile boolean shutdown = false;

	@Override
	public void run() {

		// if you manually set a name on the threads, then you might use the
		// track of your threads
		// so, be careful in order to do that
		Thread.currentThread().setName("Cool-Thread " + instanceNumber);
		// get the name of current Thread
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] <" + taskId + "> tick tick starting");

		for (int i = 0; ; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + "> tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (this) {
				if (shutdown) break;
			}
		}

		System.out.println("******* [" + currentThreadName + "]<" + taskId + "> DONE");
	}
	
	public void cancel() {
		System.out.println("******* [" + Thread.currentThread().getName() + "]<" + taskId + "> CANCEL HERE");
		
		synchronized (this) {
			this.shutdown =true; 
		}
	}

	public LoopTaskE() {

		this.instanceNumber = ++count;
		this.taskId = "LoopTaskE" + instanceNumber;
	}

}