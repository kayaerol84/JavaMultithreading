package threads.terminate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import threads.common.LoopTaskD;
import threads.common.LoopTaskE;

public class TerminatingThreadFirstWay {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");

		LoopTaskE task1 = new LoopTaskE();
		LoopTaskE task2 = new LoopTaskE();
		LoopTaskE task3 = new LoopTaskE();
		
		new Thread(task1, "CoolThread-1").start();
		new Thread(task2, "CoolThread-2").start();
		
		new Thread(task3, "CoolThread-3").start();
		
		TimeUnit.MILLISECONDS.sleep(2000);
		
		task1.cancel();
		task2.cancel();
		task3.cancel();
		
		/*ExecutorService executor = Executors.newCachedThreadPool(); // new DaemonThreadsFactory()

		executor.execute(new LoopTaskD(100));
		executor.execute(new LoopTaskD(200));
		executor.execute(new LoopTaskD(300));
		executor.execute(new LoopTaskD(400));

		executor.shutdown();
*/
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
}
