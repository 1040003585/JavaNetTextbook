package _04ʵ����_����SOCKET���._04socketproject;


import java.io.*;

import java.net.*;

public class SocketServer {

	// Ϊ���Socketѡ��һ���Ͽ�8080:

	public static final int PORT = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("��ʼ: " + s);
		while (true) {
			Socket socket = s.accept();
			new SocketThread(socket);
		}
	}

	private static boolean checkserver(String str) {

		String a = "IP��ַ:�˿ں�:�༶:����ѧ��";
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
			out.println(str.substring(num1) + "--���ǩ���Ѿ����");
			System.out.println("--ǩ���ɹ���");
			socket1.close();
		} catch (IOException e) {
			System.out.println("--ǩ�����ɹ���");
			return false;
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("--ǩ�����ɹ���");
			return false;
		}
		return true;
	}

}
