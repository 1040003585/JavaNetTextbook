package ǩ�����Գ���;

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
//���Է�����    ��������֤���������Ƿ��ܹ���ȷǩ��
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
