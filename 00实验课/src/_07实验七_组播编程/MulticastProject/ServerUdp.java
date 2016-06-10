package _07实验七_组播编程.MulticastProject;

import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerUdp {
    //定义一个时钟
    private static Timer timer;
    static public void main(String args[]) {
        //定义一个UDP Socket对象，端口随机产生
        final UdpSocket us = new UdpSocket();
        //实例化时钟对象
        timer = new Timer();
        //运行时钟
        timer.schedule(new TimerTask() {
            String string;
            DatagramPacket sendPacket;
            public void run() {
                    try {
                        //产生一个随机码，范围为1000-6000
                        us.rndCode=(int)(1000+Math.random()*5000);
                        string = us.rndCode+"为随机码,该随机码为签到钥匙，"+"通过该钥匙向源端返回请求信息，"
                                  + "格式为：随机码#你建的组播组IP#你建的组播组端口号，例如：2356#239.0.0.1#8080";
                        byte[] data=string.getBytes();
                        //创建组播组数据包，组播地址为：239.1.2.3，端口为22363
                        sendPacket = new DatagramPacket(data, data.length,
                                InetAddress.getByName("239.1.2.3"), 22363);
                        //发送相关数据包
                        us.SendProcess(sendPacket);

                    } catch (UnknownHostException ex) {
                        Logger.getLogger(ServerUdp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, 10000, 10000);//10秒启动，每10秒执行一次
            //启用UDP Socket接收处理方法
            us.recvProcess();

    }
}
