package ǩ��������UTF8;

import java.io.*;
import java.net.*;

//������������
public class SocketServer {
  // Ϊ���Socketѡ��һ���Ͽ�8080:

  public static final int PORT = 50000;

  public static void main(String[] args) throws IOException {
    ServerSocket s = new ServerSocket(PORT);
    System.out.println("��ʼ: " + s);
    while(true) {
        Socket socket = s.accept();
        new SocketThread(socket);
    }
  }
}

