//--------------- 文件名：Server.java ----------------------------------------------------------------
package _4_3Socket编程示例._4_4_1一对一的通信;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * _4_3Socket编程示例._4_4_1一对一的通信
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: Server.java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-7/下午02:35:45
 * Description: 服务器端接收客户端圆半径，计算圆的面积
 */
public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("S等待连接......");
		try {
			//创建端口号9955的服务器套接字
			ServerSocket serverSocket = new ServerSocket(9955);
			//监听来处客户的连接请求
			Socket socket=serverSocket.accept();
			System.out.println("S连接请求来自："+socket.getInetAddress().getHostAddress());
			//创建数据输入输出流
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			
			boolean goon=true;
			while(goon){		
				String string=dis.readUTF();			//从socket中读取数据
				if(string.equals("end")==false){
					string=dealWith(string);			//服务器执行特定功能
					dos.writeUTF(string);				//向socket dos写数据
					dos.flush();						//清空缓冲区，立即发送
					System.out.println("S计算结果（"+string+"）已经发送");
				}else{
					goon=false;
					dos.writeUTF("end");
					dos.flush();
				}

			}

			//关闭socket和流
			serverSocket.close();
			dis.close();
			dos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 服务器执行特定功能函数，这里是个计算圆面积的功能
	 * @param string 圆的半径
	 * @return 圆的面积
	 */
	public static String dealWith(String string){
		System.out.print("S接收到的半径值（"+string+"）；");	
		double radius=0.0;
		try {
			radius=Double.parseDouble(string);			
		} catch (NumberFormatException e) {
			return "输入数据格式不对！";
		}
		if(radius<0)return "数据数据不能小于0！";
		double area=radius*radius*Math.PI;
		return Double.toString(area);
	}

}
