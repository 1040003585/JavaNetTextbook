package _6_1在Java中实现多线程._1_继承Thread类;
/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-19/下午12:50:54
 * Description:
 */
public class TestMitiThread {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+"线程运行开始");
		new MitiThread("A").start();
		new MitiThread("B").start();
		
		System.out.println(Thread.currentThread().getName()+"线程运行结束");
	}
	
}
