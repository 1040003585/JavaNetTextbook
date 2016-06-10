package _5_2DatagramSocket���ʾ��._5_2_2�������ݱ�ͨ�ŵ�CS����;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 * 
 * FileName: .java
 * 
 * @author : Wu_Being <1040003585@qq.com> Date/Time: 2016-6-10/����10:36:20
 *         Description:
 */
public class UDPSender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while (true) {
			Scanner scanner = new Scanner(System.in);
			try {
				System.out.print("������Ҫ���͵����ݣ�");
				String string = scanner.nextLine();
				DatagramSocket sendSocket = new DatagramSocket();// �˿ں����
				// byte[] databyte=new byte[100]
				byte[] databyte = string.getBytes();
				DatagramPacket sentPacket = new DatagramPacket(databyte,
						databyte.length, InetAddress.getByName("127.0.0.1"),
						5000);
				sendSocket.send(sentPacket);
				System.out.println("send the data:" + string);
			} catch (SocketException e) {
				System.out.println("���ܴ����ݱ�Socket,�����ݱ�Socket�޷���ָ���˿�����");
			} catch (IOException e) {
				System.out.println("����ͨ�ų������⣬�������ڣ�" + e.toString());
			}

		}
	}
}
