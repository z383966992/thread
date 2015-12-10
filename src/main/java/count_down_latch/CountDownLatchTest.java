package count_down_latch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch一个计数器，等待所有线程完成
 * @author zhouliangliang1
 *
 */
public class CountDownLatchTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void test() throws Exception{
		CountDownLatch latch = new CountDownLatch(2);
		Worker worker1 = new Worker("张三", 5000, latch);
		Worker worker2 = new Worker("李四", 8000, latch);
		worker1.start();
		worker2.start();
		latch.await();
		System.out.println("work finished");
	}
	
	public static void main(String[] args) throws Exception{
		new CountDownLatchTest().test();
	}
	
	class Worker extends Thread {
		
		private String workerName;
		private int workTime;
		private CountDownLatch latch;
		
		public Worker(String workerName, int workTime, CountDownLatch latch) {
			this.workerName = workerName;
			this.workTime = workTime;
			this.latch = latch;
		}
		
		@Override
		public void run() {
			try {
				System.out.println("worker :" + workerName + "work begin at : " + format.format(new Date()));
				work();
				System.out.println("worker :" + workerName + "work end at : " + format.format(new Date()));
			} finally {
				latch.countDown();
			}
		}
		
		private void work() {
			try {
				Thread.sleep(workTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


