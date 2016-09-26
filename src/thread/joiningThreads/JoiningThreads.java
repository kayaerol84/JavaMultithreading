package thread.joiningThreads;

import thread.common.LoopTaskD;

public class JoiningThreads {

	public static void main(String[] args) throws InterruptedException {
		
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		Thread t1 = new Thread(new LoopTaskD(100), "MyThread-A");
		Thread t2 = new Thread(new LoopTaskD(200), "MyThread-B");
		Thread t3 = new Thread(new LoopTaskD(500), "MyThread-C");
		Thread t4 = new Thread(new LoopTaskD(400), "MyThread-D");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		System.out.println("[" + currentThreadName + "] '" + currentThreadName + " joined " + t1.getName()+ "." );
		t2.join();
		System.out.println("[" + currentThreadName + "] '" + currentThreadName + " joined " + t2.getName()+ "." );		
		t3.join();
		System.out.println("[" + currentThreadName + "] '" + currentThreadName + " joined " + t3.getName()+ "." );
		t4.join();
		System.out.println("[" + currentThreadName + "] '" + currentThreadName + " joined " + t4.getName()+ "." );
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
		
		/*
		 * Output is very explanatory 
		 * ******* 
		 *  [MyThread-C] , USER  <LoopTaskB3> DONE
			[MyThread-A] , USER  <LoopTaskB1> tick tick 9
			******* [MyThread-A] , USER  <LoopTaskB1> DONE
			[main] 'main joined MyThread-A.
			[MyThread-D] , USER  <LoopTaskB4> tick tick 8
			******* [MyThread-B] , USER  <LoopTaskB2> DONE
			[main] 'main joined MyThread-B.
			[main] 'main joined MyThread-C.
			[MyThread-D] , USER  <LoopTaskB4> tick tick 9
			******* [MyThread-D] , USER  <LoopTaskB4> DONE
			[main] 'main joined MyThread-D.
			[main] Main thread ends
		 */
	}
}
