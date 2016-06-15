package JavaHttpProxy;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 2. 核心代码，处理链接的代理线程，内部设计了Socket的认证
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-15/下午01:08:50
 * Description:
 */
public class SocketThread extends Thread {
    private Socket socketIn;
    private InputStream isIn;
    private OutputStream osIn;
    //
    private Socket socketOut;
    private InputStream isOut;
    private OutputStream osOut;

    public SocketThread(Socket socket) {
        this.socketIn = socket;
    }

    private byte[] buffer = new byte[4096];
    private static final byte[] VER = { 0x5, 0x0 };
    private static final byte[] CONNECT_OK = { 0x5, 0x0, 0x0, 0x1, 0, 0, 0, 0, 0, 0 };

    public void run() {
        try {
            System.out.println("\n\na client connect " + socketIn.getInetAddress() + ":" + socketIn.getPort());
            isIn = socketIn.getInputStream();
            osIn = socketIn.getOutputStream();
            int len = isIn.read(buffer);
            System.out.println("< " + bytesToHexString(buffer, 0, len));
            osIn.write(VER);
            osIn.flush();
            System.out.println("> " + bytesToHexString(VER, 0, VER.length));
            len = isIn.read(buffer);
            System.out.println("< " + bytesToHexString(buffer, 0, len));
            // 查找主机和端口
            String host = findHost(buffer, 4, 7);
            int port = findPort(buffer, 8, 9);
            System.out.println("host=" + host + ",port=" + port);
            socketOut = new Socket(host, port);
            isOut = socketOut.getInputStream();
            osOut = socketOut.getOutputStream();
            //
            for (int i = 4; i <= 9; i++) {
                CONNECT_OK[i] = buffer[i];
            }
            osIn.write(CONNECT_OK);
            osIn.flush();
            System.out.println("> " + bytesToHexString(CONNECT_OK, 0, CONNECT_OK.length));
            SocketThreadOutput out = new SocketThreadOutput(isIn, osOut);
            out.start();
            SocketThreadInput in = new SocketThreadInput(isOut, osIn);
            in.start();
            out.join();
            in.join();
        } catch (Exception e) {
            System.out.println("a client leave");
        } finally {
            try {
                if (socketIn != null) {
                    socketIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("socket close");
    }

    public static String findHost(byte[] bArray, int begin, int end) {
        StringBuffer sb = new StringBuffer();
        for (int i = begin; i <= end; i++) {
            sb.append(Integer.toString(0xFF & bArray[i]));
            sb.append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static int findPort(byte[] bArray, int begin, int end) {
        int port = 0;
        for (int i = begin; i <= end; i++) {
            port <<= 16;
            port += bArray[i];
        }
        return port;
    }

    // 4A 7D EB 69
    // 74 125 235 105
    public static final String bytesToHexString(byte[] bArray, int begin, int end) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = begin; i < end; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
            sb.append(" ");
        }
        return sb.toString();
    }
}

