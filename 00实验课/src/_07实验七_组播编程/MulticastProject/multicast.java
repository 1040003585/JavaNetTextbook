package _07实验七_组播编程.MulticastProject;

import java.io.*;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class multicast {

  public static void main(String[] args) throws IOException {
    //创建组播组Socket
    MulticastSocket socket = new MulticastSocket(22363);
    //指定组播IP地址
    InetAddress address = InetAddress.getByName("239.1.2.3");
    //加入指定组播IP的组播组
    socket.joinGroup(address);
    //创建DatagramPacket对象
    DatagramPacket packet;
    for (int i = 0; i <1000; i++) {
        //定义字节数组
        byte[] buf = new byte[1000];
        //依据字节数组创建组播组
        packet = new DatagramPacket(buf, buf.length);
        //等待接收组播数据报
        socket.receive(packet);
        //获取组播数据包中的数据并转成字符串
        String received = new String(packet.getData());
        //显示接收到的数据，显示对方的IP
        System.out.println("1-"+received+packet.getAddress());
        //取接收到的数据的前4个字节+特定字符串(#239.0.1.1#12346)组成一个新的字符串
        String sendstr=received.substring(0,4)+"#239.0.1.1#12346";
        //打包组建的字符串为向对方发送的数据包
        packet=new DatagramPacket(sendstr.getBytes(),sendstr.length(),packet.getAddress(),packet.getPort());
        //发送数据包
        socket.send(packet);
            try {
                //线程睡2秒钟
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(multicast.class.getName()).log(Level.SEVERE, null, ex);
            }
        //字符串格式
        String sendstr1="0号-肖东-计算机班";
        //字符串创建字节数组
        byte[] data=sendstr1.getBytes();
        //创建一个新的组播组的数据包
        packet=new DatagramPacket(data,data.length,InetAddress.getByName("239.0.1.1"),12346);
        //发送数据包
        socket.send(packet);
    }
    //离开组播组
    socket.leaveGroup(address);
    //关闭组播组Socket
    socket.close();
  }

}

