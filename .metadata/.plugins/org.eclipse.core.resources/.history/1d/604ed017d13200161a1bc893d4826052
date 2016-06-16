package JavaHttpProxy;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1. 主服务，用来侦听端口
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-15/下午01:07:37
 * Description: http://blog.csdn.net/java2000_net/article/details/7826660
 */
public class SocketProxy {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8888);
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				new SocketThread(socket).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}