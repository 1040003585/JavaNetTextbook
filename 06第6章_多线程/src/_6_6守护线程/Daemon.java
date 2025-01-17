package _6_6守护线程;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-20/上午10:59:36
 * Description:
 */
public class Daemon extends Thread {

	private static final int SIZE = 10;
	private Thread[] t = new Thread[SIZE];

	public Daemon() {
		setDaemon(true);
		start();
	}

	public void run() {
		for (int i = 0; i < SIZE; i++) {
			t[i] = new DaemonSpawn(i);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < SIZE; i++) {
			System.out.println("t[" + i + "].isDaemon()=" + t[i].isDaemon());
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while (true) {
			yield();
		}
	}

	/**
	 * 
	 * Copyright ? 2016 Authors. All rights reserved.
	 *
	 * FileName: .java
	 * @author : Wu_Being <1040003585@qq.com>
	 * Date/Time: 2016-6-20/上午11:00:15
	 * Description:
	 */
	public class DaemonSpawn extends Thread {


		public DaemonSpawn(int i) {
			System.out.println("DaemonSpawn " + i + " started...");
			start();
		}

		public void run() {
			while (true) {
				yield();
			}
		}
	}

}
