package _04实验四_面向SOCKET编程._01签到测试程序;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author K01
 */
//测试服务器    用来做验证点名程序是否能够正确签到
public class Server {
    public static final int PORT = 33333;
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            while(true) {
                Socket socket = server.accept();
                new ServerThread(socket);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
