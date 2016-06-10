package _01实验一_基础的IO流编程技术;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 
 */
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class IOTest6_3 {
    static Frame f;
    public static void main(String args[]){
        byte byt[]=new byte[2500];
        int b;
        TextArea text;
        text=new TextArea(30,20);
        f=new Frame();
        f.setSize(20,20);
        f.setVisible(true);
        f.add(text);f.pack();
        f.addWindowListener(new WindowAdapter(){//侦听
            public void windowClosing(WindowEvent e){
                f.setVisible(false);
                System.exit(0);
            }
        });
        try{
            FileInputStream readfile=new FileInputStream("test.txt");
            while((b=readfile.read(byt,0,2500))!=1){
                String str=new String(byt,0,b,"Default");
                //System.out.println(str);
                text.append(str);
            }
            readfile.close();
         }
        catch(IOException e){
            System.out.println("File read Error");
        }
    }
}
