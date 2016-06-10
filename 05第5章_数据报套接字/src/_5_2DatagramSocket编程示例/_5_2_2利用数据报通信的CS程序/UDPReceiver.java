package _5_2DatagramSocket���ʾ��._5_2_2�������ݱ�ͨ�ŵ�CS����;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-10/����10:36:26
 * Description:
 */
public class UDPReceiver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			DatagramSocket receiveSocket =new DatagramSocket(5000);
			byte buf[]=new byte[1000];
			DatagramPacket receivepPacket=new DatagramPacket(buf,buf.length);
			System.out.println("starting to receive packet...");
			while(true){
				receiveSocket.receive(receivepPacket);
				String receiveData=new String(receivepPacket.getData(),0,receivepPacket.getLength());
				String name=receivepPacket.getAddress().getHostAddress();
				int port=receivepPacket.getPort();
				System.out.print("����������"+name);
				System.out.print("���Ķ˿ڣ�"+port);
				System.out.println("�����ݣ�"+receiveData);
			}
		} catch (SocketException e) {
			System.out.println("���ܴ����ݱ�Socket,�����ݱ�Socket�޷���ָ���˿�����");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("����ͨ�ų������⣬�������ڣ�"+e.toString());
		}
		}

}
