package thread.exceptionHandlers;

import thread.NamedThreadFactory;

public class ThreadFactoryWithExceptionHandlerAlternator extends NamedThreadFactory {

	private static int count = 0;
	//private static String NAME = "AmazingThread-";
	@Override
	public Thread newThread(Runnable r) {
		// 
		Thread t = new Thread(r);
		int sequence = ++count;
		if (sequence % 2 ==0) {
			t.setUncaughtExceptionHandler(new ThreadExceptionHandler("handler"+ count));	
		}
		
		return t;
	}

}
