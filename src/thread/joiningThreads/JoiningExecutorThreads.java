package thread.joiningThreads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.NamedThreadFactory;
import thread.common.LoopTaskI;

public class JoiningExecutorThreads {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		
		ExecutorService es = Executors.newCachedThreadPool(new NamedThreadFactory());
		
		//CountDownLatch doneSignal = new CountDownLatch(4);
		CountDownLatch doneSignal = new CountDownLatch(2);
		//es.execute(new LoopTaskI(doneSignal));
		es.execute(new LoopTaskI(null));
		es.execute(new LoopTaskI(doneSignal));
		es.execute(new LoopTaskI(doneSignal));
		//es.execute(new LoopTaskI(doneSignal));
		es.execute(new LoopTaskI(null));
		
		es.shutdown();
		
		try {
			doneSignal.await();
			System.out.println("[" + currentThreadName + "] " + currentThreadName + " GOT THE SIGNAL TO CONTINUE ***** ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		System.out.println("[" + currentThreadName + "] Main thread end");
	}
	
	/* 
	 *  ******* [AmazingThread-4] , USER  <LoopTaskI4> DONE
		******* [AmazingThread-4] , USER  <LoopTaskI4> LATCH COUNT = 3
		[AmazingThread-3] , USER  <LoopTaskI3> tick tick 8
		[AmazingThread-3] , USER  <LoopTaskI3> tick tick 9
		[AmazingThread-1] , USER  <LoopTaskI1> tick tick 9
		[AmazingThread-2] , USER  <LoopTaskI2> tick tick 7
		******* [AmazingThread-1] , USER  <LoopTaskI1> DONE
		******* [AmazingThread-1] , USER  <LoopTaskI1> LATCH COUNT = 2
		******* [AmazingThread-3] , USER  <LoopTaskI3> DONE
		******* [AmazingThread-3] , USER  <LoopTaskI3> LATCH COUNT = 1
		[AmazingThread-2] , USER  <LoopTaskI2> tick tick 8
		[AmazingThread-2] , USER  <LoopTaskI2> tick tick 9
		******* [AmazingThread-2] , USER  <LoopTaskI2> DONE
		******* [AmazingThread-2] , USER  <LoopTaskI2> LATCH COUNT = 0
		[main] main GOT THE SIGNAL TO CONTINUE ***** 
		[main] Main thread end
	 */
	
	/*
	 * 
	 * Second run
	 * 
	 * ******* 
	 *  [AmazingThread-3] , USER  <LoopTaskI3> LATCH COUNT = 1
		[AmazingThread-4] , USER  <LoopTaskI4> tick tick 6
		[AmazingThread-1] , USER  <LoopTaskI1> tick tick 9
		******* [AmazingThread-1] , USER  <LoopTaskI1> DONE
		[AmazingThread-2] , USER  <LoopTaskI2> tick tick 9
		******* [AmazingThread-4] , USER  <LoopTaskI4> DONE
		******* [AmazingThread-2] , USER  <LoopTaskI2> DONE
		[main] main GOT THE SIGNAL TO CONTINUE ***** 
		[main] Main thread end
		******* [AmazingThread-2] , USER  <LoopTaskI2> LATCH COUNT = 0
	 */
}
