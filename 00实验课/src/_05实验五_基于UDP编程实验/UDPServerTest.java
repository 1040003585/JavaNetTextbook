package _05ʵ����_����UDP���ʵ��;

import java.io.*;
import java.net.*;
public class UDPServerTest {
  static public void main(String args[]) {
    try {
      DatagramSocket receiveSocket = new DatagramSocket(5000);
      byte buf[] = new byte[1000];
      DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
      System.out.println("��ʼ�������ݣ�");
      while (true) {
        receiveSocket.receive(receivePacket);
        String name = receivePacket.getAddress().toString();
        System.out.println("/n����������" + name + "/n�˿ڣ�"
                           + receivePacket.getPort());
        String s = new String(receivePacket.getData(), 0,
                              receivePacket.getLength());
        System.out.println("���ܵ���������: " + s);
      }
    }
    catch (SocketException e) {
      e.printStackTrace();
      System.exit(1);
    }
    catch (IOException e) {
      System.out.println("����ͨѶ���ִ���������" + e.toString());
    }
  }
}