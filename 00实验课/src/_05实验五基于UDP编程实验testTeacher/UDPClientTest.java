package _05实验五基于UDP编程实验testTeacher;

import java.io.*;
import java.net.*;

public class UDPClientTest {
	public static void main(String args[]) {
		try {
			// 发送
			DatagramSocket localSocket = new DatagramSocket(3456);
			String string = "222.17.106.99:5000:Wu_Being";
			byte[] databyte = new byte[100];
			databyte = string.getBytes();
			DatagramPacket localPacket = new DatagramPacket(databyte, string
					.length(), InetAddress.getByName("222.17.106.113"), 5000);
			localSocket.send(localPacket);
			System.out.println("客户端开始传送数据！");

			// 接收回复
			System.out.println("开始接受数据：");
			while (true) {
				localSocket.receive(localPacket);
				String name = localPacket.getAddress().toString();
				int port=localPacket.getPort();
				System.out.println("/n来自主机：" + name + "/n端口："
						+ port);
				String s = new String(localPacket.getData(), 0, localPacket
						.getLength());
				System.out.println("接受到的数据是: " + s);

				localPacket = new DatagramPacket(databyte, string.length(),
						InetAddress.getByName("222.17.106.113"), port);
				localSocket.send(localPacket);
			}

		} catch (SocketException e) {
			System.out.println("不能打开数据报Socket，或数据报Socket无法与指定端口连接!");
		} catch (IOException ioe) {
			System.out.println(ioe.toString());
		}
	}
}