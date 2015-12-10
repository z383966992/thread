package thread;

//启动三个线程，打印ABC ABC十次
public class ThreadPrinter implements Runnable {

	private String name;
	private Object prev;
	private Object self;

	public ThreadPrinter(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					self.notify();
				}
				
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) throws Exception {

		Object a = new Object();
		Object b = new Object();
		Object c = new Object();

		ThreadPrinter tpA = new ThreadPrinter("A", c, a);
		ThreadPrinter tpb = new ThreadPrinter("B", a, b);
		ThreadPrinter tpc = new ThreadPrinter("C", b, c);

		new Thread(tpA).start();
		Thread.sleep(10);
		new Thread(tpb).start();
		Thread.sleep(10);
		new Thread(tpc).start();
		Thread.sleep(10);
	}

}
