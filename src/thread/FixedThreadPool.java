package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	public static void main(String[] args) {
		System.out.println("Main thread started");
		
		ExecutorService executor = Executors.newFixedThreadPool(6);
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		executor.submit(new LoopTaskA());
		System.out.println("Main thread ended");
		
		// Program is still running
		/*
		 * Why?
		 * we need to carry out the shutdown process
		 */
		
		executor.shutdown();
		
		System.err.println("Main thread is shutting down");
		
		/*
		 * Fixed thread pool size = 3 iken 6 tane thread yaratirsak ilk 3ununden birinin bitmesini bekliyor. 
		 */
		
		/*
		 * shutdown'dan sonra submit edersek
		 * compile error yok ama bu thread icin runtime exception firlatiliyor
		 * Task java.util.concurrent.FutureTask@1909752 rejected from java.util.concurrent.ThreadPoolExecutor@1f96302[Shutting down, pool size = 3, active threads = 3, queued tasks = 3, completed tasks = 0]
		 */
		executor.submit(new LoopTaskA());
	}
}
