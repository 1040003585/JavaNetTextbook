package _4_3Socket编程示例._4_4_3CS环境下的套接字应用程序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Copyright ? 2016 Authors. All rights reserved.
 *
 * FileName: .java
 * @author : Wu_Being <1040003585@qq.com>
 * Date/Time: 2016-6-8/下午10:05:33
 * Description:
 */
public class KnockKnockClient {
    public static void main(String[] args) {
        try {
            Socket kkSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                kkSocket = new Socket("127.0.0.1", 4444);
                out = new PrintWriter(kkSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            } catch (IOException ex) {
                Logger.getLogger(KnockKnockClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromUser;
            String fromServer;
            while((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if(fromServer.equals("bye")) {
                    break;
                }
                fromUser = stdIn.readLine();
                if(fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                    out.flush();
                }
            }
            out.close();
            in.close();
            stdIn.close();
            kkSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(KnockKnockClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
