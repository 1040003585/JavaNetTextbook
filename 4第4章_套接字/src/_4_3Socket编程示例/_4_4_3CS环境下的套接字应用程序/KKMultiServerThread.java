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
 * Date/Time: 2016-6-8/下午10:05:54
 * Description:
 */
public class KKMultiServerThread extends Thread {
    private Socket socket = null;
    public KKMultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }
    
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine, outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            while((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                System.out.println(inputLine);
                out.println(outputLine);
                if(outputLine.equals("Bye")) {
                    break;
                }
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(KKMultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}