package _5_2DatagramSocket编程示例;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-10/上午12:36:45
 * Description: 查询端口的占用情况
 */
public class UDPScan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int port = 0; port < 65536; port++) {
			try {
				DatagramSocket server = new DatagramSocket(port);
				server.close();
			} catch (SocketException e) {
				System.out.println("there is a server in port:" + port + ".");
			}

		}

	}

}
