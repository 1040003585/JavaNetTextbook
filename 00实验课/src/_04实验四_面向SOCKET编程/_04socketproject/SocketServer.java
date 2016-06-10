package _04实验四_面向SOCKET编程._04socketproject;


import java.io.*;

import java.net.*;

public class SocketServer {

	// 为这个Socket选择一个断口8080:

	public static final int PORT = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("开始: " + s);
		while (true) {
			Socket socket = s.accept();
			new SocketThread(socket);
		}
	}

	private static boolean checkserver(String str) {

		String a = "IP地址:端口号:班级:姓名学号";
		try {
			int num = str.indexOf(":");
			String ip = str.substring(0, num);
			// System.out.println(ip);
			int num1 = str.indexOf(":", num + 1);
			// System.out.println(num+":"+num1);
			// System.out.println(str.substring(num+1,num1));
			Integer port = Integer.parseInt(str.substring(num + 1, num1));
			InetAddress addr1 = InetAddress.getByName(ip);
			Socket socket1 = new Socket(addr1, port);
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket1.getOutputStream())), true);
			out.println(str.substring(num1) + "--你的签到已经完成");
			System.out.println("--签到成功！");
			socket1.close();
		} catch (IOException e) {
			System.out.println("--签到不成功！");
			return false;
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("--签到不成功！");
			return false;
		}
		return true;
	}

}
