package testKetangxuoye_GBK;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 
 * @author admin ���һ������ʵ���յ�һ����ݰ����Է�����һ�仰�����������յ� ����ҵ���䣺wlrjzy@126.com
 */
public class UDP_receive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			DatagramSocket receiveSocket = new DatagramSocket(5000);
			byte buf[] = new byte[1000];
			DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("starting to receive pachet");
			while (true) {
				System.out.println("----------------------------------------");
				System.out.println("这是接收器，正在等待接收！！！！！��������");
				//����
				receiveSocket.receive(receivePacket);
				String ipname = receivePacket.getAddress().toString();
				int port=receivePacket.getPort();
				System.out.println("来自主机�" + ipname + "\n端口"
						+ port);
				String s = new String(receivePacket.getData(), 0, receivePacket
						.getLength());
				System.out.println("the receiver data:" + s);
				
				//�ظ�
				DatagramSocket sendSocket = new DatagramSocket();
				String string = "已收到谢谢.....";
				byte[] databyte = new byte[100];
				databyte = string.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(databyte, string
						.length(), InetAddress.getByName("127.0.0.1"), 5123);
				sendSocket.send(datagramPacket);
				System.out.println("已发\"已收到谢谢\"");
				
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����ͨ�ų��ִ���������" + e.toString());
		}
	}
}
