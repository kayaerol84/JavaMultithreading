package thread.exceptionHandlers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.common.ExceptionLeakikngTask;

public class HandlingExecutorUncaughtException {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));
		
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		es.execute(new ExceptionLeakikngTask());
		
		ExecutorService es2 = Executors.newCachedThreadPool();
		es2.execute(new ExceptionLeakikngTask());
		es2.execute(new ExceptionLeakikngTask());
		es2.execute(new ExceptionLeakikngTask());
		
		es.shutdown();
		es2.shutdown();
				
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
		
		/*
		 * [main] Main thread started
			ThreadExceptionHandler [handlerId=DEFAULT_HANDLER] caught Exception in Thread - " pool-1-thread-2" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=DEFAULT_HANDLER] caught Exception in Thread - " pool-1-thread-1" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=DEFAULT_HANDLER] caught Exception in Thread - " pool-1-thread-3" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=DEFAULT_HANDLER] caught Exception in Thread - " pool-2-thread-1" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=DEFAULT_HANDLER] caught Exception in Thread - " pool-2-thread-2" =>java.lang.RuntimeException
			ThreadExceptionHandler [handlerId=DEFAULT_HANDLER] caught Exception in Thread - " pool-2-thread-3" =>java.lang.RuntimeException
			[main] Main thread ends

		 */
		
	}
}
