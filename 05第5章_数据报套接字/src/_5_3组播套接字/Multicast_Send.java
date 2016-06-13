package _5_3×é²¥Ì×½Ó×Ö;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Multicast_Send {

	public static void main(String[] args) {
		
		try {
			InetAddress address=InetAddress.getByName("224.0.0.1");
			int port=5000;
			byte[] data="multicastsocket programming".getBytes();
			DatagramPacket datap = new DatagramPacket(data,data.length,address,port);
			MulticastSocket multicastSocket=new MulticastSocket();
			multicastSocket.send(datap);
		} catch (UnknownHostException e) {System.err.println("UnknownHostException");
		} catch (IOException e) {System.err.println("IOException");
		}
		
	}
}
