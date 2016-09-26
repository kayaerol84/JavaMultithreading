package thread.joiningThreads;

import thread.common.ValueReturningTaskC;

public class ReturningValueThirdWay {
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread.currentThread().setName("Main Thread");
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		//ValueReturningTaskC task1 = new ValueReturningTaskC(5, 8, 2000);
		ValueReturningTaskC task1 = new ValueReturningTaskC(5, 8, 500);
		Thread t1 = new Thread(task1, "Thread-1");
		
		ValueReturningTaskC task2 = new ValueReturningTaskC(3, 8, 1000);
		Thread t2 = new Thread(task2, "Thread-2");
		
		//ValueReturningTaskC task3 = new ValueReturningTaskC(1, 8, 500);
		ValueReturningTaskC task3 = new ValueReturningTaskC(1, 8, 2500);
		Thread thread3 = new Thread(task3, "Thread-3");
		
		t1.start();
		t2.start();
		thread3.start();
		
		t1.join();
		System.out.println("Result 1 : " + task1.getSum());
		t2.join();		
		System.out.println("Result 2 : " + task2.getSum());
		thread3.join();
		System.out.println("Result 3 : " + task3.getSum());
		
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
	
	/*
	 *  
	 * ####### 
	 * 	[Thread-3] <LoopTaskC3> tick tick starting #####
		[Thread-3] <LoopTaskC3> sleeping for 500 millis
		[Thread-3] <LoopTaskC3> tick tick 
		******* [Thread-3]<LoopTaskC3> DONE
		******* [Thread-2]<LoopTaskC2> DONE
		******* [Thread-1]<LoopTaskC1> DONE
		Result 1 : 13
		Result 2 : 11
		Result 3 : 9
		[Main Thread] Main thread ends
		
		
      2nd time;
      ####### 
        [Thread-3] <LoopTaskC3> tick tick starting #####
		[Thread-3] <LoopTaskC3> sleeping for 2500 millis
		[Thread-3] <LoopTaskC3> tick tick 
		******* [Thread-1]<LoopTaskC1> DONE
		Result 1 : 13
		******* [Thread-2]<LoopTaskC2> DONE
		Result 2 : 11
		******* [Thread-3]<LoopTaskC3> DONE
		Result 3 : 9
		[Main Thread] Main thread ends		

	 */

}
