

/**
 *
 * @author 
 */
import java.io.*;

public class IOTest6_2 {
    public static void main(String args[]){
        int b;
        byte buffer[]=new byte[2500];
        try{
            FileInputStream readfile=new FileInputStream("test.txt");
            b=readfile.read(buffer,0,2500);
            try{
                String str=new String(buffer,0,b,"Default");
                System.out.println(str);
            }
            catch(UnsupportedEncodingException e)
            {
                System.out.println("The File was not found:"+e);
            }
         }
        catch(IOException e){
                System.out.println("File read Error");
            }
    }
}
