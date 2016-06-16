/*
 * HttpProxy.java
 *
 * Created on 2016年6月15日, 下午4:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author K01
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
                                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                                                System.out.println(socket.getInetAddress());
                                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                                                
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