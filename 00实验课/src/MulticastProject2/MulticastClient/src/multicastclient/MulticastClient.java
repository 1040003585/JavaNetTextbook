/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastclient;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date;
/**
 *
 * @author K01
 */
public class MulticastClient {
    public static void main(String[] args) throws IOException {
        MulticastSocket socket;
        int port = 22363;                                                       // 端口  
        socket = new MulticastSocket(22363);
        InetAddress address = InetAddress.getByName("239.1.2.3");
            socket.joinGroup(address);
        DatagramPacket packet;
        MulticastSocket mss = null;
        byte [] buf = new byte[256];
        packet = new DatagramPacket (buf,buf.length);
        try {
                for(;;){
                    socket.receive (packet);
                    String received = new String(packet.getData());
                    System.out.println("Quote of the moment:"+received);
                    mss = new MulticastSocket(port);                            // 创建一个用于发送和接收的MulticastSocket组播套接字对象  
                    mss.joinGroup(address);                                     // 使用组播套接字joinGroup(),将其加入到一个组播  
        //            String message = "\n2548#239.213.213.13#6666\n";
          //          byte[] buffer2 = message.getBytes();
            //        DatagramPacket dp = new DatagramPacket(buffer2, 
              //          buffer2.length, address, port);  
                //    mss.send(dp);                                               // 使用组播套接字的send()方法，将组播数据包对象放入其中，发送组播数据包  
                  //  System.out.println("发送数据包给" 
                    //        + address + ":" + port);  
                    //System.out.println("发送数据包:" + message);
                }
            }catch (IOException e) {  
                    e.printStackTrace();  
            }
            socket.leaveGroup(address);
            socket.close();
        }
}