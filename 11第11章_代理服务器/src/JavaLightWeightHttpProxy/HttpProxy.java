package JavaLightWeightHttpProxy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 * 
 * FileName: .java
 * 
 * @author : Wu_Being <1040003585@qq.com> Date/Time: 2016-6-15/下午01:03:23
 *         Description: http://www.jb51.net/article/48908.htm
 *         http://www.moonsos.com/Article/WebSecurity/68.html
 *         (Java编写代理服务器(Burp拦截Demo)) http://my.oschina.net/u/267384/blog/173243
 *         Java中HttpURLConnection使用代理服务器
 *         http://www.ibm.com/developerworks/cn/java/l-javaproxy/ 用Java开发代理服务器
 *         http://www.codeforge.cn/article/97354一款Java实现的HTTP代理服务器，兼容HTTP/1.1,
 *         支持SSL链接。RabbIT可以通过过滤背景图片，降低图片的解析率来加速网络浏览的速度。...
 */
public class HttpProxy {

	int tcpPort = 0;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private Thread thread = null;

	public HttpProxy(int port) throws IOException {
		tcpPort = port;
		serverSocket = new ServerSocket(tcpPort);
		thread = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						socket = serverSocket.accept();
						System.out.println("有人来送钱了");
						if (socket != null) {
							new HTTPSession(socket);
						}
					}
				} catch (IOException ioe) {
					System.err.println(ioe);
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	/**
	 * Stops the server.
	 */
	public void stop() {
		try {
			serverSocket.close();
			thread.join();
		} catch (IOException ioe) {
		} catch (InterruptedException e) {
		}
	}

}