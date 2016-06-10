package _04实验四_面向SOCKET编程;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// 指定使用本地IP
		InetAddress addr = InetAddress.getByName(null);
		System.out.println("Caddr = " + addr);
		Socket socket = new Socket(addr, SocketServer.PORT);
		//Socket socket = new Socket("222.17.106.71", 31288);
			// 将代码放在Try语句中执行，以确保程序能关闭socket
		try {
			System.out.println("Csocket = " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			// 定义一个PrintWriter对象写输出流
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			for (int i = 0; i < 2; i++) {
				out.println("C测试：" + i);//to->SocketServer
				String str = in.readLine();//from->SocketServer
				System.out.println("C自服务端： " + str);
			}
			out.println("END");//END
		} finally {
			System.out.println("C关闭");
			socket.close();
		}
	}

}
