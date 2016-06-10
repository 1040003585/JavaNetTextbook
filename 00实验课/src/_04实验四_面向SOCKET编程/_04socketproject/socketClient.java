package _04实验四_面向SOCKET编程._04socketproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class socketClient {

	public static void main(String[] args) throws IOException {
		// 指定使用本地IP
		InetAddress addr = InetAddress.getByName(null);
		Socket socket = new Socket(addr, SocketServer.PORT);
		// 将代码放在Try语句中执行，以确保程序能关闭socket
		try {
			System.out.println("socket = " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			// 定义一个PrintWriter对象写输出流
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			// for (int i = 0; i < 10; i++) {
			//
			// out.println("测试 " + i);
			//
			String str = in.readLine();
			System.out.println("自服务端： " + str);
			//
			// }
			// out.println("127.0.0.1:1000:12网络2班：黄晓冰：1214080613209");
		} finally {
			System.out.println("关闭");
			socket.close();
		}
	}
}
