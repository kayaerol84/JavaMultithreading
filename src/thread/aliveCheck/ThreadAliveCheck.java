package thread.aliveCheck;

import java.util.concurrent.TimeUnit;

import thread.common.LoopTaskD;

public class ThreadAliveCheck {


	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread started");
		
		
		Thread t1 = new Thread(new LoopTaskD(1200));
		
		Thread t2 = new Thread(new LoopTaskD(2200));
		
		boolean isThread1Alive = t1.isAlive();
		boolean isThread2Alive = t2.isAlive();
		
		System.out.println("[" + currentThreadName + "] BEFORE STARTING; Is " + t1.getName() + " alive = "+ isThread1Alive);
		System.out.println("[" + currentThreadName + "] BEFORE STARTING; Is " + t2.getName() + " alive = "+ isThread2Alive);
		
		t1.start();
		t2.start();
		
		while (true) {
			TimeUnit.SECONDS.sleep(1);
			
			isThread1Alive = t1.isAlive();
			isThread2Alive = t2.isAlive();
			
			System.out.println("[" + currentThreadName + "] Is " + t1.getName() + " alive = "+ isThread1Alive);
			System.out.println("[" + currentThreadName + "] Is " + t2.getName() + " alive = "+ isThread2Alive);
		
			if(!isThread1Alive && !isThread2Alive)
				break;
		}
		
		System.out.println("[" + currentThreadName + "] Main thread ends");
	}
}
