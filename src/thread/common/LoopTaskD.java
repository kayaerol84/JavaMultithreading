package thread.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskD implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private long sleepTime;

	@Override
	public void run() {

		boolean isRunningInDaemonThread = Thread.currentThread().isDaemon();
		String threadType = isRunningInDaemonThread ? "DAEMON" : "USER";
		// get the name of current Thread
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "], " + threadType + " <" + taskId + "> tick tick starting");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + currentThreadName + "] , " + threadType + "  <" + taskId + "> tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}

		System.out.println("******* [" + currentThreadName + "] , " + threadType + "  <" + taskId + "> DONE");
	}

	public LoopTaskD(long sleepTime) {

		this.instanceNumber = ++count;
		this.taskId = "LoopTaskB" + instanceNumber;
		this.sleepTime = sleepTime;
	}

}
