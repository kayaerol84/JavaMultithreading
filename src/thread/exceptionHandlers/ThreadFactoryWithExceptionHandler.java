package thread.exceptionHandlers;

import thread.NamedThreadFactory;

public class ThreadFactoryWithExceptionHandler extends NamedThreadFactory {

	//private static int count = 0;
	//private static String NAME = "AmazingThread-";
	@Override
	public Thread newThread(Runnable r) {
		// 
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new ThreadExceptionHandler());
		return t;
	}

}
