package _04实验四_面向SOCKET编程;

import java.io.*;
import java.net.*;

public class SocketServer {
	// 为这个Socket选择一个断口8080:
	public static final int PORT = 8888;

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("S开始: " + s);
		try {
			// 生成一个Socket等待连接请求
			Socket socket = s.accept();
			try {
				System.out.println("S接受连接请求： " + socket);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				// 定义一个PrintWriter对象写输出流
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);
				while (true) {
					String str = in.readLine();
					if (str.equals("END")) {
						break;
					}
					System.out.println("S自客户端： " + str);
					out.println(str+" ++S");//++
				}
				// 关闭socket
			} finally {
				System.out.println("S关闭...");
				socket.close();
			}
		} finally {
			s.close();
		}
	}
}