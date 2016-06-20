package _6_6守护线程;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-20/上午10:59:06
 * Description:
 * 当一个应用程序的所有非守护线程终止运行时，即使仍然有守护线程在运行，应用程序也将终止。
 * 反之，只要有一个非守护线程在运行，应用程序就不会终止。
 */
public class DaemonTest {

	public static void main(String[] args) {
		Thread thread = new Daemon();
		System.out.println("thread.isDaemon()=" + thread.isDaemon());
		//等待非守护线程的回车结束运行
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("UnDaemon Waiting for CR to end all daemon threads ......");
		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
