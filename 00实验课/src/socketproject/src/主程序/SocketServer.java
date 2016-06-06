package 主程序;

import java.io.*;

import java.net.*;

//点名程序服务端
public class SocketServer {

  // 为这个Socket选择一个断口8080:

  public static final int PORT = 31288;

  public static void main(String[] args) throws IOException {
    ServerSocket s = new ServerSocket(PORT);
    System.out.println("开始: " + s);
    while(true) {
        Socket socket = s.accept();
        new SocketThread(socket);
    }
  }
}

