package _4_3Socket编程示例._4_4_3CS环境下的套接字应用程序;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-8/下午10:05:41
 * Description:
 */
public class KKMultiServer {
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = null;
            boolean listening = true;
            try {
                serverSocket = new ServerSocket(4444);
            } catch (IOException ex) {
                Logger.getLogger(KKMultiServer.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-1);
            }
            while(listening) {
                new KKMultiServerThread(serverSocket.accept()).start();
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(KKMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}