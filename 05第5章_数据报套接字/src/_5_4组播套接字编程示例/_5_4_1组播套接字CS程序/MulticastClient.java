package _5_4组播套接字编程示例._5_4_1组播套接字CS程序;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {

	public static void main(String[] args) throws IOException {
		MulticastSocket socket = new MulticastSocket(4446);
		InetAddress address = InetAddress.getByName("236.122.133.1");
		// socket.setTimeToLive(0);//在本机
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
