package threads.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LoopTaskF implements Runnable {

	private static final int DATA_SIZE = 100000;
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
			
			doSomeWork();
			
			if (Thread.interrupted()){
				System.out.println("[" + currentThreadName + "] <" + taskId + "> interrupted. Cancelling... " + i);
				break;
			}
		}

		System.out.println("[" + currentThreadName + "] <" + taskId + "> retrieving 'INTERRUPTED' status again... " );
		System.out.println("******* [" + currentThreadName + "]<" + taskId + "> DONE");
	}

	private void doSomeWork() {
		for (int i = 0; i < 2; i++) {
			Collections.sort(generateDataSet());
		}
	}
	private List<Integer> generateDataSet() {
		// TODO Auto-generated method stub
		List<Integer> myList = new ArrayList<>();
		
		Random randomGen = new Random();
		for (int i = 0; i < DATA_SIZE; i++) {
			myList.add(randomGen.nextInt(DATA_SIZE));
		}
		return myList;
	}

	public LoopTaskF() {

		this.instanceNumber = ++count;
		this.taskId = "LoopTaskF" + instanceNumber;
	}

}