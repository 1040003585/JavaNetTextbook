
public class Readme {

}
/*
 *  ʵ���� ����SOCKET���
��ǩ�� socketimport���stringjava������
2009-03-26 15:37 1689���Ķ� ����(0) �ղ� �ٱ�
 ���ࣺ  ����ͨ�ű�̣�17��  
��Ȩ����������Ϊ����ԭ�����£�δ������������ת�ء�

ʵ���� ����SOCKET���
ʵ��Ŀ�ģ�
1�����SOCKET�Ļ���ԭ��
2��ѧ������SOCKET��̵Ĵ����д������������ݣ�
3����������SOCKET��̵ľ���Ӧ�á�
ʵ��Ҫ��
1��������������������SOCKET��̵�JAVA���̣����ڷ������Ϳͻ���ϵͳ��
2������SOCKET��JAVA�࣬������ȷ��������ʵ��SOCKETͨ�ţ�
3����JAVA��Ĺ��ܽ����ع㣬ʹSOCKET����ĳһ�����Ӧ�á�
ʵ�����ݣ�
1�������������˹��̺�JAVA�࣬������ԭ����Ϊ��
import java.io.*;
import java.net.*;
public class SocketServer {
  // Ϊ���Socketѡ��һ���Ͽ�8080:
  public static final int PORT = 8080;
  public static void main(String[] args) throws IOException {
    ServerSocket s = new ServerSocket(PORT);
    System.out.println("��ʼ: " + s);
    try {
      // ����һ��Socket�ȴ���������
      Socket socket = s.accept();
      try {
        System.out.println(
            "������������ " + socket);
        BufferedReader in =
            new BufferedReader(
            new InputStreamReader(
            socket.getInputStream()));
        // ����һ��PrintWriter����д�����
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
          System.out.println("�Կͻ��ˣ� " + str);
          out.println(str);
        }
        // �ر�socket
      }
      finally {
        System.out.println("�ر�...");
        socket.close();
      }
    }
    finally {
      s.close();
    }
  }
}
2�������ͻ��˹��̺�JAVA�࣬������ԭ����Ϊ��
import java.io.*;
import java.net.*;
import ch07.section03.*;
public class socketClient {
  public static void main(String[] args) throws IOException {
    // ָ��ʹ�ñ���IP
    InetAddress addr =
        InetAddress.getByName(null);
    System.out.println("addr = " + addr);
    Socket socket =
        new Socket(addr, SocketServer.PORT);
    // ���������Try�����ִ�У���ȷ�������ܹر�socket
    try {
      System.out.println("socket = " + socket);
      BufferedReader in =
          new BufferedReader(
          new InputStreamReader(
          socket.getInputStream()));
      // ����һ��PrintWriter����д�����
      PrintWriter out =
          new PrintWriter(
          new BufferedWriter(
          new OutputStreamWriter(
          socket.getOutputStream())), true);
      for (int i = 0; i < 10; i++) {
        out.println("���� " + i);
        String str = in.readLine();
        System.out.println("�Է���ˣ� " + str);
      }
      out.println("END");
    }
    finally {
      System.out.println("�ر�");
      socket.close();
    }
  }
}
3������ʹ���������������������С�
4���Է������˺Ϳͻ��˵ĳ����������Ķ�����⣬Ҫ������ÿ����䶼����������庬�壬ÿ���඼�˽��书�ܡ�
5�������ϳ�������ʵ����޸ģ�Ҫ��SOCKETͨ�Ź��ܱ���ʵ�֡�
��ע���޸ĵķ����У�ͼ�λ����桢��SOCKET���á�����̱߳�̵ȡ���
6��׫дʵ�鱨��
ʵ�鱨�����ʵ�鱨����ҳ��ʵ�鲽�裨ÿ�������ݣ��г����Ҫ����Դ������н���������������ʵ���ĵá�
*/
