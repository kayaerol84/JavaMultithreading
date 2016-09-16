package thread.daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import thread.common.LoopTaskD;

public class DaemonThreadsWithExecutors {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		
		ExecutorService executor = Executors.newCachedThreadPool(new DaemonThreadsFactory());
		
		executor.execute(new LoopTaskD(100));
		executor.execute(new LoopTaskD(200));
		executor.execute(new LoopTaskD(300));
		executor.execute(new LoopTaskD(400));
		
		executor.shutdown();
		
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
}
