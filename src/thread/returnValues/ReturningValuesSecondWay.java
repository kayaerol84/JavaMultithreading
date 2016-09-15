package thread.returnValues;

import thread.common.ValueReturningTaskB;

public class ReturningValuesSecondWay {
	
	public static void main(String[] args) {
		
		Thread.currentThread().setName("Main Thread");
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		ValueReturningTaskB task1 = new ValueReturningTaskB(5, 8, 2000, new SumObserver("task-Id-1"));
		Thread thread1 = new Thread(task1, "Thread-1");
		
		ValueReturningTaskB task2 = new ValueReturningTaskB(3, 8, 2000, new SumObserver("task-Id-2"));
		Thread thread2 = new Thread(task2, "Thread-2");
		
		ValueReturningTaskB task3 = new ValueReturningTaskB(1, 8, 2000, new SumObserver("task-Id-3"));
		Thread thread3 = new Thread(task3, "Thread-3");
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
	
}
