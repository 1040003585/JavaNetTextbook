package _5_3�鲥�׽���;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCServer {

	public static void main(String[] args) throws IOException {

		System.out.println("Server starting...");
		MulticastSocket s = new MulticastSocket();
		InetAddress group = InetAddress.getByName("231.0.0.1");
		byte[] dummy = new byte[0];
		DatagramPacket dgp = new DatagramPacket(dummy, 0, group, 10000);
		for (int i = 0; i < 30000; i++) {
			byte[] buffer = ("Video line " + i).getBytes();
			dgp.setData(buffer);
			dgp.setLength(buffer.length);
			s.send(dgp);
		}
	}
}
