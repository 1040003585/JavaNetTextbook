package _04实验四_面向SOCKET编程._01签到测试程序;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

//测试客户端        用于测试点名程序是否能够正确签到
//当socket连接数过多会导致缓冲区不足，以致客户端连接失败
public class SocketClient {

	public static void main(String[] args) throws IOException {

		// 指定使用本地IP
		InetAddress addr = InetAddress.getByName(null);
		for (int i = 0; i < 2; i++) {
			new Runnable() {
				public void run() {
					try {
						Socket socket = new Socket("127.0.0.2", 31288);

						// 将代码放在Try语句中执行，以确保程序能关闭socket
						try {

							System.out.println("socket = " + socket);

							BufferedReader in = new BufferedReader(
									new InputStreamReader(socket
											.getInputStream()));

							// 定义一个PrintWriter对象写输出流
							PrintWriter out = new PrintWriter(
									new BufferedWriter(new OutputStreamWriter(
											socket.getOutputStream())), true);

							out
									.println("127.0.0.3:33333:13网络1班：吴成兵1314080903133");
						} finally {

							System.out.println("关闭");

							socket.close();

						}
					} catch (IOException ex) {
						Logger.getLogger(SocketClient.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				}
			}.run();
		}

	}

}
