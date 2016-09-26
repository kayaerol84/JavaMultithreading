package thread.exceptionHandlers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.common.ExceptionLeakikngTask;

public class HandlingExecutorUncaughtExceptionDifferentPerThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		
		/* 
		 * thread 0 --> default handler
		 * thread 1 --> custom handler
		 * thread 2 --> default handler
		 * thread 3 --> custom handler
		 */
	
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("Default Handler"));
		
		ExecutorService es = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandlerAlternator());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		
		es.shutdown();
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
		
		/*
		 *  [main] Main thread started
			ThreadExceptionHandler [handlerId=Default Handler] caught Exception in Thread - " Thread-0" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=handler2] caught Exception in Thread - " Thread-1" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=Default Handler] caught Exception in Thread - " Thread-2" =>java.lang.RuntimeException
			[main] Main thread ends
			ThreadExceptionHandler [handlerId=handler4] caught Exception in Thread - " Thread-3" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=Default Handler] caught Exception in Thread - " Thread-5" =>java.lang.RuntimeException


		 */
		
	}
}
