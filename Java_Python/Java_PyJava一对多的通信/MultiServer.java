
import java.net.*;
import java.io.*;


public class MultiServer {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("S:Start......");
		try {
			ServerSocket serverSocket = new ServerSocket(9955);
			Socket connectToClientSocket=null;
			int i=0;
			while(++i!=0){
				connectToClientSocket=serverSocket.accept();
				new ServerThread(connectToClientSocket,i);
			}
		}
		catch (Exception e) {
		}
	
	}

}
