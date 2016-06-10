package _05ʵ����_����UDP���ʵ��.testKetangxuoye;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDP_sent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket sendSocket = null;
		DatagramSocket receiveSocket = null;
		try {
			sendSocket = new DatagramSocket();
			String string = "hello, This is ����������";
			// string = new String(string.getBytes("gbk"),"utf-8");
			byte[] databyte = new byte[100];
			databyte = string.getBytes();
			DatagramPacket datagramPacket = new DatagramPacket(databyte, string
					.length(), InetAddress.getByName("127.0.0.1"), 5000);
			sendSocket.send(datagramPacket);
			System.out.println("���Ƿ�������������");

			// ���ջظ�
			receiveSocket = new DatagramSocket(5123);
			byte buf[] = new byte[1000];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			receiveSocket.receive(receivePacket);
			String name = receivePacket.getAddress().toString();
			int port = receivePacket.getPort();
			System.out.println("����������" + name + "\n�˿�" + port);
			String s = new String(receivePacket.getData(), 0, receivePacket
					.getLength());
			System.out.println("�յ��ظ�:" + s);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sendSocket != null) {
				sendSocket.close();
			}
			if (receiveSocket != null) {
				receiveSocket.close();
			}
		}
	}

}
