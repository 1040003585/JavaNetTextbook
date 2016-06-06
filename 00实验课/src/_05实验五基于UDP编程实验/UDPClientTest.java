package _05实验五基于UDP编程实验;

import java.io.*;
import java.net.*;
 
public class UDPClientTest {
  public static void main(String args[]) {
    try {
      DatagramSocket sendSocket = new DatagramSocket(3456);
      String string = "iopiopiopiopiopiopiopipio";
      byte[] databyte = new byte[100];
      databyte = string.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(databyte, string.length(),
          InetAddress.getByName("192.168.1.101"), 5000);
      sendSocket.send(sendPacket);
      System.out.println("客户端开始传送数据！");
    }
    catch (SocketException e) {
      System.out.println("不能打开数据报Socket，或数据报Socket无法与指定端口连接!");
    }
    catch (IOException ioe) {
      System.out.println(ioe.toString());
    }
  }
}