package testKetangxuoye_UTF8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 * @author admin ���һ������ʵ���յ�һ�����ݰ�����Է�����һ�仰������������յ� ����ҵ���䣺wlrjzy@126.com
 */
public class UDP_receive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			final DatagramSocket receiveSocket = new DatagramSocket(5000);	//�������������ն˿�5000
			byte buf[] = new byte[1000];
			final DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("starting to receive pachet");
			while (true) {
				System.out.println("----------------------------------------");
				System.out.println("���ǽ����������ڵȴ����գ�����������");
				//����
				receiveSocket.receive(receivePacket);
				
				//���̲߳���
				Thread thread=new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String ipname = receivePacket.getAddress().toString();
						int port=receivePacket.getPort();
						System.out.println("����������" + ipname + "\n�˿�"
								+ port);
						String s = new String(receivePacket.getData(), 0, receivePacket
								.getLength());
						System.out.println("the receiver data:" + s);
						
						//�ظ�
						DatagramSocket sendSocket = null;
						DatagramPacket datagramPacket = null;
						try {
							sendSocket = new DatagramSocket();
							String string = "���յ�лл...";
							byte[] databyte = new byte[100];
							databyte = string.getBytes();
							datagramPacket = new DatagramPacket(databyte, string
									.length(), InetAddress.getByName("127.0.0.1"), 5123);
							sendSocket.send(datagramPacket);
							System.out.println("�ѷ�\"���յ�лл\"");
								
						} catch (SocketException e) {
							e.printStackTrace();
						} catch (UnknownHostException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}finally{
							sendSocket.close();
						}
						
						
						
						
					}//run()
					
				});
				thread.start();
				
				
			}//while
		} catch (SocketException e) {
			System.out.println("����ͨ�ų��ִ���������SocketException......");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("����ͨ�ų��ִ���������IOException...");
		}
		
	}
}
