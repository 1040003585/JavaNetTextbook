package _07实验七_组播编程;


import java.io.*;
import java.net.*;

public class ClientTest {
	public static void main(String[] args) throws IOException {
		MulticastSocket socket = new MulticastSocket(22363);
		InetAddress address = InetAddress.getByName("239.1.2.3");
//		MulticastSocket socket = new MulticastSocket(22363);
//		InetAddress address = InetAddress.getByName("222.17.106.99");
		socket.joinGroup(address);
		DatagramPacket packet;
		for (int i = 0; i < 5; i++) {
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			String received = new String(packet.getData());
			System.out.println("Quote of the Moment: " + received);
		}
		socket.leaveGroup(address);
		socket.close();
	}
}