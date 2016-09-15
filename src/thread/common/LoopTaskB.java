package thread.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskB implements Runnable {

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {

		// if you manually set a name on the threads, then you might use the track of your threads
		// so, be careful in order to do that
		Thread.currentThread().setName("Cool-Thread " + instanceNumber);
		// get the name of current Thread
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] <" + taskId + "> tick tick starting");

		for (int i = 0; i < 10; i++) {
			System.out.println("[" + currentThreadName + "] <" + taskId + "> tick tick " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}

		System.out.println("******* [" + currentThreadName + "]<" + taskId + "> DONE");
	}

	public LoopTaskB() {

		this.instanceNumber = ++count;
		this.taskId = "LoopTaskB" + instanceNumber;
	}

}
