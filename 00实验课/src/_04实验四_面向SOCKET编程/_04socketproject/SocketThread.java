package _04ʵ����_����SOCKET���._04socketproject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
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
public class SocketThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static FileWriter fileWriter;
    private static int count = 1;
    
    static {
        try {
            fileWriter = new FileWriter("C:\\temp.txt", true);
        } catch (IOException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public SocketThread(Socket socket) {
        try {
            this.socket =socket;
            out = new PrintWriter( new BufferedWriter(new OutputStreamWriter( socket.getOutputStream())), true);
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out.println("ǩ����ʽΪ��IP��ַ:�˿ں�:�༶:����ѧ�ţ�");
            start();
        } catch (IOException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private boolean checkserver(String str){

        String a="IP��ַ:�˿ں�:�༶:����ѧ��";
     try{
      int num=str.indexOf(":");
        String ip=str.substring(0, num);
//        System.out.println(ip);
        int num1=str.indexOf(":", num+1);
//        System.out.println(num+":"+num1);
//        System.out.println(str.substring(num+1,num1));
           Integer port=Integer.parseInt(str.substring(num+1,num1));
          InetAddress addr1 =InetAddress.getByName(ip);
          Socket socket1 =new Socket(addr1, port);
          PrintWriter out = new PrintWriter( new BufferedWriter(new OutputStreamWriter( socket1.getOutputStream())), true);
          out.println(str.substring(num1)+"--���ǩ���Ѿ����");
          System.out.println("--ǩ���ɹ���");
          socket1.close();
        }catch(IOException e){
            System.out.println("--ǩ�����ɹ���");
            return false;
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("--ǩ�����ɹ���");
            return false;
        }
        return true;
      }
    
    public void run() {
        try {
            String str = in.readLine();
            if(str.length()>0){
                System.out.print(count + ":" + str+socket.getInetAddress());
                //check server
                if(!checkserver(str)){
                    out.println("û�м�⵽��ķ����������ܸ�ʽ���ԣ�ǩ����ʽΪ��IP��ַ:�˿ں�:�༶:����ѧ�ţ�");
//            System.out.print/ln("--ǩ�����ɹ�");
                } else {
                    fileWriter.write(count+"  ");
                    fileWriter.write(str);
                    fileWriter.write("\r\n");
                    count++;
                }
                fileWriter.flush();
            }   } catch (IOException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
