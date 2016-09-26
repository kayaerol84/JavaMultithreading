package thread.terminating;

import java.util.concurrent.TimeUnit;

import thread.common.LoopTaskF;

public class TerminatingThreadSecondWay {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");

		LoopTaskF task1 = new LoopTaskF();
		LoopTaskF task2 = new LoopTaskF();
		LoopTaskF task3 = new LoopTaskF();

		Thread t1 = new Thread(task1, "CoolThread-1");
		t1.start();
		Thread t2 = new Thread(task2, "CoolThread-2");
		t2.start();
		Thread t3 = new Thread(task3, "CoolThread-3");
		t3.start();
		TimeUnit.MILLISECONDS.sleep(3000);

		System.out.println("[" + currentThreadName + "] interrupting " + t1.getName() + "........");
		t1.interrupt();

		System.out.println("[" + currentThreadName + "] interrupting " + t2.getName() + "........");
		t2.interrupt();

		System.out.println("[" + currentThreadName + "] interrupting " + t3.getName() + "........");
		t3.interrupt();

		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
}
