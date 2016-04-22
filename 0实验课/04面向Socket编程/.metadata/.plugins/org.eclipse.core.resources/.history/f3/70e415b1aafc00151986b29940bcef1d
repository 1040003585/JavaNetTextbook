package 测试程序;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
//用于测试
public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static int count = 1;

    public ServerThread(Socket socket) {
        try {
            this.socket = socket;
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            start();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            String str = in.readLine();
            System.out.println(count++ + ":" + str);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
