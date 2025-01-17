package _6_7线程组;
/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-20/下午01:23:45
 * Description:
 */
public class TestAccess {

	public static void main(String[] args) {
		// xGroup1隶属于系统线程组
		ThreadGroup xGroup1 = new ThreadGroup("xGroup1N");
		// yGroup11隶属于xGroup1组
		ThreadGroup yGroup11 = new ThreadGroup(xGroup1, "yGroup11N");
		// zGroup111隶属于yGroup11组
		ThreadGroup zGroup111 = new ThreadGroup(yGroup11, "zGroup111N");

		// Thread one1 = new TestThread1(xGroup1, "one1N");
		// Thread one2 = new TestThread1(xGroup1, "one2N");
		// Thread two1 = new TestThread3(yGroup11, "two1N");
		Thread three1 = new TestThread3(zGroup111, "three1N");
		// Thread three2 = new TestThread3(zGroup111, "three2N");
	}
}







