package testKetangxuoye_UTF8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 * @author admin 设计一个程序，实现收到一个数据包后向对方发送一句话，你的数据已收到 交作业邮箱：wlrjzy@126.com
 */
public class UDP_receive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			final DatagramSocket receiveSocket = new DatagramSocket(5000);	//开启服务器接收端口5000
			byte buf[] = new byte[1000];
			final DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
			System.out.println("starting to receive pachet");
			while (true) {
				System.out.println("----------------------------------------");
				System.out.println("这是接收器，正在等待接收！！！！！！");
				//接收
				receiveSocket.receive(receivePacket);
				
				//多线程并发
				Thread thread=new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						String ipname = receivePacket.getAddress().toString();
						int port=receivePacket.getPort();
						System.out.println("来自主机：" + ipname + "\n端口"
								+ port);
						String s = new String(receivePacket.getData(), 0, receivePacket
								.getLength());
						System.out.println("the receiver data:" + s);
						
						//回复
						DatagramSocket sendSocket = null;
						DatagramPacket datagramPacket = null;
						try {
							sendSocket = new DatagramSocket();
							String string = "已收到谢谢...";
							byte[] databyte = new byte[100];
							databyte = string.getBytes();
							datagramPacket = new DatagramPacket(databyte, string
									.length(), InetAddress.getByName("127.0.0.1"), 5123);
							sendSocket.send(datagramPacket);
							System.out.println("已发\"已收到谢谢\"");
								
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
			System.out.println("网络通信出现错误，问题在SocketException......");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("网络通信出现错误，问题在IOException...");
		}
		
	}
}
