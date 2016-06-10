package _07实验七_组播编程.MulticastProject2.MulticastClient.test;
import java.io.*;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class multicast {

  public static void main(String[] args) throws IOException {
    //1.加入组播组239.1.2.3:22363
    MulticastSocket socket = new MulticastSocket(22363);
    InetAddress address = InetAddress.getByName("239.1.2.3");
    socket.joinGroup(address);
   
    DatagramPacket packet;
    for (int i = 0; i <100; i++) {
        //2.创建数据包
        byte[] buf = new byte[1000];
        packet = new DatagramPacket(buf, buf.length);
        
        //3.接收数据包
        socket.receive(packet);
        
        //4.解析数据包（目的是：获取数据包中的数据，发送方的IP地址和端口号）
        String received = new String(packet.getData());
        System.out.println("1-"+received+"#"+packet.getAddress());
        //sendstr为你要发送的数据内容，即随机码#你建的组播组IP#你建的组播组端口号
        String sendstr=received.substring(0,4)+"#239.0.213.10#5430";
        
        //5.向发送方发送数据包
        packet=new DatagramPacket(sendstr.getBytes(),sendstr.length(),packet.getAddress(),packet.getPort());
        socket.send(packet);
        
            try {
                //socket.receive(packet);
                //6.等待2秒钟，以便让服务器解析数据，并加入你建的组播组
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(multicast.class.getName()).log(Level.SEVERE, null, ex);
            }
//        System.out.println("2-"+packet.getData()+packet.getAddress());
        //7.向你建的组播组发送个人信息
        String sendstr1="########################################33号-吴成兵-13网络1班#############################################";
        byte[] data=sendstr1.getBytes();
        //这里填写的IP地址和端口号与sendstr中填写的ip地址和端口号应该一样
        packet=new DatagramPacket(data,data.length,InetAddress.getByName("239.0.213.10"),5430);
        socket.send(packet);
    }
    socket.leaveGroup(address);
    socket.close();
  }

}

