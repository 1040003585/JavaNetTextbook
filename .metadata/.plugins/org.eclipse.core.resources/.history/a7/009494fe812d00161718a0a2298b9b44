/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 套接字.CS环境下的套接字应用程序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
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
