
public class Readme {

}
/*
 *  实验四 面向SOCKET编程
标签： socketimport编程stringjava服务器
2009-03-26 15:37 1689人阅读 评论(0) 收藏 举报
 分类：  网络通信编程（17）  
版权声明：本文为博主原创文章，未经博主允许不得转载。

实验四 面向SOCKET编程
实验目的：
1、理解SOCKET的基本原理；
2、学会面向SOCKET编程的代码编写及理解各语句内容；
3、掌握面向SOCKET编程的具体应用。
实验要求：
1、建立两个独立的面向SOCKET编程的JAVA工程，用于服务器和客户端系统；
2、建立SOCKET的JAVA类，并能正确运行且能实现SOCKET通信；
3、对JAVA类的功能进行拓广，使SOCKET用于某一具体的应用。
实验内容：
1、建立服务器端工程和JAVA类，类程序的原代码为：
import java.io.*;
import java.net.*;
public class SocketServer {
  // 为这个Socket选择一个断口8080:
  public static final int PORT = 8080;
  public static void main(String[] args) throws IOException {
    ServerSocket s = new ServerSocket(PORT);
    System.out.println("开始: " + s);
    try {
      // 生成一个Socket等待连接请求
      Socket socket = s.accept();
      try {
        System.out.println(
            "接受连接请求： " + socket);
        BufferedReader in =
            new BufferedReader(
            new InputStreamReader(
            socket.getInputStream()));
        // 定义一个PrintWriter对象写输出流
        PrintWriter out =
            new PrintWriter(
            new BufferedWriter(
            new OutputStreamWriter(
            socket.getOutputStream())), true);
        while (true) {
          String str = in.readLine();
          if (str.equals("END")) {
            break;
          }
          System.out.println("自客户端： " + str);
          out.println(str);
        }
        // 关闭socket
      }
      finally {
        System.out.println("关闭...");
        socket.close();
      }
    }
    finally {
      s.close();
    }
  }
}
2、建立客户端工程和JAVA类，类程序的原代码为：
import java.io.*;
import java.net.*;
import ch07.section03.*;
public class socketClient {
  public static void main(String[] args) throws IOException {
    // 指定使用本地IP
    InetAddress addr =
        InetAddress.getByName(null);
    System.out.println("addr = " + addr);
    Socket socket =
        new Socket(addr, SocketServer.PORT);
    // 将代码放在Try语句中执行，以确保程序能关闭socket
    try {
      System.out.println("socket = " + socket);
      BufferedReader in =
          new BufferedReader(
          new InputStreamReader(
          socket.getInputStream()));
      // 定义一个PrintWriter对象写输出流
      PrintWriter out =
          new PrintWriter(
          new BufferedWriter(
          new OutputStreamWriter(
          socket.getOutputStream())), true);
      for (int i = 0; i < 10; i++) {
        out.println("测试 " + i);
        String str = in.readLine();
        System.out.println("自服务端： " + str);
      }
      out.println("END");
    }
    finally {
      System.out.println("关闭");
      socket.close();
    }
  }
}
3、调试使以上两个工程能正常运行。
4、对服务器端和客户端的程序代码进行阅读和理解，要求做到每条语句都能明白其具体含义，每个类都了解其功能。
5、对以上程序进行适当的修改，要求SOCKET通信功能保持实现。
（注：修改的方向有：图形化界面、多SOCKET互访、结合线程编程等。）
6、撰写实验报告
实验报告包：实验报告首页、实验步骤（每步的内容，有程序的要求有源码和运行结果及结果描述）、实验心得。
*/
