import java.io.*;
import java.net.*;

public class UDPClientTest {
	public static void main(String args[]) {
		try {
			// ����
			DatagramSocket localSocket = new DatagramSocket(11111);
			String string = "222.17.106.99:11111:2:Wu_Being";
			byte[] databyte = new byte[100];
			databyte = string.getBytes();
			DatagramPacket localPacket = new DatagramPacket(databyte, string
					.length(), InetAddress.getByName("222.17.106.113"), 5000);
			localSocket.send(localPacket);
			System.out.println("�ͻ��˿�ʼ������ݣ�fasong...");

			// ���ջظ�
			System.out.println("��ʼ������ݣ�jieshou...");
			while (true) {
				localSocket.receive(localPacket);
				String name = localPacket.getAddress().toString();
				int port = localPacket.getPort();
				System.out.println("/n��������" + name + "/n�˿ڣ�" + port);
				String s = new String(localPacket.getData(), 0, localPacket
						.getLength());
				System.out.println("���ܵ��������: " + s);
				string = s + "_" + string;
				databyte = (string).getBytes();
				localPacket = new DatagramPacket(databyte, (string).length(),
						InetAddress.getByName("222.17.106.113"), port);
				localSocket.send(localPacket);
			}

		} catch (SocketException e) {
			System.out.println("���ܴ���ݱ�Socket������ݱ�Socket�޷���ָ���˿�����!");
		} catch (IOException ioe) {
			System.out.println(ioe.toString());
		}
	}
}