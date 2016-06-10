package _05实验五_基于UDP编程实验;

import java.io.*;
import java.net.*;
public class UDPServerTest {
  static public void main(String args[]) {
    try {
      DatagramSocket receiveSocket = new DatagramSocket(5000);
      byte buf[] = new byte[1000];
      DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
      System.out.println("开始接受数据：");
      while (true) {
        receiveSocket.receive(receivePacket);
        String name = receivePacket.getAddress().toString();
        System.out.println("/n来自主机：" + name + "/n端口："
                           + receivePacket.getPort());
        String s = new String(receivePacket.getData(), 0,
                              receivePacket.getLength());
        System.out.println("接受到的数据是: " + s);
      }
    }
    catch (SocketException e) {
      e.printStackTrace();
      System.exit(1);
    }
    catch (IOException e) {
      System.out.println("网络通讯出现错误，问题在" + e.toString());
    }
  }
}