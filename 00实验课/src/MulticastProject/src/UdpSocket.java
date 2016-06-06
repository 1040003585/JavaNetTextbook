
import java.net.*;
import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UdpSocket {
    DatagramSocket udpSocket;
    String string = "Sign in successfully!";//签到成功
    public Integer rndCode;
    Vector<user> userList=new Vector<user>();

    public UdpSocket(){
        try {
            udpSocket = new DatagramSocket(0);
        } catch (SocketException ex) {
            Logger.getLogger(UdpSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recvProcess(){
      byte buf[] = new byte[1000];
      DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
      System.out.println("开始接受数据："+udpSocket.getLocalPort());
      while (true) {
            try {
                //接收数据报包
                udpSocket.receive(receivePacket);
                //处理数据报包
                packetDataProcess(receivePacket);
            } catch (IOException ex) {
                Logger.getLogger(UdpSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
    }

    public void packetDataProcess(DatagramPacket dp){
        String keyword;;
        String str = new String(dp.getData(), 0, dp.getLength());
        String[] strarray=str.split("#");
        if(strarray.length==3 ){
            boolean checkm=false;
            if(isnumber(strarray[0].substring(0,1))){
            if(Integer.parseInt(strarray[0])==rndCode){
                for(int i =0;i<userList.size();i++){
                    if(strarray[1].equalsIgnoreCase(userList.get(i).ip) && userList.get(i).port==Integer.parseInt(strarray[2])){
                        String string="你指定的组播组（"+strarray[1]+":"+strarray[2]+"）被占用，请重新申请！";
                        byte[] data=string.getBytes();
                        dp=new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
                       SendProcess(dp);
//                       System.out.println("重复组播组");
                       checkm=true;
                    }
                }
                if(!checkm){
                   userList.add(new user(strarray[1],Integer.parseInt(strarray[2])));
                    //创建组播组，接收签到
                   CreateMulticase(strarray[1],Integer.parseInt(strarray[2])) ;
                   String string="你指定的组播组已经成功创建，请向该组播组发送你的个人信息签到，格式为：学号-姓名-班级";
                    byte[] data=string.getBytes();
                    dp=new DatagramPacket(data,data.length,dp.getAddress(),dp.getPort());
                   SendProcess(dp);
                }
            }
            }

//            System.out.println(strarray[0]+"+"+strarray[1]+"+"+strarray[2]+"+");
        }

    }
    public void CreateMulticase(String ip1,Integer port1){
        final String ip=ip1;
        final Integer port = port1;
        new Thread(){
            public void run(){
                try {
                    MulticastSocket socket = new MulticastSocket(port);
                    InetAddress address = InetAddress.getByName(ip);
                    socket.joinGroup(address);
                    DatagramPacket packet;
                    byte[] buf = new byte[1000];
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String received = new String(packet.getData());
                    for(int i =0;i<userList.size();i++){
                        if(ip.equalsIgnoreCase(userList.get(i).ip) && port.equals(userList.get(i).port)){
                            userList.get(i).xs=received;
                        }
                        System.out.println(i+"  "+userList.get(i).ip+":"+userList.get(i).port+"   "+userList.get(i).xs);
                    }
                    socket.leaveGroup(address);
                    socket.close();

                } catch (IOException ex) {
                    Logger.getLogger(UdpSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }


    public void SendProcess(DatagramPacket dp){
        final DatagramPacket dp1=dp;
        new Thread(){
            public void run(){
                try {
                    udpSocket.send(dp1);
                    
                } catch (IOException ex) {
                    Logger.getLogger(UdpSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    public boolean isnumber(String x){
        if("0".equalsIgnoreCase(x)) return true;
        if("1".equalsIgnoreCase(x)) return true;
        if("2".equalsIgnoreCase(x)) return true;
        if("3".equalsIgnoreCase(x)) return true;
        if("4".equalsIgnoreCase(x)) return true;
        if("5".equalsIgnoreCase(x)) return true;
        if("6".equalsIgnoreCase(x)) return true;
        if("7".equalsIgnoreCase(x)) return true;
        if("8".equalsIgnoreCase(x)) return true;
        if("9".equalsIgnoreCase(x)) return true;
        return false;
    }


}
