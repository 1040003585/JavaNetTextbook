package _04ʵ����_����SOCKET���._04socketproject;

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
		// ָ��ʹ�ñ���IP
		InetAddress addr = InetAddress.getByName(null);
		Socket socket = new Socket(addr, SocketServer.PORT);
		// ���������Try�����ִ�У���ȷ�������ܹر�socket
		try {
			System.out.println("socket = " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			// ����һ��PrintWriter����д�����
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			// for (int i = 0; i < 10; i++) {
			//
			// out.println("���� " + i);
			//
			String str = in.readLine();
			System.out.println("�Է���ˣ� " + str);
			//
			// }
			// out.println("127.0.0.1:1000:12����2�ࣺ��������1214080613209");
		} finally {
			System.out.println("�ر�");
			socket.close();
		}
	}
}
