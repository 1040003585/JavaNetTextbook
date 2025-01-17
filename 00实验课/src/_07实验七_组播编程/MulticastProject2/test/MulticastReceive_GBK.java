package _07实验七_组播编程.MulticastProject2.test;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.DatagramPacket;  
import java.net.InetAddress;  
import java.net.MulticastSocket;  
import java.util.Date;  
 
/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-14/下午08:47:59
 * Description: 组播的客户端
 */
public class MulticastReceive_GBK {  
  
      
    public static void main(String[] args) throws Exception {  
        test();  
    }  
      
    public static void test() throws Exception{  
        InetAddress group = InetAddress.getByName("239.1.2.3");//组播地址  
        int port = 22363;  
       MulticastSocket msr = null;//创建组播套接字  
        try {  
            msr = new MulticastSocket(port);  
            msr.joinGroup(group);//加入连接  
            byte[] buffer = new byte[8192];  
            System.out.println("接收数据包启动！(启动时间: "+new Date()+")");  
            while(true){  
                //建立一个指定缓冲区大小的数据包  
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);  
                msr.receive(dp);  
                String s = new String(dp.getData(),0,dp.getLength());  
                //解码组播数据包  
                System.out.println(s);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            if(msr!=null){  
                try {  
                    msr.leaveGroup(group);  
                    msr.close();  
                } catch (Exception e2) {  
                    // TODO: handle exception  
                }  
            }  
        }  
    }  
      
 
}  
