package _05ʵ�������UDP���ʵ��;

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
      System.out.println("�ͻ��˿�ʼ�������ݣ�");
    }
    catch (SocketException e) {
      System.out.println("���ܴ����ݱ�Socket�������ݱ�Socket�޷���ָ���˿�����!");
    }
    catch (IOException ioe) {
      System.out.println(ioe.toString());
    }
  }
}