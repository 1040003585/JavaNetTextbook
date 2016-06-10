package _5_2DatagramSocket编程示例._5_2_2利用数据报通信的CS程序;

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
 * Date/Time: 2016-6-10/下午10:36:26
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
				System.out.print("来自主机："+name);
				System.out.print("，的端口："+port);
				System.out.println("的数据："+receiveData);
			}
		} catch (SocketException e) {
			System.out.println("不能打开数据报Socket,或数据报Socket无法与指定端口连接");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("网络通信出现问题，问题在于："+e.toString());
		}
		}

}
