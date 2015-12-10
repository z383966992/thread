package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
	
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	public void test() throws Exception{
		notFull.await();
	}
	
	
	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
		
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		
		pool.execute(t1);
		pool.schedule(t2, 1000, TimeUnit.MILLISECONDS);
		pool.schedule(t3, 10, TimeUnit.MILLISECONDS);
		pool.submit(task)
		pool.shutdown();
		System.out.println("sdfsfsd");
		
	}

}

class MyThread extends Thread{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "ÕýÔÚÖ´ÐÐ...");
	}
}
