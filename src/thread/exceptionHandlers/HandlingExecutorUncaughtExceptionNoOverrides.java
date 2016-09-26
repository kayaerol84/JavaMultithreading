package thread.exceptionHandlers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.common.ExceptionLeakikngTask;

public class HandlingExecutorUncaughtExceptionNoOverrides {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		
		/* 
		 * Need to implement ThreadFactory first
		 * Then, in ThreadFactory we will set the Uncaught Exception Handler 
		 */
	
		
		ExecutorService es = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandler());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		
		es.shutdown();
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
		
		/*
		 * [main] Main thread started
			ThreadExceptionHandler [handlerId=null] caught Exception in Thread - " Thread-1" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=null] caught Exception in Thread - " Thread-0" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=null] caught Exception in Thread - " Thread-2" =>java.lang.RuntimeException
			[main] Main thread ends
			ThreadExceptionHandler [handlerId=null] caught Exception in Thread - " Thread-5" =>java.lang.RuntimeException

		 */
		
	}
}
